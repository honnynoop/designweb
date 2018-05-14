package com.hk.mobile.exception;

import org.springframework.dao.DataAccessException;

public class MyException extends DataAccessException {



	public MyException(String msg, String errCode, String errMsg) {
		super(msg);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public MyException( String errCode, String errMsg) {
		this("",errCode,errMsg);
	}


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
}
