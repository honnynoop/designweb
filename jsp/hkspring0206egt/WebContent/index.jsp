<%@ page contentType="text/html; charset=UTF-8" %>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
</head>
<body>
<%
response.sendRedirect("./login.do");
%>
<!-- <a href='./login.do'>로그인</a> -->
</body>
</html>
