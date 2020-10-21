package com.vanniktech.rxriddles.operators.multiplestreams;

import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;

public class Riddle15Test {

	@Test
	public void test() {
		TestScheduler rxRule = new TestScheduler();

		AtomicInteger subscribeCounter = new AtomicInteger();

		Observable<Integer> first = Observable.timer(1, SECONDS, rxRule)
				.doOnSubscribe(__ -> subscribeCounter.incrementAndGet())
        .map(it -> 1);

		Observable<Integer> second = Observable.just(5)
											   .doOnSubscribe(__ -> subscribeCounter.incrementAndGet());

		TestObserver<Integer> o = Riddle15.solve(first, second)
										  .test()
										  .assertEmpty();

		assertEquals(subscribeCounter.get(),2); // We want to subscribe immediately.

		rxRule.advanceTimeBy(1, SECONDS);

		o.assertResult(1, 5);
	}
}