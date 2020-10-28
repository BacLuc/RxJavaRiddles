package com.vanniktech.rxriddles.operators.withscheduler;

import java.util.concurrent.atomic.AtomicInteger;

import com.vanniktech.rxriddles.util.Unit;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.assertj.core.api.Assertions.assertThat;

public class Riddle8Test {
	@Test
	public void solve() {
		TestScheduler scheduler = new TestScheduler();
		AtomicInteger counter = new AtomicInteger();

		final Unit unit = Unit.create();
		Observable<Unit> source = Observable.just(unit).doOnSubscribe(disposable -> {
			counter.incrementAndGet();
		}).doOnEach(unitNotification -> {
			counter.incrementAndGet();
		}).doOnError(throwable -> {
			counter.incrementAndGet();
		}).doOnComplete(counter::incrementAndGet);

		TestObserver<Unit> o = Riddle8.solve(source, scheduler).test().assertEmpty();

		assertThat(counter.get()).isEqualTo(0); // We don't want to do anything.

		scheduler.advanceTimeBy(100, MILLISECONDS);
		o.assertEmpty();
		assertThat(counter.get()).isEqualTo(0);

		scheduler.advanceTimeBy(200, MILLISECONDS);
		o.assertResult(unit);
		assertThat(counter.get()).isEqualTo(4);
	}
}
