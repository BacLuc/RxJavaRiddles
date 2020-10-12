package com.vanniktech.rxriddles.operators.withscheduler;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;

class Riddle36 {
	/**
	 * Return an Observable that only emits items from [source] if there isn't another emission before [milliseconds] has passed.
	 * <p>
	 * Use case: You want the user-input to trigger a search request for the entered text but only when no changes have been made for a pre-determined time to avoid unnecessary requests.
	 */
	public static Observable<String> solve(Observable<String> source, Long milliseconds, Scheduler scheduler) {
		//TODO()
		return null;
	}
}
