package com.vanniktech.rxriddles.operators.multiplestreams;

import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.subjects.PublishSubject;
import org.junit.Test;

import static org.junit.Assert.*;

public class Riddle18Test {

	@Test
	public void test() {
		PublishSubject<Integer> first = PublishSubject.create();
		PublishSubject<Integer> second = PublishSubject.create();
		TestObserver<Integer> o = Riddle18.solve(first, second)
										  .test()
										  .assertEmpty();

		first.onNext(1);
		second.onNext(2);
		o.assertValuesOnly(1);

		first.onNext(3);
		first.onNext(4);
		o.assertValuesOnly(1, 3, 4);

		second.onNext(3);
		second.onNext(4);
		second.onComplete();
		o.assertValuesOnly(1, 3, 4);

		first.onComplete();
		o.assertResult(1, 3, 4);
	}

}