package com.vanniktech.rxriddles.solutions.operators.withscheduler;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableTransformer;
import org.reactivestreams.Publisher;

public class RiddleRxCacheSolution<T> implements FlowableTransformer<T,T> {
	/**
	 * Return the last emitted value when a new Observer subscribes
	 *
	 * Use case: we want to cache the last value for a monitoring system.
	 */
	@Override
	public @NonNull Publisher<T> apply(@NonNull Flowable<T> upstream) {
		return upstream.replay(1).refCount();
	}
}
