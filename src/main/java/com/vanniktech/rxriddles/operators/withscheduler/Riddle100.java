package com.vanniktech.rxriddles.operators.withscheduler;

import com.vanniktech.rxriddles.util.Unit;
import io.reactivex.rxjava3.core.Observable;

class Riddle100 {
	/**
	 * If the [source] emits more than once within 300ms we want to emit [Unit].
	 * If there is only one or non emissions within 300ms we don't want to emit anything.
	 * <p>
	 * Use case: Double click detection mechanism for a button.
	 */
	public static Observable<Unit> solve(Observable<Unit> source) {
		//TODO()
		return null;
	}
}
