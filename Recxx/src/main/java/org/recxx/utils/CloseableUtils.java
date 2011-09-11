package org.recxx.utils;

import java.io.Closeable;
import java.io.IOException;

public class CloseableUtils {

	public IOException tryToClose(Closeable closeable) {
		try {
			closeable.close();
		} catch (IOException exception) {
			return exception;
		}
		return null;
	}
}