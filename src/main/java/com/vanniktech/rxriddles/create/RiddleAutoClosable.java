package com.vanniktech.rxriddles.create;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Supplier;

public class RiddleAutoClosable {
	public static Completable solve(Supplier<? extends ThrowingResource> throwingResourceSupplier) {
		return Completable.using(throwingResourceSupplier,
								 throwingResource -> Completable.fromAction(() -> throwingResource.doSomeThing()),
								 AutoCloseable::close);
	}

	public interface ThrowingResource extends AutoCloseable {
		void doSomeThing() throws Exception;
	}
}
