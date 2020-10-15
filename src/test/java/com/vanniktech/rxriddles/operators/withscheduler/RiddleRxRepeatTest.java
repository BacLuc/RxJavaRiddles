package com.vanniktech.rxriddles.operators.withscheduler;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import io.reactivex.rxjava3.subscribers.TestSubscriber;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RiddleRxRepeatTest {
	private static final Duration INTERVAL = Duration.ofMillis(10);
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
	public void emit_item_on_start_and_after_each_interval() {
		TestSubscriber<Integer> testSubscriber = Single.just(1).toFlowable().compose(RiddleRxRepeat.create(INTERVAL, testScheduler)).test();

		testSubscriber.assertValueCount(1);

		testScheduler.advanceTimeBy(INTERVAL.toMillis(), TimeUnit.MILLISECONDS);
		testSubscriber.assertValueCount(2);

		testScheduler.advanceTimeBy(INTERVAL.toMillis() - 1, TimeUnit.MILLISECONDS);
		testSubscriber.assertValueCount(2);
		testSubscriber.assertNotComplete();

		testSubscriber.cancel();
	}

	@Test
	public void stop_on_error() throws Exception {
		@SuppressWarnings("unchecked")
		Callable<Integer> callable = mock(Callable.class);
		when(callable.call()).thenReturn(1).thenThrow(RuntimeException.class);
		TestSubscriber<Integer> testSubscriber = Single.fromCallable(callable).toFlowable().compose(RiddleRxRepeat.create(INTERVAL, testScheduler)).test();

		testSubscriber.assertValueCount(1);

		testScheduler.advanceTimeBy(INTERVAL.toMillis(), TimeUnit.MILLISECONDS);
		testSubscriber.assertError(RuntimeException.class);

		testScheduler.advanceTimeBy(INTERVAL.toMillis(), TimeUnit.MILLISECONDS);
		testSubscriber.assertError(RuntimeException.class);
		testSubscriber.assertValueCount(1);
	}

	@Test
	public void resubscribe_on_onSubscribe_thread_when_repeat_thread_is_different() {
		TestScheduler subscribeOnThread = new TestScheduler();
		TestSubscriber<Integer> testSubscriber = Single.just(1).subscribeOn(subscribeOnThread).toFlowable().compose(RiddleRxRepeat.create(INTERVAL, testScheduler)).test();

		subscribeOnThread.advanceTimeBy(INTERVAL.toMillis() -1, TimeUnit.MILLISECONDS);
		testSubscriber.assertValueCount(1);

		testScheduler.advanceTimeBy(INTERVAL.toMillis() -1, TimeUnit.MILLISECONDS);
		testSubscriber.assertValueCount(1);

		testScheduler.advanceTimeBy(INTERVAL.toMillis(), TimeUnit.MILLISECONDS);
		testSubscriber.assertValueCount(1);

		subscribeOnThread.triggerActions();
		testSubscriber.assertValueCount(2);

		testSubscriber.cancel();
		subscribeOnThread.shutdown();
	}
}