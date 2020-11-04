package com.vanniktech.rxriddles.operators.onestream;

import io.reactivex.rxjava3.core.Observable;
import org.junit.Test;

import static org.junit.Assert.*;

public class Riddle7Test {

	@Test
	public void test() {
		Riddle7.solve(Observable.just(1, 2, 3, 1, 2, 4, 4, 5, 4))
			   .test()
			   .assertResult(1, 2, 3, 4, 5);
	}

}