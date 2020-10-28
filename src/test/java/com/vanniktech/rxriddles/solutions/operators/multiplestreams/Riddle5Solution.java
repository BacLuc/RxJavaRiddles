package com.vanniktech.rxriddles.solutions.operators.multiplestreams;

import io.reactivex.rxjava3.core.Observable;

public class Riddle5Solution {
	/**
	 * Sum up the latest values of [first] and [second] whenever one of the Observables emits a new value.
	 * <p>
	 * Use case: Two input fields in a calculator that need to be summed up and the result should be updated every time one of the inputs change.
	 */
	public static Observable<Integer> solve(Observable<Integer> first, Observable<Integer> second) {
		return Observable.combineLatest(first, second, Integer::sum);
	}
}
