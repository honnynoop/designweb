<%@page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="euc-kr"/>
<html>
<head>
<title>CustUser Update</title>
</head>
<c:if test="${empty param.id }">
<c:redirect url="index.jsp"/>
</c:if> 
<sql:query var="custuser" dataSource="${sessionScope.oracleDS}">
SELECT ID, NAME, ADDRESS  FROM CUSTUSER WHERE ID=?
   <sql:param value="${param.id}" />
</sql:query>
<body>
<center>
<form action="./custupdate.jsp" method='post'>
<table width="600" border="0" cellpadding="0" cellspacing="0">
<col width="200"><col width="400">
<c:if test="${empty custuser}">
<tr bgcolor='#F6F6D6'>
  <td colspan='2' align='center'>고객 리스트가 존재하지 않습니다.</td>
</tr>
</c:if>
<c:if test="${not empty custuser}">
<c:forEach var='cust' items='${custuser.rows}'>
 <tr bgcolor="#F6F6F6">
 <td>아 이 디</td>
 <td><input size='30' type='text' name='id' value="${cust.id}" readonly/></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>이      름</td>
 <td><input size='30' type='text' name='name' value="${cust.name}"/></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>주      소</td>
 <td><input size='30' type='text' name='address' value="${cust.address}"/></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td colspan='2'> <input type='submit'  value='고객정보변경'/></td>
 </tr>
 </c:forEach>
 </c:if>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
</table>
 </form>
<br/>
<a href='index.jsp'>Home</a><br/>
</center>
</body>
</html>
