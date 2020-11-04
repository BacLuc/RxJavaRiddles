package com.vanniktech.rxriddles.operators.onestream;

import io.reactivex.rxjava3.core.Observable;
import org.junit.Test;

public class Riddle12Test {

	@Test
	public void test() {
		Riddle12.solve(Observable.create(it -> {
			it.onNext(1);
			it.onNext(2);
			it.onError(new RuntimeException());
		})).test().assertResult(1, 2, 5);
	}

}