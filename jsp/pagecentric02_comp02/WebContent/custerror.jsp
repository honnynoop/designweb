<%@ page contentType="text/html; charset=euc-kr" %>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>Error</title>
</head>
<body>
<%
String errormsg=(String)request.getAttribute("errormsg");
String url=(String)request.getAttribute("url");
%>
������ ����� �˼��մϴ�. ��� �ݺ��Ǹ� �����ڿ��� �����Ͻʽÿ�.<br/>
����: <%=errormsg %><br/>
<a href='<%=url%>'>���ư���</a><br/>
</body>
</html>