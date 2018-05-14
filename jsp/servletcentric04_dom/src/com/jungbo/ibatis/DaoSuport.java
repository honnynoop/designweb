package com.jungbo.ibatis;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public abstract class DaoSuport {
	protected abstract Connection getConnection() throws SQLException;
	protected void close(Connection conn, Statement psmt, ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		if(psmt!=null){
			try {
				psmt.close();
			} catch (SQLException e) {
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}//
}
