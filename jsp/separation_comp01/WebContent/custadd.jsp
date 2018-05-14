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
if(custwb.getId()==null || custwb.getId().equals("")
||custwb.getName()==null || custwb.getName().equals("")
||custwb.getAddress()==null || custwb.getAddress().equals("")){
	throw new CustUserSQLException(
	"custadd.jsp  ������ �߰��� �� �����ϴ�.");
}	
try {
	count=custwb.addCustUser();
} catch (SQLException e) {
	throw new CustUserSQLException(
	"custadd.jsp���� ���� �غ��ϴ�  ����.");
}
//��� ���� ���� �̿�
if(count>0){
	%>
	<script type="text/javascript">
	alert('���������� ���� �߰��Ͽ����ϴ�.');
	location.href='custuserlist.jsp';
	</script>
	<% 
}else{
	%>
	<script type="text/javascript">
	alert('�� �߰��� �����Ͽ����ϴ�.');
	location.href='custaddform.jsp';
	</script>
	<% 
}
%>
</body>
</html>
