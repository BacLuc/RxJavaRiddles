package com.vanniktech.rxriddles.operators.withscheduler;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import org.junit.Test;
import org.mockito.Mockito;

public class RiddleCarouselTest {

	private static final Duration CAROUSEL_INTERVAL = Duration.ofMillis(10);
	public static final int FETCH_INTERVAL_FACTOR = 6;
	private static final Duration FETCH_INTERVAL = CAROUSEL_INTERVAL.multipliedBy(FETCH_INTERVAL_FACTOR);
	private static final String ACQUIRER_1 = "ACQUIRER_1";
	private static final String ACQUIRER_2 = "ACQUIRER_2";
	private static final String ACQUIRER_3 = "ACQUIRER_3";
	private static final List<String> ACQUIRERS_1 = Arrays.asList(ACQUIRER_1, ACQUIRER_2, ACQUIRER_3);
	private static final String ACQUIRER_4 = "ACQUIRER_4";
	private static final String ACQUIRER_5 = "ACQUIRER_5";
	private static final String ACQUIRER_6 = "ACQUIRER_6";
	private static final List<String> ACQUIRERS_2 = Arrays.asList(ACQUIRER_4, ACQUIRER_5, ACQUIRER_6);

	@Test
	public void emits_the_next_acquirer_after_interval() throws Exception {
		TestScheduler scheduler = new TestScheduler();
		@SuppressWarnings("unchecked")
		Callable<List<String>> acquirerCallable = Mockito.mock(Callable.class);
		Mockito.when(acquirerCallable.call()).thenReturn(ACQUIRERS_1);
		TestObserver<String> testObserver = RiddleCarousel.solve(acquirerCallable, FETCH_INTERVAL, CAROUSEL_INTERVAL, scheduler).test();

		scheduler.advanceTimeBy(CAROUSEL_INTERVAL.toMillis() - 1, TimeUnit.MILLISECONDS);
		testObserver.assertValues(ACQUIRER_1);

		scheduler.advanceTimeBy(1, TimeUnit.MILLISECONDS);
		testObserver.assertValues(ACQUIRER_1, ACQUIRER_2);

		scheduler.advanceTimeBy(4 * CAROUSEL_INTERVAL.toMillis(), TimeUnit.MILLISECONDS);
		testObserver.assertValues(ACQUIRER_1, ACQUIRER_2, ACQUIRER_3, ACQUIRER_1, ACQUIRER_2, ACQUIRER_3);

		Mockito.when(acquirerCallable.call()).thenReturn(ACQUIRERS_2);
		scheduler.advanceTimeBy(CAROUSEL_INTERVAL.toMillis(), TimeUnit.MILLISECONDS);
		testObserver.assertValues(ACQUIRER_1, ACQUIRER_2, ACQUIRER_3, ACQUIRER_1, ACQUIRER_2, ACQUIRER_3, ACQUIRER_4);

		scheduler.advanceTimeBy(CAROUSEL_INTERVAL.toMillis(), TimeUnit.MILLISECONDS);
		testObserver.assertValues(ACQUIRER_1, ACQUIRER_2, ACQUIRER_3, ACQUIRER_1, ACQUIRER_2, ACQUIRER_3, ACQUIRER_4, ACQUIRER_5);
	}
}