package com.vanniktech.rxriddles.operators.multiplestreams;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class Riddle6Test {

	@Test
	public void solve() {
		AtomicInteger subscribeCounter = new AtomicInteger();

		TestScheduler scheduler = new TestScheduler();
		Single<Integer> first = Single.timer(5, TimeUnit.SECONDS, scheduler).map(aLong -> 10).doOnSubscribe(disposable -> {
			subscribeCounter.incrementAndGet();
		});

		Single<Integer> second = Single.just(5).doOnSubscribe(disposable -> subscribeCounter.incrementAndGet());

		final TestObserver<Map.Entry<Integer, Integer>> o = Riddle6.solve(first, second).test().assertEmpty();

		assertThat(subscribeCounter.get()).isEqualTo(2); // We want to subscribe immediately.

		scheduler.advanceTimeBy(4, TimeUnit.SECONDS);
		o.assertEmpty();

		scheduler.advanceTimeBy(1, TimeUnit.SECONDS);
		o.assertResult(entry(10, 5));
	}

}
