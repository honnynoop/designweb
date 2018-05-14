<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>CustUser ADD</title>
</head>
<c:set var='errormsg' value='${requestScope.errormsg}'/>
<c:set var='url' value='${requestScope.url}'/>
<body>
불편을 드려서 죄송합니다. 계속 반복되면 하진님에게 항의하십시오.<br/>
내용  : ${errormsg}<br/>
<c:url value="${url}" var='home'/>
<a href='${home}'>Home</a><br/>
</body>
</html>
