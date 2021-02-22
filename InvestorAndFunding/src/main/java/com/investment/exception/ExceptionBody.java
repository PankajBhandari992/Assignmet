package com.investment.exception;

/**
 * @author pankaj
 *
 */
public class ExceptionBody {
	private String message;

	
	
	public ExceptionBody(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
