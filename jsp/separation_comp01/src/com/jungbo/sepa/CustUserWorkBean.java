package com.jungbo.sepa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
public class CustUserWorkBean {
	private String id;
	private String[] ids;
	private String name;
	private String address;
	public CustUserWorkBean() {init();}//
	public CustUserWorkBean(String id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
		init();
	}//
	private void init(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("1/6 로딩 실패");
		}
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public String toString() {
		return "["+id+"/"+name+"/"+address+"]";
	}//
	//-------아래는 DAO-------------------
	public Connection getConnection() throws SQLException{
		Connection conn=null;
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user="hr";
		String passwd="jungbo";
		try {
			conn=DriverManager.getConnection(url,user,passwd);
		} catch (SQLException e) {
			System.out.println("2/6 연결 실실패");
			throw e;
		}
		return conn;
	}//
	private void close(Connection conn, Statement psmt, ResultSet rs){
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
	//----------------------------------------------------------------------
	//useBean의 멤버를 이용하여 데이터 insert
	public int addCustUser() throws SQLException{
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		int count=0;
		String sql="INSERT INTO CUSTUSER VALUES(?,?,?)";
		try{
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			//받은 파라메터 쿼리의 ?에 넣기 
			psmt.setString(1,id); 
			psmt.setString(2,name); 
			psmt.setString(3,address); 
			//insert 실행하기
			count=psmt.executeUpdate();
		}catch(SQLException se){
			System.out.println(se);
			throw se;
		}finally{
			System.out.println("-----finally----------");
			close(conn, psmt, rs);
		}
		return count;
	}//
	
	public int updateCustUser() throws SQLException{
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		int count=0;
		String sql="UPDATE CUSTUSER SET NAME=? , ADDRESS=? WHERE ID=?";
		try{
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			//받은 파라메터 쿼리의 ?에 넣기 
			psmt.setString(1,name.trim()); 
			psmt.setString(2,address.trim()); 
			psmt.setString(3,id.trim()); 
			//updateCustUser 실행하기
			count=psmt.executeUpdate();
		}catch(SQLException se){
			System.out.println(se);
			throw se;
		}finally{
			System.out.println("-----finally----------");
			close(conn, psmt, rs);
		}
		return count;
	}//
	public int deleteCustUser() throws SQLException{
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		int count=0;
		String sql="DELETE FROM CUSTUSER WHERE ID=?";
		try{
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			//받은 파라메터 쿼리의 ?에 넣기 
			psmt.setString(1,id.trim()); 
			//updateCustUser 실행하기
			count=psmt.executeUpdate();
		}catch(SQLException se){
			System.out.println(se);
			throw se;
		}finally{
			System.out.println("-----finally----------");
			close(conn, psmt, rs);
		}
		return count;
	}//
	public boolean deleteCustUsers() throws SQLException{
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		int[] count=new int[ids.length];
		String sql="DELETE FROM CUSTUSER WHERE ID=?";
		try{
			conn=getConnection();
			conn.setAutoCommit(false);
			psmt=conn.prepareStatement(sql);
			for(int i=0;i<ids.length; i++){
				psmt.setString(1,ids[i].trim()); 
				psmt.addBatch();
			}//
			
			//updateCustUser 실행하기
			count=psmt.executeBatch();
			conn.commit();
		}catch(SQLException se){
			try {
				conn.rollback();
			} catch (SQLException e) {
				
			}
			System.out.println(se);
			throw se;
		}finally{
			System.out.println("-----finally----------");
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				
			}
			close(conn, psmt, rs);
		}
		boolean isS=true;
		for(int i=0; i<count.length; i++){
			if(count[i]!=-2){
				isS=false;
				break;
			}
		}
		return isS;
	}//
	public Vector<CustUserWorkBean> getCustUserList() throws Exception {
        Connection conn = null;
		  PreparedStatement  stmt = null;
		  ResultSet rs = null;
		  String strQuery = "SELECT ID,NAME,ADDRESS FROM CUSTUSER "
			               +" ORDER BY ID DESC";
		  
		  Vector<CustUserWorkBean> list=new Vector<CustUserWorkBean>(5,5);
		  try {
			 conn = getConnection();
			 stmt = conn.prepareStatement(strQuery);
			System.out.println("strquery="+strQuery);
			rs = stmt.executeQuery();
			while(rs.next()){
				CustUserWorkBean cust=new CustUserWorkBean();
				cust.setId(rs.getString("ID"));
				cust.setName(rs.getString("NAME"));
				cust.setAddress(rs.getString("ADDRESS"));
				list.add(cust);
			}
		  }catch(Exception ex){
			 System.out.println("Exception" + ex);
			 throw ex;
		  }finally{
			  System.out.println("-----finally----------");
	        this.close(conn, stmt, rs);
		  }
		return list;
	}//
	public CustUserWorkBean getCustUser() throws Exception {
        Connection conn = null;
		  PreparedStatement  stmt = null;
		  ResultSet rs = null;
		  String strQuery = "SELECT ID,NAME,ADDRESS FROM CUSTUSER "
			               +" WHERE  ID=?";
		  
		  CustUserWorkBean cust=new CustUserWorkBean();
		  try {
			 conn = getConnection();
			 stmt = conn.prepareStatement(strQuery);
			 stmt.setString(1, id.trim());
			System.out.println("strquery="+strQuery);
			rs = stmt.executeQuery();
			while(rs.next()){
				cust.setId(rs.getString("ID"));
				cust.setName(rs.getString("NAME"));
				cust.setAddress(rs.getString("ADDRESS"));
			}
		  }catch(Exception ex){
			 System.out.println("Exception" + ex);
			 throw ex;
		  }finally{
			  System.out.println("-----finally----------");
	        this.close(conn, stmt, rs);
		  }
		return cust;
	}//
}//
