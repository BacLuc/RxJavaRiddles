package com.vanniktech.rxriddles.create;

import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.rxjava3.core.Single;
import org.junit.Test;

import static org.junit.Assert.*;

public class Riddle17Test {

	@Test
	public void test() {
		AtomicInteger value = new AtomicInteger(1);
		Single<Integer> s = Riddle17.solve(() -> value.get() );

		value.set(3);

		s.test().assertResult(3);
	}
}