package com.vanniktech.rxriddles;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

class Riddle35 {
	/**
	 * Return a Single that emits the value from the [first] source if present, otherwise emit from the [second] source.
	 * <p>
	 * Use case: You have a local cache and only want to hit the network if the cache misses.
	 */
	public static Single<String> solve(Maybe<String> first, Single<String> second) {
		//TODO()
		return null;
	}
}
