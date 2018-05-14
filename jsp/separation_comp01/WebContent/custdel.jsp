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
<jsp:useBean id="manager" class="com.jungbo.sepa.CustUserWorkBean"/>
<jsp:setProperty name="manager" property="id" />
<%
//�Ķ���� �ޱ�  
//null ���ڿ��ΰ� �Ǻ��� �κ��� �ʿ��ϴ�.
if(manager.getId()==null || manager.getId().equals("") ){
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
	count=manager.deleteCustUser();
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
	location.href='custuserdetail.jsp?id=<%=manager.getIds()%>';
	</script>
	<% 
}
%>
</body>
</html>
