<%@page contentType="text/html; charset=euc-kr" %>
<%@page errorPage="custerror.jsp" %>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.jungbo.except.CustUserSQLException"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>CustUser Detail</title>
</head>
<body>
<%!
private boolean isNull(String str){
	return str==null || str.trim().equals("");
}
%>
<%
String pid=request.getParameter("id");
if(isNull(pid)  ){
	throw new CustUserSQLException("파라메터가 널입니다. 모두 입력하시기 바랍니다.");
}//
try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
} catch (ClassNotFoundException e) {
	System.out.println("1/6 로딩 실패");
	throw new ClassNotFoundException(
	 "custuserupdate.jsp에서 클래스 로딩 하다 실패, 드라이버를 확인하십시오.");
}
String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
String user="hr";
String pwd="jungbo"; 
String sql=" SELECT ID,NAME,ADDRESS FROM CUSTUSER "+
		   " WHERE ID=?";
Connection conn= null;
PreparedStatement psmt=null;
ResultSet rs=null;
String id="";
String name="";
String address="";
try {
	conn=DriverManager.getConnection(url,user,pwd);
	psmt=conn.prepareStatement(sql);
	psmt.setString(1,pid); 
	rs=psmt.executeQuery();
	while(rs.next()){
		id=rs.getString("ID");
		name=rs.getString("NAME");
		address=rs.getString("ADDRESS");
	}
} catch (SQLException e) {
	throw new CustUserSQLException(
	"custuserupdate.jsp에서 쿼리 실행하다  실패, SQL 쿼리 확인하세요");
}finally{
	if(rs!=null)rs.close();
	if(psmt!=null)psmt.close();
	if(conn!=null)conn.close();
}
%>
<center>
<div id="Content">
<form action="custupdate.jsp" method='post'>
<table width="600" border="0" cellpadding="0" cellspacing="0">
<col width="200"><col width="400">
<tr><td height="2" bgcolor="#0000ff" colspan="2"></td></tr>
 <tr bgcolor="#F6F6F6">
 <td>아 이 디</td>
 <td><input size='30' type='text' name='id' value="<%=id%>" readonly/></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>이      름</td>
 <td><input size='30' type='text' name='name' value="<%=name%>"/></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>주      소</td>
 <td><input size='30' type='text' name='address' value="<%=address%>"/></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td colspan='2'> <input type='submit'  value='고객정보변경'/></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
</table>
 </form>
<br/>
<a href='index.jsp'>Home</a><br/>
</div>
</center>
</body>
</html>
