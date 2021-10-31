package com.github.hmld.common.exception;
/**
 * 必填项错误
 * @author hmld
 *
 */
public class EnityRequiredException extends Exception{

	private static final long serialVersionUID = 1L;

	public EnityRequiredException() {
		super();
	}

	public EnityRequiredException(String message, Throwable cause, boolean enableSuppression,
	    boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EnityRequiredException(String message, Throwable cause) {
		super(message, cause);
	}

	public EnityRequiredException(String message) {
		super(message);
	}

	public EnityRequiredException(Throwable cause) {
		super(cause);
	}

}
