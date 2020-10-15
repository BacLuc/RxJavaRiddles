package com.vanniktech.rxriddles.operators.withscheduler;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableTransformer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.reactivestreams.Publisher;

public class RiddleRxRepeat<T> implements FlowableTransformer<T, T> {

	public static <T> RiddleRxRepeat<T> create(Duration interval) {
		return new RiddleRxRepeat<>(interval, Schedulers.io());
	}

	public static <T> RiddleRxRepeat<T> create(Duration interval, Scheduler scheduler) {
		return new RiddleRxRepeat<>(interval, scheduler);
	}

	private final Duration interval;
	private final Scheduler scheduler;

	private RiddleRxRepeat(Duration interval, Scheduler scheduler) {
		this.interval = interval;
		this.scheduler = scheduler;
	}

	@NonNull
	@Override
	public Publisher<T> apply(@NonNull Flowable<T> flowable) {
		return Flowable.empty();
	}
}
