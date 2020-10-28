package com.vanniktech.rxriddles.solutions.operators.withscheduler;

import java.util.concurrent.TimeUnit;

import com.vanniktech.rxriddles.util.Unit;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;

public class Riddle11Solution {
	/**
	 * Let the first emission of the [source] within a time window of 300ms travel downstream but don't emit any other events until the next time window.
	 * <p>
	 * Use case: Handle the click of a button right away but prevent double clicking by not handling multiple click events within a given time window.
	 */
	public static Observable<Unit> solve(Observable<Unit> source, Scheduler scheduler) {
		return source.throttleFirst(300, TimeUnit.MILLISECONDS, scheduler);
	}
}
