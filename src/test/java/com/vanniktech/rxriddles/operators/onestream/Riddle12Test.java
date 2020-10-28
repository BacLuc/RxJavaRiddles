package com.vanniktech.rxriddles.operators.onestream;

import io.reactivex.rxjava3.core.Observable;
import org.junit.Test;

public class Riddle12Test {
	@Test
	public void solve() {
		Riddle12.solve(Observable.create(emitter -> {
			emitter.onNext(1);
			emitter.onNext(2);
			emitter.onError(new RuntimeException());
		})).test().assertResult(1, 2, 5);
	}
}
