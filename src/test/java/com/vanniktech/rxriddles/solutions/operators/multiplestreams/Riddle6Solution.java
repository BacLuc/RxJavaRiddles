package com.vanniktech.rxriddles.solutions.operators.multiplestreams;

import java.util.AbstractMap;
import java.util.Map;

import io.reactivex.rxjava3.core.Single;

class Riddle6Solution {
	/**
	 * Execute both [first] and [second] Single's in parallel and provide both results as a pair.
	 * Assume both [first] and [second] will execute on a different thread already.
	 * This is a slightly simpler version of [Riddle102], where no schedulers are applied by default.
	 * <p>
	 * Use case: Execute two network requests in parallel and wait for each other and process the combined data.
	 */
	public static Single<Map.Entry<Integer, Integer>> solve(Single<Integer> first, Single<Integer> second) {
		return Single.zip(first, second, AbstractMap.SimpleEntry::new);
	}
}
