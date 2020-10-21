package com.vanniktech.rxriddles.operators.withscheduler;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.vanniktech.rxriddles.util.Unit;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.Assert.assertEquals;

public class Riddle8Test {

	@Test
	public void test() {
		final TestScheduler scheduler = new TestScheduler();

		final AtomicInteger counter = new AtomicInteger();

		final Unit unit = Unit.create();

		Observable<Unit> source = Observable.just(unit)
											.doOnSubscribe(__ -> counter.incrementAndGet())
											.doOnEach(__ -> counter.incrementAndGet())
											.doOnError(__ -> counter.incrementAndGet())
											.doOnComplete(() -> counter.incrementAndGet());

		TestObserver<Unit> o = Riddle8.solve(source, scheduler).test().assertEmpty();

		assertEquals(counter.get(), 0); // We don't want to do anything.

		scheduler.advanceTimeBy(100, MILLISECONDS);
		o.assertEmpty();
		assertEquals(counter.get(),0);

		scheduler.advanceTimeBy(200, MILLISECONDS);
		o.assertResult(unit);
		assertEquals(counter.get(),4);
	}

}