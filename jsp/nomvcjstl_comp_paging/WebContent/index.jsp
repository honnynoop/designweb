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
<!-- 데이터 소스를 세션에 저장 -->
<sql:setDataSource var="oracleDS" 
 driver="oracle.jdbc.driver.OracleDriver"
 url="jdbc:oracle:thin:@127.0.0.1:1521:xe"
 user="hr" 
 password="jungbo"
 scope="session"/>
<c:url value="custuserlist.jsp" var='lists'/>
<a href='${lists}'>모든 CUSTUSER 보기</a><br/>
</body>
</html>