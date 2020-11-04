package com.vanniktech.rxriddles.operators.withscheduler;

import com.vanniktech.rxriddles.util.Unit;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Riddle11Test {

	@Test
	public void test() {
		final TestScheduler rxRule = new TestScheduler();

		final Unit unit = Unit.create();

		Observable<Unit> source = Observable.fromIterable(asList(50L, 200L, 250L, 400L))
											.flatMapSingle(it -> Single.timer(it, MILLISECONDS, rxRule).map(__ -> unit));

		TestObserver<Unit> o = Riddle11.solve(source, rxRule).test().assertEmpty();

		rxRule.advanceTimeBy(50, MILLISECONDS);
		o.assertValuesOnly(unit); // Handle the first one.

		rxRule.advanceTimeBy(200, MILLISECONDS);
		o.assertValuesOnly(unit); // Don't handle any others.

		rxRule.advanceTimeBy(150, MILLISECONDS);
		o.assertResult(unit, unit); // Now process the next one.
	}
}