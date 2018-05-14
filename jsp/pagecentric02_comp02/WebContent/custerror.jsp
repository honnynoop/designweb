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
불편을 드려서 죄송합니다. 계속 반복되면 관리자에게 항의하십시오.<br/>
내용: <%=errormsg %><br/>
<a href='<%=url%>'>돌아가기</a><br/>
</body>
</html>