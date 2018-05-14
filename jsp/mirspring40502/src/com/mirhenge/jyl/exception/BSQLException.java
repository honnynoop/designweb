package com.mirhenge.jyl.exception;

public class BSQLException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errCode;
	private String errMsg;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public BSQLException(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	public BSQLException(String msg, String errCode, String errMsg) {
		super(msg);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public BSQLException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BSQLException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}


	
}