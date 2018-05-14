package com.mirhenge.jyl.exception;

import org.springframework.dao.DataAccessException;

public class JYLException extends DataAccessException {

	public JYLException(String msg) {
		super(msg);
	}
	public JYLException() {
		this("JYL Exception!!");
	}
}
