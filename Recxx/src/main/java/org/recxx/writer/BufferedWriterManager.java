package org.recxx.writer;

import org.recxx.utils.CloseableUtils;

import java.io.*;
import java.util.LinkedList;

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