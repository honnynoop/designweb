<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="euc-kr"/>
<html>
<head><title>Index</title></head>
<body>
<c:url value="custusercontrol.jsp" var='controls'>
<c:param name="command" value="list"/>
</c:url>
<a href='${controls}'>��� CUSTUSER ����</a><br/>
</body>
</html>