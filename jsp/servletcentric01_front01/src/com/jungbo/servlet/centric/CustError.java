package com.jungbo.servlet.centric;

import java.io.Serializable;

public class CustError implements Serializable {

	private static final long serialVersionUID = -7365995081398894078L;
	private String errorMessage;
	private String errorType;

	public CustError(String errorMessage, String errorType) {
		this.errorMessage = errorMessage;
		this.errorType = errorType;
	}
	public CustError() {
		
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
}//
