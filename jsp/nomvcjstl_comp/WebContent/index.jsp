<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="euc-kr"/>
<html>
<head>
<title>Index</title>
</head>
<body>
<!-- ������ �ҽ��� ���ǿ� ���� -->
<sql:setDataSource dataSource="jdbc/nomvc" var="oracleDS" 
                                        scope="session"/>
<c:url value="custuserlist.jsp" var='lists'/>
<a href='${lists}'>��� CUSTUSER ����</a><br/>
</body>
</html>