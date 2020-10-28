package com.vanniktech.rxriddles.operators.withscheduler;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import com.vanniktech.rxriddles.solutions.operators.withscheduler.RiddleRxCacheSolution;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import io.reactivex.rxjava3.subscribers.TestSubscriber;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class RiddleRxCacheTest {
	private static final Duration INTERVAL = Duration.ofMillis(100);
	private TestScheduler testScheduler;
	@Before
	public void setup() {
		testScheduler = new TestScheduler();
	}
	@After
	public void tearDown() {
		testScheduler.shutdown();
	}
	@Test
	public void cache_last_value_and_propagate_error() throws Exception {
		@SuppressWarnings("unchecked")
		Callable<Integer> callable = mock(Callable.class);
		when(callable.call()).thenReturn(1).thenReturn(2).thenReturn(3);
		Flowable<Integer> cachedFlowable = Single.fromCallable(callable)
												 .repeatWhen(t -> t.delay(INTERVAL.toMillis(), TimeUnit.MILLISECONDS, testScheduler))
												 .compose(new RiddleRxCache<>());
		TestSubscriber<Integer> testSubscriber1 = cachedFlowable.test();
		testSubscriber1.assertValues(1);

		testScheduler.advanceTimeBy(INTERVAL.toMillis() - 1, TimeUnit.MILLISECONDS);
		TestSubscriber<Integer> testSubscriber2 = cachedFlowable.test();
		testSubscriber1.assertValues(1);
		testSubscriber2.assertValues(1);

		testScheduler.advanceTimeBy(1, TimeUnit.MILLISECONDS);
		testSubscriber1.assertValues(1, 2);
		testSubscriber2.assertValues(1, 2);
		Mockito.verify(callable, times(2)).call();
		TestSubscriber<Integer> testSubscriber3 = cachedFlowable.test();
		testSubscriber3.assertValues(2);

		when(callable.call()).thenThrow(RuntimeException.class).thenReturn(1);
		testScheduler.advanceTimeBy(INTERVAL.toMillis(), TimeUnit.MILLISECONDS);
		testSubscriber1.assertError(RuntimeException.class);
		testSubscriber2.assertError(RuntimeException.class);
		testSubscriber3.assertError(RuntimeException.class);

		TestSubscriber<Integer> testSubscriber4 = cachedFlowable.test();
		testSubscriber4.assertNoErrors();
	}

	@Test
	public void allow_new_connections_after_dispose_and_keep_not_disposed_subscriptions() throws Exception {
		@SuppressWarnings("unchecked")
		Callable<Integer> callable = mock(Callable.class);
		when(callable.call()).thenReturn(1).thenReturn(2).thenReturn(3);
		Flowable<Integer> cachedFlowable = Single.fromCallable(callable)
												 .repeatWhen(t -> t.delay(INTERVAL.toMillis(), TimeUnit.MILLISECONDS, testScheduler))
												 .compose(new RiddleRxCache<>());
		TestSubscriber<Integer> testSubscriber1 = cachedFlowable.test();
		TestSubscriber<Integer> testSubscriber2 = cachedFlowable.test();
		testSubscriber1.assertValues(1);
		testSubscriber2.assertValues(1);

		testScheduler.advanceTimeBy(INTERVAL.toMillis(), TimeUnit.MILLISECONDS);
		testSubscriber1.assertValues(1, 2);
		testSubscriber2.assertValues(1, 2);
		testSubscriber1.cancel();
		TestSubscriber<Integer> testSubscriber3 = cachedFlowable.test();
		testSubscriber3.assertValues(2);

		testScheduler.advanceTimeBy(INTERVAL.toMillis(), TimeUnit.MILLISECONDS);
		testSubscriber1.assertValues(1, 2);
		testSubscriber2.assertValues(1, 2, 3);
		testSubscriber3.assertValues(2, 3);
	}
}
