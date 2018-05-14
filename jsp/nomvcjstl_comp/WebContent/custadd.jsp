<%@page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="euc-kr"/>
<html>
<head>
<title>CustUser Lists</title>
</head>
<body>
<c:if test="${empty param.id or empty param.name or empty param.address}">
<c:redirect url="index.jsp"/>
</c:if> 
<sql:update var="updateCust" dataSource="${sessionScope.oracleDS}">
       INSERT INTO CUSTUSER VALUES(?,?,?)
   <sql:param value="${param.id}" />
   <sql:param value="${param.name}"/>
   <sql:param value="${param.address}" />
</sql:update>
<c:choose>
<c:when test="${updateCust gt 0}">
<script type="text/javascript">
alert('���������� ���� �߰��Ͽ����ϴ�.');
location.href='custuserlist.jsp';
</script>
</c:when>
<c:otherwise>
<script type="text/javascript">
alert('�� �߰��� �����Ͽ����ϴ�.');
location.href='custaddform.jsp';
</script>
</c:otherwise>
</c:choose>
</body>
</html>
