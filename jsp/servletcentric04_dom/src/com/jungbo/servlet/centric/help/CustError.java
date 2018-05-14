package com.jungbo.servlet.centric.help;

import java.io.Serializable;

public class CustError implements Serializable {

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
