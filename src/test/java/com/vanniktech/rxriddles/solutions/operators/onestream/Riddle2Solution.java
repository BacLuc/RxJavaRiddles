package com.vanniktech.rxriddles.solutions.operators.onestream;

import io.reactivex.rxjava3.core.Observable;

public class Riddle2Solution {
	/**
	 * Increment each emitted value of the given [source] by 1.
	 * <p>
	 * Use case: You want to transform the data.
	 */
	public static Observable<Integer> solve(Observable<Integer> source) {
		return source.map(integer -> ++integer);
	}
}
