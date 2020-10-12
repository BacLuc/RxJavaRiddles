package com.vanniktech.rxriddles.operators.withscheduler;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;

class Riddle32 {
	/**
	 * Signal a [TimeoutException] when the given [source] does not terminate within 3 seconds.
	 * <p>
	 * Use case: You want to terminate the given reactive type and stop the operation.
	 */
	public static Single<Long> solve(Single<Long> source, Scheduler scheduler) {
		//TODO()
		return null;
	}
}
