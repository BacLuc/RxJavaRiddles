package com.vanniktech.rxriddles.solutions.operators.withscheduler;

import java.util.concurrent.TimeUnit;

import com.vanniktech.rxriddles.util.Unit;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;

public class Riddle8Solution {
	/**
	 * Delay the entire [source] by 200ms. This includes subscribing, emissions and terminal events.
	 * <p>
	 * Use case: Make an Observable "lazy" for some time. For instance, when wanting to postpone some UI action.
	 */
	public static Observable<Unit> solve(Observable<Unit> source, Scheduler scheduler) {
		return source.delaySubscription(200, TimeUnit.MILLISECONDS, scheduler);
	}
}
