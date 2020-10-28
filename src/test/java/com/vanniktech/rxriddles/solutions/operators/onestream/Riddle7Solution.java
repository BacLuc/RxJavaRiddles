package com.vanniktech.rxriddles.solutions.operators.onestream;

import io.reactivex.rxjava3.core.Observable;

public class Riddle7Solution {
	/**
	 * When the [source] emits the same value multiple times, only allow the first value to travel downstream.
	 * <p>
	 * Use case: You never want to show the same value twice.
	 */
	public static Observable<Integer> solve(Observable<Integer> source) {
		return source.distinct();
	}
}
