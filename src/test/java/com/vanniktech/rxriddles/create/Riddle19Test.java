package com.vanniktech.rxriddles.create;

import java.util.function.Consumer;
import java.util.function.Function;

import com.vanniktech.rxriddles.util.Unit;
import io.reactivex.rxjava3.observers.TestObserver;
import org.junit.Test;

import static org.junit.Assert.*;

public class Riddle19Test {

	@Test
	public void solve() {

		TestInteraction testInteraction = new TestInteraction();

		TestObserver<Integer> o = Riddle19.solve(testInteraction)
										  .test()
										  .assertEmpty();

		assertNotNull(testInteraction.listener);

		testInteraction.listener.accept(5);
		o.assertValuesOnly(5);

		testInteraction.listener.accept(10);
		o.assertValuesOnly(5, 10);

		o.dispose();
		assertNull(testInteraction.listener);
	}

	public class TestInteraction implements Riddle19.Interaction {

		public Consumer<Integer> listener = null;

		@Override
		public void addListener(Consumer<Integer> listener) {
			this.listener = listener;
		}

		@Override
		public void removeListener(Consumer<Integer> listener) {
			this.listener = null;
		}
	}

}