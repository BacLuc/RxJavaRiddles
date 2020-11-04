package com.vanniktech.rxriddles.solutions.operators.withscheduler;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;

public class RiddleCarouselSolution {
	/**
	 * After each fetchInterval, fetch the current acquirer List.
	 * From the fetched acquirer List, emit the next item after carouselInterval.
	 * Repeat the same acquirer List, until the next time the acquirerList is fetched.
	 * Then use the new acquirer List.
	 * <p>
	 * Use Case: You want to show the available acquirers from an external resource and show each of them for carouselInterval in the UI.
	 */
	public static Observable<String> solve(Callable<List<String>> acquirerCallable, Duration fetchInterval, Duration carouselInterval, Scheduler scheduler) {
		return Observable.interval(0, fetchInterval.toMillis(), TimeUnit.MILLISECONDS, scheduler)
						 .map(__ -> acquirerCallable.call())
						 .switchMap(strings -> Observable.interval(0, carouselInterval.toMillis(), TimeUnit.MILLISECONDS, scheduler)
														 .map(index -> index % strings.size())
														 .map(index -> strings.get(Math.toIntExact(index))));
	}

	public static Observable<String> solve2(Callable<List<String>> acquirerCallable, Duration fetchInterval, Duration carouselInterval, Scheduler scheduler) {
		return Observable.interval(0, fetchInterval.toMillis(), TimeUnit.MILLISECONDS, scheduler)
						 .map(__ -> acquirerCallable.call())
						 .switchMap(strings -> Observable.interval(0, carouselInterval.toMillis(), TimeUnit.MILLISECONDS, scheduler)
														 .zipWith(Observable.fromIterable(strings)
																			.take(strings.size())
																			.repeatWhen(o -> o.delay(carouselInterval.toMillis(),
																									 TimeUnit.MILLISECONDS,
																									 scheduler)), (aLong, s) -> s));
	}
}
