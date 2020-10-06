package com.vanniktech.rxriddles;

import org.junit.Test;

/** Solution [Riddle1Solution] */
class Riddle1Test {
  @Test
  public void solve() {
    Riddle1.solve(5)
        .test()
        .assertResult(5);
  }
}
