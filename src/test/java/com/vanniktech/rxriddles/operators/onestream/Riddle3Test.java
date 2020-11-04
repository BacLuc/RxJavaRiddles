package com.vanniktech.rxriddles.operators.onestream;

import io.reactivex.rxjava3.core.Observable;
import junit.framework.TestCase;
import org.junit.Test;

public class Riddle3Test extends TestCase {

	@Test
	public void test()  {
		Riddle3.solve(Observable.range(0, 10))
			   .test()
			   .assertResult(0, 2, 4, 6, 8);
	}

}