package com.vanniktech.rxriddles.operators.multiplestreams;

import com.vanniktech.rxriddles.solutions.operators.multiplestreams.Riddle10Solution;
import com.vanniktech.rxriddles.util.MapUtil;
import io.reactivex.rxjava3.core.Observable;
import org.junit.Test;

public class Riddle10Test {
	@Test
	public void solve() {
		Observable<Integer> first = Observable.just(1, 2);
		Riddle10Solution.solve(first, Riddle10Test::create)
						.test()
						.assertResult(MapUtil.entry(1, "1"),
							  MapUtil.entry(1, "2"),
							  MapUtil.entry(1, "3"),
							  MapUtil.entry(2, "5"),
							  MapUtil.entry(2, "6")
				);
	}

	private static Observable<String> create(Integer it) {
		if (it == 1) {
			return Observable.just("1", "2", "3");
		} else {
			return Observable.just("5", "6");
		}
	}
}
