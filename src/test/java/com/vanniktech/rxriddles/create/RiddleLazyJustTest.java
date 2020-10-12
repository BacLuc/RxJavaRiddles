package com.vanniktech.rxriddles.create;

import org.junit.Test;

public class RiddleLazyJustTest {
	@Test
	public void emits_values_from_to() {
		RiddleLazyJust.solve(0, 0).test().assertNoValues().assertComplete();
		RiddleLazyJust.solve(0, 1).test().assertValues(0).assertComplete();
		RiddleLazyJust.solve(0, 5).test().assertValues(0, 1, 2, 3, 4).assertComplete();
	}
}