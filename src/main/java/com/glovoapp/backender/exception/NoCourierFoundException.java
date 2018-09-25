package com.glovoapp.backender.exception;

public class NoCourierFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6949862479050655029L;

	public NoCourierFoundException(String message) {
		super(message);
	}

	public NoCourierFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
