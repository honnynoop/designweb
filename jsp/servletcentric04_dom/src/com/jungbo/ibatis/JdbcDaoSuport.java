package com.jungbo.ibatis;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public abstract class JdbcDaoSuport extends DaoSuport {
	public JdbcDaoSuport() {
		init();
	}
	public JdbcDaoSuport(String driver) {
		init(driver);
	}
	protected void init(){
		init("oracle.jdbc.driver.OracleDriver");
	}
	protected void init(String driver){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("1/6 �ε� ����");
		}
	}
	public Connection getConnection() throws SQLException{
		Connection conn=null;
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user="hr";
		String passwd="jungbo";
		try {
			conn=DriverManager.getConnection(url,user,passwd);
		} catch (SQLException e) {
			System.out.println("2/6 ���� �ǽ���");
			throw e;
		}
		return conn;
	}//
}//
