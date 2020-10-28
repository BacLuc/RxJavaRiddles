package com.vanniktech.rxriddles.operators.multiplestreams;

import java.util.concurrent.TimeUnit;

import com.vanniktech.rxriddles.util.Unit;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import io.reactivex.rxjava3.subjects.PublishSubject;
import org.junit.Test;

public class Riddle9Test {
	@Test
	public void solve() {
		TestScheduler scheduler = new TestScheduler();

		PublishSubject<Unit> trigger = PublishSubject.create();
		final Unit unit = Unit.create();
		Observable<Unit> main = Observable.interval(1, TimeUnit.SECONDS, scheduler).map(__ -> Unit.create());

		TestObserver<Unit> o = Riddle9.solve(main, trigger).test().assertEmpty();

		scheduler.advanceTimeBy(1, TimeUnit.SECONDS);
		o.assertValueCount(1);

		scheduler.advanceTimeBy(15, TimeUnit.SECONDS);
		o.assertValueCount(16);

		trigger.onNext(unit);

		scheduler.advanceTimeBy(15, TimeUnit.SECONDS);
		o.assertValueCount(16);
	}
}
