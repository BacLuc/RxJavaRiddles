package com.vanniktech.rxriddles.operators.onestream;

import java.util.concurrent.atomic.AtomicInteger;

import com.vanniktech.rxriddles.util.Unit;
import io.reactivex.rxjava3.core.Single;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Riddle14Test {

	@Test
	public void success() {
		final Unit unit = Unit.create();
		Riddle14.solve(Single.just(unit)).test().assertResult(unit);
	}

	@Test
	public void retriesOnlyThreeTimes() {
		AtomicInteger subscribeCounter = new AtomicInteger(0);
		Riddle14.solve(Single.fromCallable(() -> {
			subscribeCounter.getAndIncrement();
			throw new UnsupportedOperationException();
		})).test().assertFailure(UnsupportedOperationException.class);

		assertThat(subscribeCounter.get()).isEqualTo(3);
	}

	@Test
	public void valueAtSecond() {
		AtomicInteger subscribeCounter = new AtomicInteger(0);
		final Unit unit = Unit.create();
		Riddle14.solve(Single.fromCallable(() -> {
			if (subscribeCounter.getAndIncrement() == 1) {
				return unit;
			} else {
				throw new UnsupportedOperationException();
			}
		})).test().assertResult(unit);

		assertThat(subscribeCounter.get()).isEqualTo(2);
	}

	@Test
	public void stopsAtIllegalArgumentException() {
		AtomicInteger subscribeCounter = new AtomicInteger(0);
		Riddle14.solve(Single.fromCallable(() -> {
			if (subscribeCounter.getAndIncrement() == 1) {
				throw new IllegalArgumentException();
			} else {
				throw new UnsupportedOperationException();
			}
		})).test().assertFailure(IllegalArgumentException.class);

		assertThat(subscribeCounter.get()).isEqualTo(2);
	}
}
