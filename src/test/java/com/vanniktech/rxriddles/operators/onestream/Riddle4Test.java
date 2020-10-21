package com.vanniktech.rxriddles.operators.onestream;

import com.vanniktech.rxriddles.util.Unit;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.subjects.PublishSubject;
import junit.framework.TestCase;
import org.gradle.internal.impldep.org.jetbrains.annotations.TestOnly;
import org.junit.Test;

public class Riddle4Test extends TestCase {

	@Test
	public void test() {

		PublishSubject<Unit> subject  = PublishSubject.create();

		TestObserver<Boolean> o = Riddle4.solve(subject)
												  .test()
												  .assertValuesOnly(false);

		subject.onNext(Unit.create());
		o.assertValuesOnly(false, true);

		subject.onNext(Unit.create());
		o.assertValuesOnly(false, true, false);

		subject.onNext(Unit.create());
		o.assertValuesOnly(false, true, false, true);
	}

}