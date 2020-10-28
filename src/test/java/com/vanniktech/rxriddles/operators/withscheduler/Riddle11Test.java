package com.vanniktech.rxriddles.operators.withscheduler;

import com.vanniktech.rxriddles.util.Unit;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Riddle11Test {

	@Test
	public void solve() {
		TestScheduler testScheduler = new TestScheduler();
		Observable<Unit> source = Observable.fromArray(50L, 200L, 250L, 400L)
											.flatMapSingle(aLong -> Single.timer(aLong, MILLISECONDS, testScheduler))
											.map(__ -> Unit.create());

		TestObserver<Unit> o = Riddle11.solve(source, testScheduler).test().assertEmpty();

		testScheduler.advanceTimeBy(50, MILLISECONDS);
		o.assertValueCount(1); // Handle the first one.

		testScheduler.advanceTimeBy(200, MILLISECONDS);
		o.assertValueCount(1); // Don't handle any others.

		testScheduler.advanceTimeBy(150, MILLISECONDS);
		o.assertValueCount(2); // Now process the next one.
	}
}
