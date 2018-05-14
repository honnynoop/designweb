package com.hk.mobile.exception;

import org.springframework.dao.DataAccessException;

public class IdNotFoundException extends DataAccessException {

	public IdNotFoundException(String msg) {
		super(msg);
	}

	public IdNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
