package com.vanniktech.rxriddles.solutions.hotobservable;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import com.vanniktech.rxriddles.util.Unit;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;

public class HotObservableRiddleSolution {

	static final long PERIOD = 100L;

	public static Observable<Unit> solve(Callable<Unit> callable, Scheduler scheduler) {
		return Observable.interval(PERIOD, TimeUnit.MILLISECONDS, scheduler).map(__ -> callable.call()).subscribeOn(scheduler).publish().autoConnect();
	}
}
