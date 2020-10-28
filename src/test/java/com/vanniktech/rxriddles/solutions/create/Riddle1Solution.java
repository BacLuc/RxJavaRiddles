package com.vanniktech.rxriddles.solutions.create;

import io.reactivex.rxjava3.core.Observable;

public class Riddle1Solution {
	/**
	 * Transform the given [value] into an Observable that emits the value and then completes.
	 * <p>
	 * Use case: You want to transform some value to the reactive world.
	 */
	public static Observable<Integer> solve(Integer value) {
		return Observable.just(value);
	}
}
