package org.alalgo.exception;

public class BusinessException extends RuntimeException {
	public BusinessException() {
		super();
	}	
	public BusinessException(String msg) {
		super(msg);
	}
	public BusinessException(String msg,Throwable throwable) {
		super(msg,throwable);
	}	
}
