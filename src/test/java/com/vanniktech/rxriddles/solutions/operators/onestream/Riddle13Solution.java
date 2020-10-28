package com.vanniktech.rxriddles.solutions.operators.onestream;

import io.reactivex.rxjava3.core.Observable;

public class Riddle13Solution {
	/**
	 * When the [source] emits the same value as it did last time, don't allow it to travel downstream.
	 * <p>
	 * Use case: You only want to observe changes of a value but don't care if the same value has been emitted consecutively.
	 */
	public static Observable<Integer> solve(Observable<Integer> source) {
		return source.distinctUntilChanged();
	}
}
