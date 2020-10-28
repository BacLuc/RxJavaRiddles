package com.vanniktech.rxriddles.solutions.operators.multiplestreams;

import com.vanniktech.rxriddles.util.Unit;
import io.reactivex.rxjava3.core.Observable;

public class Riddle9Solution {
	/**
	 * As long as the [trigger] Observable does not emit an item, keep the [main] Observable alive.
	 * <p>
	 * Use case: Cancel an Observable when something has happened. For instance, stop polling when the user has been logged out.
	 */
	public static Observable<Unit> solve(Observable<Unit> main, Observable<Unit> trigger) {
		return main.takeUntil(trigger);
	}
}
