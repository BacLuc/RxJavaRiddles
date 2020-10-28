package com.vanniktech.rxriddles.operators.multiplestreams;

import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import org.junit.Test;

public class Riddle5Test {
	@Test
	public void solve() {
		final BehaviorSubject<Integer> first = BehaviorSubject.createDefault(0);
		final BehaviorSubject<Integer> second = BehaviorSubject.createDefault(0);

		final TestObserver<Integer> o = Riddle5.solve(first, second).test().assertValuesOnly(0);

		first.onNext(5);
		o.assertValuesOnly(0, 5);

		second.onNext(6);
		o.assertValuesOnly(0, 5, 11);

		first.onNext(-6);
		o.assertValuesOnly(0, 5, 11, 0);
	}

}
