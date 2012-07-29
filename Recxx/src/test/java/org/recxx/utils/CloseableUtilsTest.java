package org.recxx.utils;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.Closeable;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CloseableUtilsTest {

	@Mock
	private Closeable closeableMock;

	private final CloseableUtils closeableUtils = new CloseableUtils();

	@org.junit.Test
	public void tryToCloseShouldCloseTheCloseable() throws Exception {
		IOException exceptionThrownWhenClosing = closeableUtils.tryToClose(closeableMock);
		verify(closeableMock).close();
		verifyNoMoreInteractions(closeableMock);
		assertThat(exceptionThrownWhenClosing, is(nullValue()));
	}

	@org.junit.Test
	public void tryToCloseShouldReturnExceptionThrownFromCloseOfCloseable() throws Exception {
		IOException testException = new IOException("Test exception");
		doThrow(testException).when(closeableMock).close();
		IOException exceptionThrownWhenClosing = closeableUtils.tryToClose(closeableMock);
		verify(closeableMock).close();
		verifyNoMoreInteractions(closeableMock);
		assertThat(exceptionThrownWhenClosing, is(testException));
	}
}