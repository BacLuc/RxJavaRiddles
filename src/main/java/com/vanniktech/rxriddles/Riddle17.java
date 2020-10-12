package com.vanniktech.rxriddles;

import io.reactivex.rxjava3.core.Single;

import java.util.function.Supplier;

class Riddle17 {
	/**
	 * Return a Single that emits the value from the given [function] when being subscribed to.
	 * <p>
	 * Use case: Reactive types are lazy by default. Hence you might also want to get the value upon the subscription and not execution time.
	 */
	public static Single<Integer> solve(Supplier<Integer> integerSupplier) {
		//TODO()
		return null;
	}
}
