package com.vanniktech.rxriddles.hotobservable;

import java.util.concurrent.Callable;

import com.vanniktech.rxriddles.util.Unit;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;

public class RiddleHotObservable {

	static final long PERIOD = 100L;

	public static Observable<Unit> solve(Callable<Unit> callable, Scheduler scheduler) {
		return null;
	}
}
