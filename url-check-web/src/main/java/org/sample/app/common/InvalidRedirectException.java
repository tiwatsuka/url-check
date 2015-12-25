package org.sample.app.common;

public class InvalidRedirectException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidRedirectException(String message) {
		super(message);
	}	
}
