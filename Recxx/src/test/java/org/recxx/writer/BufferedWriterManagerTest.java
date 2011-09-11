package org.recxx.writer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.recxx.utils.CloseableUtils;

@RunWith(MockitoJUnitRunner.class)
public class BufferedWriterManagerTest {

	private final TestFileManager testFileManager = new TestFileManager();

	private BufferedWriterManager bufferedWriterManager;

	@Mock
	private CloseableUtils closeableUtilsMock;

	@After
	public void tearDown() throws Exception {
		testFileManager.deleteTestFile();
	}

	private void givenBufferedWriterManagerIsCreated() {
		bufferedWriterManager = new BufferedWriterManager(closeableUtilsMock);
	}

	@Test
	public void openShouldCreateBufferedWriter() throws Exception {
		givenBufferedWriterManagerIsCreated();
		File testFile = testFileManager.getTestFile();
		BufferedWriter bufferedWriter = bufferedWriterManager.open(testFile);
		assertThat(bufferedWriter, is(not(nullValue())));
	}

	@Test
	public void closeShouldCloseWriters() throws Exception {
		givenBufferedWriterManagerIsCreated();
		File testFile = testFileManager.getTestFile();
		bufferedWriterManager.open(testFile);
		bufferedWriterManager.close();
	}

	@Test
	public void closeShouldThrowExceptionWhenWritersThrowExceptionDuringClose() throws Exception {
		givenBufferedWriterManagerIsCreated();
		File testFile = testFileManager.getTestFile();
		BufferedWriter bufferedWriter = bufferedWriterManager.open(testFile);
		IOException testException = new IOException("Test Exception");
		when(closeableUtilsMock.tryToClose(bufferedWriter)).thenReturn(testException);
		try {
			bufferedWriterManager.close();
			fail("Should have thrown IOException.");
		} catch (IOException exception) {
			assertThat(exception, is(testException));
		}
	}
}