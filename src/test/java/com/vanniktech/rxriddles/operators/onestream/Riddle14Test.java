package com.vanniktech.rxriddles.operators.onestream;

import java.util.concurrent.atomic.AtomicInteger;

import com.vanniktech.rxriddles.util.Unit;
import io.reactivex.rxjava3.core.Single;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Riddle14Test {

	private Unit unit = Unit.create();

	@Test
	public void success() {
		Riddle14.solve(Single.just(unit)).test().assertResult(unit);
	}

	@Test
	public void retriesOnlyThreeTimes() {
		AtomicInteger subscribeCounter = new AtomicInteger(0);
		Riddle14.solve(Single.fromCallable(() -> {
			subscribeCounter.getAndIncrement();
			throw new UnsupportedOperationException();
		})).test().assertFailure(UnsupportedOperationException.class);

		assertEquals(subscribeCounter.get(), 3);
	}

	@Test
	public void valueAtSecond() {
		AtomicInteger subscribeCounter = new AtomicInteger(0);
		Riddle14.solve(Single.fromCallable(() -> {
			if (subscribeCounter.getAndIncrement() == 1) {
				return unit;
			} else {
				throw new UnsupportedOperationException();
			}
		})).test().assertResult(unit);

		assertEquals(subscribeCounter.get(), 2);
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

		assertEquals(subscribeCounter.get(), 2);
	}

}