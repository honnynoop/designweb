<%@ page contentType="text/html; charset=euc-kr" %>
<%@page errorPage="custerror.jsp" %>
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
<title>CustUser Update</title>
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
String name=request.getParameter("name");
String address=request.getParameter("address");
if(isNull(id) ||isNull(name)||isNull(address) ){
	throw new CustUserSQLException("�Ķ���Ͱ� ���Դϴ�. ��� �Է��Ͻñ� �ٶ��ϴ�.");
}//
//DB�� �����ϱ� ���ۺκ�
try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	//Class.forName("oracle.OracleDriver"); //�ε����� Ȯ��
} catch (ClassNotFoundException e) {
	System.out.println("1/6 �ε� ����");
	throw new ClassNotFoundException(
	 "custuserupdate.jsp���� Ŭ���� �ε� �ϴ� ����, ����̹��� Ȯ���Ͻʽÿ�.");
}

String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
String user="hr";
String pwd="jungbo";
Connection conn= null;
PreparedStatement psmt=null;
int count=0;
String sql="UPDATE CUSTUSER SET NAME=? , ADDRESS=? WHERE ID=?";
try {
	conn=DriverManager.getConnection(url,user,pwd);
	psmt=conn.prepareStatement(sql);
	//���� �Ķ���� ������ ?�� �ֱ� 
	psmt.setString(1,name); 
	psmt.setString(2,address); 
	psmt.setString(3,id); 
	//insert �����ϱ�
	count=psmt.executeUpdate();
} catch (SQLException e) {
	throw new CustUserSQLException(
	"custuserupdate.jsp���� ���� �����ϴ�  ����, SQL ���� Ȯ���ϼ���");
}finally{
	//������ �ݱ�
	if(psmt!=null)psmt.close();
	if(conn!=null)conn.close();
}
//��� ���� ���� �̿�
if(count>0){
	%>
	<script type="text/javascript">
	alert('���������� �������� �����Ͽ����ϴ�.');
	location.href='custuserlist.jsp';
	</script>
	<% 
}else{
	%>
	<script type="text/javascript">
	alert('�������� ���濡  �����Ͽ����ϴ�.');
	location.href='custuserupdate.jsp?id=<%=id%>';
	</script>
	<% 
}
%>
</body>
</html>