<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="euc-kr"/>
<html>
<head>
<title>CustUser ADD</title>
</head>
<c:set var='errormsg' value='${requestScope.errormsg}'/>
<c:set var='url' value='${requestScope.url}'/>
<body>
������ ����� �˼��մϴ�. ��� �ݺ��Ǹ� �����ڴԿ��� �����Ͻʽÿ�.<br/>
����  : ${errormsg}<br/>
<c:url value="${url}" var='home'/>
<a href='${home}'>Home</a><br/>
</body>
</html>
