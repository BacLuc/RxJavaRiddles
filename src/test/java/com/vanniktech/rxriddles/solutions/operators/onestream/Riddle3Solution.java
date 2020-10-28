package com.vanniktech.rxriddles.solutions.operators.onestream;

import io.reactivex.rxjava3.core.Observable;

public class Riddle3Solution {
	/**
	 * Don't emit odd numbers from the [source] Observable.
	 * <p>
	 * Use case: You want to filter certain items out.
	 */
	public static Observable<Integer> solve(Observable<Integer> source) {
		return source.filter(integer -> integer % 2 == 0);
	}
}
