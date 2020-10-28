package com.vanniktech.rxriddles.operators.multiplestreams;

import java.util.concurrent.atomic.AtomicInteger;

import com.vanniktech.rxriddles.solutions.operators.multiplestreams.Riddle15Solution;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

public class Riddle15Test {

	@Test
	public void solve() {
		TestScheduler scheduler = new TestScheduler();

		AtomicInteger subscribeCounter = new AtomicInteger();
		Observable<Integer> first = Observable.timer(1, SECONDS, scheduler).doOnSubscribe(__ -> subscribeCounter.incrementAndGet()).map(__ -> 1);
		Observable<Integer> second = Observable.just(5).doOnSubscribe(__ -> subscribeCounter.incrementAndGet());

		TestObserver o = Riddle15.solve(first, second).test().assertEmpty();

		assertThat(subscribeCounter.get()).isEqualTo(1); // We want to subscribe immediately.

		scheduler.advanceTimeBy(1, SECONDS);
		o.assertResult(1, 5);
	}
}
