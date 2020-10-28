package com.vanniktech.rxriddles.solutions.operators.onestream;

import com.vanniktech.rxriddles.util.Unit;
import io.reactivex.rxjava3.core.Single;

public class Riddle14Solution {
	/**
	 * Try the given [source] up to three times unless an [IllegalArgumentException] has been emitted.
	 * <p>
	 * Use case: Retry an operation for a number of times or until a valid error occurred.
	 */
	public static Single<Unit> solve(Single<Unit> source) {
		return source.retry(2, throwable -> !(throwable instanceof IllegalArgumentException));
	}
}
