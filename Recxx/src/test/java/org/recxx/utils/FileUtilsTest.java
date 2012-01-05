package org.recxx.utils;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class FileUtilsTest {

	private static final String FILE_NAME = System.getProperty("user.dir") 
												+ System.getProperty("file.separator") 
												+ "test.txt";

	private static final File FILE = new File(FILE_NAME);
	
	private static final String TEST_DATA = "Sample data" + System.getProperty("line.separator")
												+ "Row 1" + System.getProperty("line.separator")
												+ "Row 2" + System.getProperty("line.separator");

	@Test
	public void testWritingFile() {
		try {
			FileUtils.writeFile(TEST_DATA, FILE);
			if (FILE.exists()) FILE.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReadingFileAsString() {
		try {
			FileUtils.writeFile(TEST_DATA, FILE);
			assertEquals(TEST_DATA, FileUtils.readFileAsString(FILE));
			if (FILE.exists()) FILE.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
