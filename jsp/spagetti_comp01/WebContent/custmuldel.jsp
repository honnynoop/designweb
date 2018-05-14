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
<title>CustUser MULDEL</title>
</head>
<body>
<%
//�Ķ���� �ޱ�  
//null ���ڿ��ΰ� �Ǻ��� �κ��� �ʿ��ϴ�.
String[] ids=request.getParameterValues("delck");

if(ids == null || ids.length==0){
	throw new CustUserSQLException("�Ѱ� �̻� �����ؾ� �մϴ�.");
}//
//DB�� �����ϱ� ���ۺκ�

try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
} catch (ClassNotFoundException e) {
	System.out.println("1/6 �ε� ����");
	throw new ClassNotFoundException(
	 "custmuldel.jsp���� Ŭ���� �ε� �ϴ� ����, ����̹��� Ȯ���Ͻʽÿ�.");
}
String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
String user="hr";
String pwd="jungbo";
Connection conn= null;
PreparedStatement psmt=null;
int[] count=new int[ids.length];
String sql="DELETE FROM CUSTUSER WHERE ID=?";
try{
	conn=DriverManager.getConnection(url,user,pwd);
	conn.setAutoCommit(false);
	psmt=conn.prepareStatement(sql);
	for(int i=0;i<ids.length; i++){
		psmt.setString(1,ids[i].trim()); 
		psmt.addBatch();
	}//
	//updateCustUser �����ϱ�
	count=psmt.executeBatch();
	conn.commit();
}catch(SQLException se){
	try {
		conn.rollback();
	} catch (SQLException e) {
		
	}
	throw new CustUserSQLException(
			"custmuldel.jsp���� �����͸� �����ϴ� ����");
}finally{
	try {
		conn.setAutoCommit(true);
	} catch (SQLException e) {	}
	if(psmt!=null)psmt.close();
	if(conn!=null)conn.close();
}
boolean isS=true;
for(int i=0; i<count.length; i++){
	if(count[i]!=-2){
		isS=false;
		break;
	}
}
//��� ���� ���� �̿�
if(isS){
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
	location.href='custuserlist.jsp';
	</script>
	<% 
}
%>
</body>
</html>
