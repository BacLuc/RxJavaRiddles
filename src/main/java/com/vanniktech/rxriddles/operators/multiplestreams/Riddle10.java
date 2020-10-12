package com.vanniktech.rxriddles.operators.multiplestreams;

import io.reactivex.rxjava3.core.Observable;

import java.util.Map;
import java.util.function.Function;

class Riddle10 {
	/**
	 * Use the [first] Observable and flatten it with the results of the [function] that returns an Observable.
	 * <p>
	 * Use case: Get some user data and perform a network request with the user data and have both data accessible afterwards.
	 */
	public static Observable<Map.Entry<Integer, String>> solve(Observable<Integer> first, Function<Integer, Observable<String>> observableFactory) {
		//TODO()
		return null;
	}
}
