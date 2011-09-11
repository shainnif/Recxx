package org.recxx.exception;

public class PropertiesFileException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PropertiesFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public PropertiesFileException(String message) {
		super(message);
	}
}
