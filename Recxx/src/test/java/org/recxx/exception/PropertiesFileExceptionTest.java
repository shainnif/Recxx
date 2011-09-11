package org.recxx.exception;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PropertiesFileExceptionTest {

	@Test
	public void constructorShouldSetMessage() throws Exception {
		String inputMessage = "Test Message";
		PropertiesFileException exception = new PropertiesFileException(inputMessage);
		assertThat(exception.getMessage(), is(inputMessage));
	}

	@Test
	public void constructorShouldSetMessageAndCause() throws Exception {
		String inputMessage = "Test Message";
		Throwable inputCause = new Exception("Test Cause");
		PropertiesFileException exception = new PropertiesFileException(inputMessage, inputCause);
		assertThat(exception.getMessage(), is(inputMessage));
		assertThat(exception.getCause(), is(inputCause));
	}
}