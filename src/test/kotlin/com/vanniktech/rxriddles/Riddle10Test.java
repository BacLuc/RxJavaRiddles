package com.vanniktech.rxriddles;

import com.vanniktech.rxriddles.solutions.Riddle10Solution
import io.reactivex.rxjava3.core.Observable;
import org.junit.Test;

/** Solution [Riddle10Solution] */
class Riddle10Test {
  @Test public void solve() {
    val first = Observable.just(1, 2)
    val public voidction: (Int) -> Observable<String> = {
      if (it == 1) {
        Observable.just("1", "2", "3")
      } else {
        Observable.just("5", "6")
      }
    }

    Riddle10.solve(first, public voidction)
        .test()
        .assertResult(
            1 to "1",
            1 to "2",
            1 to "3",
            2 to "5",
            2 to "6"
        )
  }
}
