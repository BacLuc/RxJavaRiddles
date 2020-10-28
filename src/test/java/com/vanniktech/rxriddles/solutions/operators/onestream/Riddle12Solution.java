package com.vanniktech.rxriddles.solutions.operators.onestream;

import io.reactivex.rxjava3.core.Observable;

public class Riddle12Solution {
	/**
	 * In case the [source] Observable emits an error, don't emit the error and instead complete the Observable with a value of 5.
	 * <p>
	 * Use case: Getting a network error and you want to recover and show some default state.
	 */
	public static Observable<Integer> solve(Observable<Integer> source) {
		return source.onErrorReturnItem(5);
	}
}
