package com.vanniktech.rxriddles.solutions.operators.onestream;

import com.vanniktech.rxriddles.util.Unit;
import io.reactivex.rxjava3.core.Observable;

public class Riddle4Solution {
	/**
	 * Implement a toggle mechanism. Initially we want to return false.
	 * Every time [source] emits, we want to negate the previous value.
	 * <p>
	 * Use case: Some button that can toggle two states. For instance a switch between White & Dark theme.
	 */
	public static Observable<Boolean> solve(Observable<Unit> source) {
		return source.scan(false, (aBoolean, unit) -> !aBoolean);
	}
}
