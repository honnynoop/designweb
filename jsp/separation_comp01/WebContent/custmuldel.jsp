<%@ page contentType="text/html; charset=euc-kr" %>
<%@page errorPage="custerror.jsp" %>
<%@page import="com.jungbo.except.CustUserSQLException"%>
<%@page import="java.sql.SQLException"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>CustUser MULDEL</title>
</head>
<body>
<jsp:useBean id="custwb" class="com.jungbo.sepa.CustUserWorkBean"/>
<jsp:setProperty name="custwb" property="ids" />
<%
//�Ķ���� �ޱ�  
//null ���ڿ��ΰ� �Ǻ��� �κ��� �ʿ��ϴ�.
if(custwb.getIds() == null || custwb.getIds() .length==0){
	throw new CustUserSQLException("�Ѱ� �̻� �����ؾ� �մϴ�.");
}//
boolean isS=false;
try {
	isS=custwb.deleteCustUsers();
} catch (SQLException e) {
	throw new CustUserSQLException(
	"custmuldel.jsp����   ����.");
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
