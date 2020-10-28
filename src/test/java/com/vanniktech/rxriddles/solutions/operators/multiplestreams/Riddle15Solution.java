package com.vanniktech.rxriddles.solutions.operators.multiplestreams;

import io.reactivex.rxjava3.core.Observable;

public class Riddle15Solution {
	/**
	 * Concatenate the [first] Observable with the [second] while subscribing to both early.
	 * <p>
	 * Use case: You have two sources of your data (cache & network request). You want to subscribe to both right away and keep the emission order.
	 */
	public static Observable<Integer> solve(Observable<Integer> first, Observable<Integer> second) {
		return first.concatWith(second);
	}
}
