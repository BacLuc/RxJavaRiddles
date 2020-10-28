package com.vanniktech.rxriddles.hotobservable;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import com.vanniktech.rxriddles.util.Unit;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import org.junit.Before;
import org.junit.Test;

import static com.vanniktech.rxriddles.hotobservable.RiddleHotObservable.PERIOD;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RiddleHotObservableTest {

	private Callable<Unit> callable;
	private TestScheduler scheduler;
	private Observable<Unit> observable;

	@Before
	public void setup() throws Exception {
		callable = mock(Callable.class);
		when(callable.call()).thenReturn(Unit.create());

		scheduler = new TestScheduler();
//		observable = RiddleHotObservable.solve(callable, scheduler);
		observable = RiddleHotObservable.solve(callable, scheduler);
	}

	@Test
	public void when_not_yet_emitted_then_no_value() throws Exception {
		verify(callable, never()).call();
	}

	@Test
	public void when_emitted_but_subscribed_later_then_do_not_call() throws Exception {
		scheduler.advanceTimeBy(PERIOD, TimeUnit.MILLISECONDS);

		final TestObserver<Unit> testObserver = observable.test();
		testObserver.assertNoValues();
		verify(callable, times(0)).call();
	}

	@Test
	public void when_emitted_then_call_once() throws Exception {
		final TestObserver<Unit> testObserver = observable.test();
		testObserver.assertNoValues();

		scheduler.advanceTimeBy(PERIOD, TimeUnit.MILLISECONDS);

		testObserver.assertValueCount(1);
		verify(callable, times(1)).call();
	}

}
