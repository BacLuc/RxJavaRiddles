package com.vanniktech.rxriddles.operators.multiplestreams;

import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.SingleSubject;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Riddle16Test {

	@Test
	public void test() {

		PublishSubject<String> subject = PublishSubject.create();

		SingleSubject<Integer> inner1 = SingleSubject.create();
		SingleSubject<Integer> inner2 = SingleSubject.create();

		TestObserver<Integer> o = Riddle16.solve(subject, it -> {
			if (it == "1") {
				return inner1;
			} else {
				return inner2;
			}
		}).test().assertEmpty();

		subject.onNext("1");
		o.assertEmpty();
		assertTrue(inner1.hasObservers());

		subject.onNext("2");
		assertFalse(inner1.hasObservers());
		assertTrue(inner2.hasObservers());

		inner2.onError(new RuntimeException());
		assertFalse(subject.hasObservers());
		o.assertFailure(RuntimeException.class);
	}
}