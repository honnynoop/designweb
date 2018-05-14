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
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
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
	 "custuserdetail.jsp에서 클래스 로딩 하다 실패, 드라이버를 확인하십시오.");
}

String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
String user="hr";
String pwd="jungbo"; 
String sql=" SELECT ID,NAME,ADDRESS FROM CUSTUSER "+
		   " WHERE ID=?";

Connection conn= null;
PreparedStatement psmt=null;
ResultSet rs=null;

try {
	conn=DriverManager.getConnection(url,user,pwd);
} catch (SQLException e) {
	throw new CustUserSQLException(
			"custuserdetail.jsp에서 데이터 베이스에 연결하다 실패,"
			+ "url,user, pass 확인요망");
}
try {
	psmt=conn.prepareStatement(sql);
	psmt.setString(1,pid); 
} catch (SQLException e) {
	throw new CustUserSQLException(
	"custuserdetail.jsp에서 쿼리 준비하다  실패.");
}

String id="";
String name="";
String address="";

try {
	rs=psmt.executeQuery();
	while(rs.next()){
		id=rs.getString("ID");
		name=rs.getString("NAME");
		address=rs.getString("ADDRESS");
	}
} catch (SQLException e) {
	throw new CustUserSQLException(
	"custuserdetail.jsp에서 쿼리 실행하다  실패, SQL 쿼리 확인하세요");
}finally{
	if(rs!=null)rs.close();
	if(psmt!=null)psmt.close();
	if(conn!=null)conn.close();
}
%>
<center>
<div id="Content">
<table width="600" border="0" cellpadding="0" cellspacing="0">
<col width="200"><col width="400">
<tr><td height="2" bgcolor="#0000ff" colspan="2"></td></tr>
 <tr bgcolor="#F6F6F6">
 <td>아 이 디</td>
 <td><%=id %></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>이      름</td>
 <td> <%=name %></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>주      소</td>
 <td><%=address %></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>고 객 정 보 변 경</td>
 <td>
<form action="custuserupdate.jsp" method='post'>
      <input type='hidden' name='id' value='<%=id%>'/>
      <input type='submit'  value='고객정보변경'/>
  </form>
</td>
 </tr>
 <tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
 <tr bgcolor="#F6F6F6">
 <td>삭      제</td>
 <td>
<form action="custdel.jsp" method='post'>
      <input type='hidden' name='id' value='<%=id%>'/>
    <input type='submit'  value='고객정보삭제'/>
</form>
</td>
 </tr>
<tr><td height="2" bgcolor="#0000ff" colspan="2"></td></tr>
</table>
<br/>
<a href='index.jsp'>Home</a><br/>
</div>
</center>
</body>
</html>
