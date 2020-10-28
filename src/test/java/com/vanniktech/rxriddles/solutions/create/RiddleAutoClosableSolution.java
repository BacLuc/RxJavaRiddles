package com.vanniktech.rxriddles.solutions.create;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Supplier;

public class RiddleAutoClosableSolution {
	public static Completable solve(Supplier<? extends ThrowingResource> throwingResourceSupplier) {
		//The compiler does not like Completable.fromAction(throwingResource::doSomeThing)
		//noinspection Convert2MethodRef
		return Completable.using(throwingResourceSupplier,
								 throwingResource -> Completable.fromAction(() -> throwingResource.doSomeThing()),
								 AutoCloseable::close);
	}

	public interface ThrowingResource extends AutoCloseable {
		void doSomeThing() throws Exception;
	}
}
