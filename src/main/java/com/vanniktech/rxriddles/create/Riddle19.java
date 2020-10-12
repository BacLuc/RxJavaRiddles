package com.vanniktech.rxriddles.create;

import java.util.function.Consumer;

import io.reactivex.rxjava3.core.Observable;

class Riddle19 {
	/**
	 * Use the given [Interaction] interface and use its listener to transform the [Int] callback to an Observable that emits every time the listener is called.
	 * When the Observable is being disposed the listener should be set to null.
	 * <p>
	 * Use case: Transform any listener into an Observable.
	 */
	public static Observable<Integer> solve(Interaction interaction) {
		//TODO()
		return null;
	}

	interface Interaction {
		void addListener(Consumer<Integer> listener);

		void removeListener(Consumer<Integer> listener);
	}
}
