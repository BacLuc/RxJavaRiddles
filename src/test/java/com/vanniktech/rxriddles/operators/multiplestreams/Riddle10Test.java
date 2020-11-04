package com.vanniktech.rxriddles.operators.multiplestreams;

import java.util.AbstractMap;
import java.util.function.Function;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import org.junit.Test;

public class Riddle10Test {

	@Test
	public void test() {
		@NonNull Observable<Integer> first = Observable.just(1, 2);
		Function<Integer, Observable<String>> function = it -> {
			if (it == 1) {
				return Observable.just("1", "2", "3");
			} else {
				return Observable.just("5", "6");
			}
		};

		Riddle10.solve(first, function)
				.test()
				.assertResult(new AbstractMap.SimpleEntry<>(1, "1"),
							  new AbstractMap.SimpleEntry<>(1, "2"),
							  new AbstractMap.SimpleEntry<>(1, "3"),
							  new AbstractMap.SimpleEntry<>(2, "5"),
							  new AbstractMap.SimpleEntry<>(2, "6"));
	}
}