package com.vanniktech.rxriddles.operators.multiplestreams;

import com.vanniktech.rxriddles.util.Unit;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Riddle9Test {

	@Test
	public void test() {
		final TestScheduler scheduler = new TestScheduler();

		Subject<Unit> trigger = PublishSubject.create();
		Observable<Unit> main = Observable.interval(1, SECONDS).map(__ -> Unit.create());

		TestObserver<Unit> o = Riddle9.solve(main, trigger).test().assertEmpty();

		scheduler.advanceTimeBy(1, SECONDS);
		o.assertValueCount(1);

		scheduler.advanceTimeBy(15, SECONDS);
		o.assertValueCount(16);

		trigger.onNext(Unit.create());

		scheduler.advanceTimeBy(15, SECONDS);
		o.assertValueCount(16);
	}

}