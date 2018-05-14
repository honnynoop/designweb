<%@page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="euc-kr"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>CustUser Delete</title>
</head>
<body>

<c:if test="${empty paramValues.delck}">
<c:redirect url="index.jsp"/>
</c:if> 
<sql:transaction dataSource="${sessionScope.oracleDS}">
<c:forEach var='id' items='${paramValues.delck}'>      
<sql:update var="updateCust">
    DELETE FROM CUSTUSER WHERE ID=?
 <sql:param value="${id}" />
</sql:update>
</c:forEach>
</sql:transaction>

<c:redirect url="custuserlist.jsp"/>
           
</body>
</html>
