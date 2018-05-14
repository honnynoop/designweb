<%@ page contentType="text/html; charset=euc-kr" %>
<%@page errorPage="custerror.jsp" %>
<%@page import="java.sql.SQLException"%>
<%@page import="com.jungbo.except.CustUserSQLException"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>CustUser ADD</title>
</head>
<jsp:useBean id="custwb" class="com.jungbo.sepa.CustUserWorkBean"/>
<jsp:setProperty name="custwb" property="id" />
<jsp:setProperty name="custwb" property="name" />
<jsp:setProperty name="custwb" property="address" />
<body>
<%
//workbean�� �̿��Ͽ� insert �����ϱ�
int count=0;
try {
	count=custwb.updateCustUser();
} catch (SQLException e) {
	throw new CustUserSQLException(
	"custuserupdate.jsp����   ����.");
}
if(count>0){
	%>
	<script type="text/javascript">
	alert('���������� ������ �����Ͽ����ϴ�.');
	location.href='custuserlist.jsp';
	</script>
	<% 
}else{
	%>
	<script type="text/javascript">
	alert('������ ���濡  �����Ͽ����ϴ�.');
	location.href='custuserupdate.jsp?id=<%=custwb.getId()%>';
	</script>
	<% 
}
%>
</body>
</html>
