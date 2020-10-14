package com.vanniktech.rxriddles.operators.multiplestreams;

import java.util.Map;

import io.reactivex.rxjava3.core.Single;

class Riddle102 {
	/**
	 * Execute both [first] and [second] Single's in parallel and provide both results as a pair.
	 * <p>
	 * Use case: Execute two network requests in parallel and wait for each other and process the combined data.
	 */
	public static Single<Map.Entry<Integer, Integer>> solve(Single<Integer> first, Single<Integer> second) {
		//TODO()
		return null;
	}
}
