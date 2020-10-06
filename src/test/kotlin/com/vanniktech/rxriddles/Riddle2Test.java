package com.vanniktech.rxriddles;

import io.reactivex.rxjava3.core.Observable;
import org.junit.Test;

/** Solution [Riddle2Solution] */
class Riddle2Test {
  @Test public void solve() {
    Riddle2.solve(Observable.just(-1, 0, 5))
        .test()
        .assertResult(0, 1, 6);
  }
}
