package com.vanniktech.rxriddles.create;

import org.junit.Test;

/**
 * Solution [Riddle1Solution]
 */
public class Riddle1Test {
	@Test
	public void solve() {
		Riddle1.solve(5)
				.test()
				.assertResult(5);
	}
}
