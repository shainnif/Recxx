package org.recxx.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;

import org.recxx.utils.CloseableUtils;

public class BufferedWriterManager {

	private FileWriter fileWriter;

	private BufferedWriter bufferedWriter;

	private final CloseableUtils closeableUtils;

	public BufferedWriterManager(CloseableUtils closeableUtils) {
		this.closeableUtils = closeableUtils;
	}

	public BufferedWriter open(File file) throws IOException {
		fileWriter = new FileWriter(file);
		bufferedWriter = new BufferedWriter(fileWriter);
		return bufferedWriter;
	}

	public void close() throws IOException {
		LinkedList<IOException> exceptions = new LinkedList<IOException>();
		tryToCloseWriter(bufferedWriter, exceptions);
		tryToCloseWriter(fileWriter, exceptions);
		if (!exceptions.isEmpty()) {
			throw exceptions.pop();
		}
	}

	private void tryToCloseWriter(Writer writer, LinkedList<IOException> exceptions) {
		IOException exception = closeableUtils.tryToClose(writer);
		if (exception != null) {
			exceptions.push(exception);
		}
	}
}