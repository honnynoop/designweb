<%@ page contentType="text/html; charset=euc-kr" %>
<%@page errorPage="custerror.jsp" %>
<%@page import="com.jungbo.except.CustUserSQLException"%>
<%@page import="java.sql.SQLException"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>CustUser DELETE</title>
</head>
<body>
<jsp:useBean id="cust" class="com.jungbo.sepa.CustUserBean"/>
<jsp:setProperty name="cust" property="id" />
<jsp:useBean id="manager" class="com.jungbo.sepa.CustUserManager"/>
<%
if(cust.getId()==null || cust.getId().equals("") ){
	%>
	<script type='text/javascript'>
	alert('�߸��� ��η� ��û�� �Ͽ����ϴ�.');
	location.href='index.jsp';
	</script>
	<% 
	return;
}
int count=0;
try {
	count=manager.deleteCustUser(cust.getId());
} catch (SQLException e) {
	throw new CustUserSQLException(
	"custdel.jsp����   ����.");
}
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
	location.href='custuserdetail.jsp?id=<%=cust.getId()%>';
	</script>
	<% 
}
%>
</body>
</html>
