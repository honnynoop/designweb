package com.mirhenge.jyl.exception;

import org.springframework.dao.DataAccessException;

public class DuplicatedIDException extends DataAccessException {

	public DuplicatedIDException(String msg) {
		super(msg);
	}

	public DuplicatedIDException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
