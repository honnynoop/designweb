package com.jungbo.except;
public class CustUserSQLException extends Exception {
	private static final long serialVersionUID = -241370505609435644L;
	public CustUserSQLException() {
		super("DB 관련 처리시 예외 발생!!!");
	}
	public CustUserSQLException(String message) {
		super(message);
	}
}
/*
CREATE table "CUSTUSER" (
    "ID"         VARCHAR2(20) NOT NULL,
    "NAME"       VARCHAR2(20) NOT NULL,
    "ADDRESS"    VARCHAR2(100) NOT NULL,
    constraint  "CUSTUSER_PK" primary key ("ID")
)
*/