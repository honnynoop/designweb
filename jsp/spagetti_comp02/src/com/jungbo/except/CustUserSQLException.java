package com.jungbo.except;
//com.jungbo.except.CustUserSQLException
public class CustUserSQLException extends Exception {

	private static final long serialVersionUID = -1399056896641068330L;

	public CustUserSQLException() {
		super("DB ���� ó���� ���� �߻�!!");
	}

	public CustUserSQLException(String message) {
		super(message);
	}
}
