package com.vanniktech.rxriddles.solutions.operators.multiplestreams;

import java.util.AbstractMap;
import java.util.Map;
import java.util.function.Function;

import io.reactivex.rxjava3.core.Observable;

public class Riddle10Solution {
	/**
	 * Use the [first] Observable and flatten it with the results of the [function] that returns an Observable.
	 * <p>
	 * Use case: Get some user data and perform a network request with the user data and have both data accessible afterwards.
	 */
	public static Observable<Map.Entry<Integer, String>> solve(Observable<Integer> first, Function<Integer, Observable<String>> observableFactory) {
		return first.flatMap(observableFactory::apply, AbstractMap.SimpleEntry::new);
		// long version:
		//return first.flatMap(integer -> observableFactory.apply(integer).map(s -> new AbstractMap.SimpleEntry<>(integer, s)));
	}
}
