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
	throw new CustUserSQLException("�Ķ���Ͱ� ���Դϴ�. ��� �Է��Ͻñ� �ٶ��ϴ�.");
}//

try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
} catch (ClassNotFoundException e) {
	System.out.println("1/6 �ε� ����");
	throw new ClassNotFoundException(
	 "custuserdetail.jsp���� Ŭ���� �ε� �ϴ� ����, ����̹��� Ȯ���Ͻʽÿ�.");
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
			"custuserdetail.jsp���� ������ ���̽��� �����ϴ� ����,"
			+ "url,user, pass Ȯ�ο��");
}
try {
	psmt=conn.prepareStatement(sql);
	psmt.setString(1,pid); 
} catch (SQLException e) {
	throw new CustUserSQLException(
	"custuserdetail.jsp���� ���� �غ��ϴ�  ����.");
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
	"custuserdetail.jsp���� ���� �����ϴ�  ����, SQL ���� Ȯ���ϼ���");
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
 <td>�� �� ��</td>
 <td><%=id %></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>��      ��</td>
 <td> <%=name %></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>��      ��</td>
 <td><%=address %></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>�� �� �� �� �� ��</td>
 <td>
<form action="custuserupdate.jsp" method='post'>
      <input type='hidden' name='id' value='<%=id%>'/>
      <input type='submit'  value='������������'/>
  </form>
</td>
 </tr>
 <tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
 <tr bgcolor="#F6F6F6">
 <td>��      ��</td>
 <td>
<form action="custdel.jsp" method='post'>
      <input type='hidden' name='id' value='<%=id%>'/>
    <input type='submit'  value='������������'/>
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