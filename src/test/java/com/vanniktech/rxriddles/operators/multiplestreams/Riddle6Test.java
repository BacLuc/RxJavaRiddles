package com.vanniktech.rxriddles.operators.multiplestreams;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.Assert.assertEquals;

public class Riddle6Test {

	@Test
	public void test() {

		TestScheduler scheduler = new TestScheduler();

		AtomicInteger subscribeCounter = new AtomicInteger();

		Single<Integer> first = Single.timer(5, SECONDS, scheduler).map(it -> 10).doOnSubscribe(it -> subscribeCounter.incrementAndGet());

		Single<Integer> second = Single.just(5).doOnSubscribe(it -> subscribeCounter.incrementAndGet());

		TestObserver<Map.Entry<Integer, Integer>> o = Riddle6.solve(first, second).test().assertEmpty();

		assertEquals(subscribeCounter.get(), 2);

		scheduler.advanceTimeBy(4, SECONDS);
		o.assertEmpty();

		scheduler.advanceTimeBy(1, SECONDS);
		o.assertResult(entry(10, 5));
	}

}