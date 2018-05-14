<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Index</title>
</head>
<body>
<c:url value="custusercontrol.jsp" var='controls'>
<c:param name="command" value="list"/>
</c:url>
<a href='${controls}'>모든 CUSTUSER 보기</a><br/>
</body>
</html>