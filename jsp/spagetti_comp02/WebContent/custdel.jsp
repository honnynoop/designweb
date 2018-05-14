<%@ page contentType="text/html; charset=euc-kr" %>
<%@page import="com.jungbo.except.CustUserSQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.SQLException"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>CustUser Lists</title>
</head>
<body>
<%!
private boolean isNull(String str){
	return str==null || str.trim().equals("");
}
%>
<%
//�Ķ���� �ޱ�  
//null ���ڿ��ΰ� �Ǻ��� �κ��� �ʿ��ϴ�.
String id=request.getParameter("id");

if(isNull(id)  ){
	throw new CustUserSQLException("�Ķ���Ͱ� ���Դϴ�. ��� �Է��Ͻñ� �ٶ��ϴ�.");
}//
//DB�� �����ϱ� ���ۺκ�


Class.forName("oracle.jdbc.driver.OracleDriver");
String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
String user="hr";
String pwd="jungbo";
Connection conn= null;
PreparedStatement psmt=null;
int count=0;
String sql="DELETE FROM CUSTUSER WHERE ID=?";
conn=DriverManager.getConnection(url,user,pwd);
psmt=conn.prepareStatement(sql);
//���� �Ķ���� ������ ?�� �ֱ� 
psmt.setString(1,id); 
count=psmt.executeUpdate();
//������ �ݱ�
if(psmt!=null)psmt.close();
if(conn!=null)conn.close();

//��� ���� ���� �̿�
if(count>0){
	%>
	<script type="text/javascript">
	alert('���������� ����  �����Ͽ����ϴ�.');
	location.href='custuserlist.jsp';
	</script>
	<% 
}else{
	%>
	<script type="text/javascript">
	alert('�� ������ �����Ͽ����ϴ�.');
	location.href='custuserdetail.jsp?id=<%=id%>';
	</script>
	<% 
}
%>
</body>
</html>
