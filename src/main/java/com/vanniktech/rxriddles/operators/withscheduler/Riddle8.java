package com.vanniktech.rxriddles.operators.withscheduler;

import com.vanniktech.rxriddles.util.Unit;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;

class Riddle8 {
	/**
	 * Delay the entire [source] by 200ms. This includes subscribing, emissions and terminal events.
	 * <p>
	 * Use case: Make an Observable "lazy" for some time. For instance, when wanting to postpone some UI action.
	 */
	public static Observable<Unit> solve(Observable<Unit> source, Scheduler scheduler) {
		//TODO()
		return null;
	}
}
