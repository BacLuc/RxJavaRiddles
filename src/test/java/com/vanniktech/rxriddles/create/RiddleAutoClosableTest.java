package com.vanniktech.rxriddles.create;

import com.vanniktech.rxriddles.create.RiddleAutoClosable.ThrowingResource;
import org.junit.Test;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RiddleAutoClosableTest {
	@Test
	public void always_closes_resource() throws Exception {
		ThrowingResource throwingResource = mock(ThrowingResource.class);

		RiddleAutoClosable.solve(() -> throwingResource).test().assertComplete();

		doThrow(Exception.class).when(throwingResource).doSomeThing();
		RiddleAutoClosable.solve(() -> throwingResource).test().assertError(Exception.class);

		verify(throwingResource, times(2)).close();
	}
}