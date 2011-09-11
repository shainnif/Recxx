package org.recxx.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.io.Closeable;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CloseableUtilsTest {

	@Mock
	private Closeable closeableMock;

	private final CloseableUtils closeableUtils = new CloseableUtils();

	@Test
	public void tryToCloseShouldCloseTheCloseable() throws Exception {
		IOException exceptionThrownWhenClosing = closeableUtils.tryToClose(closeableMock);
		verify(closeableMock).close();
		verifyNoMoreInteractions(closeableMock);
		assertThat(exceptionThrownWhenClosing, is(nullValue()));
	}

	@Test
	public void tryToCloseShouldReturnExceptionThrownFromCloseOfCloseable() throws Exception {
		IOException testException = new IOException("Test exception");
		doThrow(testException).when(closeableMock).close();
		IOException exceptionThrownWhenClosing = closeableUtils.tryToClose(closeableMock);
		verify(closeableMock).close();
		verifyNoMoreInteractions(closeableMock);
		assertThat(exceptionThrownWhenClosing, is(testException));
	}
}