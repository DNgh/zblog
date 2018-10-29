package com.min.zblog.facility.exception;

public class ProcessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	//错误码
	public String errorCode;

	public ProcessException() {

	}

	public ProcessException(String message) {
		super(message);
	}
	
	public ProcessException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public ProcessException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	
}
