<%@ page contentType="text/html; charset=euc-kr" %>
<%@page isErrorPage="true" %>
<%@page import="com.jungbo.except.CustUserSQLException"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>Exception Handling</title>
</head>
<body>
<center>
<table width="700" border="0" cellpadding="0" cellspacing="0">
<col width="700">
<tr><td height="2" bgcolor="#0000ff" ></td></tr>
<tr><td>���ܹ߻�</td></tr>
<tr><td height="1" bgcolor="#0000ff" ></td></tr>
<tr><td>
<%
if(exception instanceof ClassNotFoundException){
	out.println(exception.toString()); 
}else if(exception instanceof CustUserSQLException){
	out.println(exception.toString()); 
}
%>
</td></tr>
<tr><td height="1" bgcolor="#0000ff" ></td></tr>
<tr><td>���ܰ� ��� �߻��ϸ� ����(������)���� �˷� �ֽʽÿ�.</td></tr>
<tr><td height="2" bgcolor="#0000ff" ></td></tr>
</table>
<a href='index.jsp'>HOME</a>
</center>
</body>
</html>