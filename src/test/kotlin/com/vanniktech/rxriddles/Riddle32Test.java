package com.vanniktech.rxriddles;

import com.vanniktech.rxriddles.solutions.Riddle32Solution
import com.vanniktech.rxriddles.tools.RxRule
import io.reactivex.rxjava3.core.Single
import org.junit.Rule
import org.junit.Test;
import java.util.concurrent.TimeUnit.SECONDS
import java.util.concurrent.TimeoutException

/** Solution [Riddle32Solution] */
class Riddle32Test {
  @get:Rule val rxRule = RxRule()

  @Test public void success() {
    Riddle32.solve(Single.just(1))
        .test()
        .assertResult(1)
  }

  @Test public void error() {
    Riddle32.solve(Single.error(RuntimeException()))
        .test()
        .assertFailure(RuntimeException::class.java)
  }

  @Test public void needs2Seconds() {
    val o = Riddle32.solve(Single.timer(2, SECONDS))
        .test()

    rxRule.advanceTimeBy(2, SECONDS)
    o.assertResult(0L)
  }

  @Test public void needs3Seconds() {
    val o = Riddle32.solve(Single.timer(3, SECONDS))
        .test()

    rxRule.advanceTimeBy(3, SECONDS)
    o.assertFailure(TimeoutException::class.java)
  }
}
