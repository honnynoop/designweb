package com.jungbo.ibatis;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
public abstract class DataSourceDaoSuport extends DaoSuport{
	private DataSource dataSource;
	public DataSourceDaoSuport(String path){
	    init(path);
	}
	public DataSourceDaoSuport(){
	    this("jdbc/servletcentric04_comp01");
	}
	protected void init(String path){
		InitialContext ict=null;
	    try {
			ict=new InitialContext();
			dataSource = (DataSource) ict.lookup("java:/comp/env/"+path);
		} catch (NamingException e) {
		}
	}
	public Connection getConnection() throws SQLException{
		Connection conn=null;
		try {
			conn= dataSource.getConnection();
		} catch (SQLException e) {
			System.out.println("2/6 연결 실패 SqlDaoSuport : check!");
			throw e;
		}
		return conn;
	}
}
/*
<Resource 
driverClassName="oracle.jdbc.driver.OracleDriver" 
maxActive="4" maxIdle="2" maxWait="5000" 
name="jdbc/Oracle" password="jungbo" 
type="javax.sql.DataSource" 
url="jdbc:oracle:thin:@127.0.0.1:1521:xe" 
username="hr"/>

<Context docBase="servletcentric04_comp01" path="/servletcentric04_comp01" reloadable="true" source="org.eclipse.jst.jee.server:/servletcentric04_comp01">
 <ResourceLink global="jdbc/Oracle" name="jdbc/servletcentric04_comp01" type="javax.sql.DataSource"/>
</Context>
*/