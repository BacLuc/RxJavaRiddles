package com.vanniktech.rxriddles;

import com.vanniktech.rxriddles.solutions.Riddle25Solution
import io.reactivex.rxjava3.core.Observable;
import org.junit.Test;

/** Solution [Riddle25Solution] */
class Riddle25Test {
  @Test public void empty() {
    Riddle25.solve(Observable.empty<Int>())
        .test()
        .assertResult(5)
  }

  @Test public void nonEmpty() {
    Riddle25.solve(Observable.just(1, 3))
        .test()
        .assertResult(1, 3)
  }
}
