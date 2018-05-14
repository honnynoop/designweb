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
<c:if test="${empty param.id }">
<c:redirect url="index.jsp"/>
</c:if> 
<sql:query var="custuser" dataSource="${sessionScope.oracleDS}">
SELECT ID, NAME, ADDRESS  FROM CUSTUSER WHERE ID=?
<sql:param value="${param.id}" />
</sql:query>
<center>
<table width="600" border="0" cellpadding="0" cellspacing="0">
<col width="200"><col width="400">
<c:if test="${empty custuser}">
<tr bgcolor='#F6F6D6'>
  <td colspan='2' align='center'>고객 리스트가 존재하지 않습니다.</td>
</tr>
</c:if>
<c:if test="${not empty custuser}">
<c:forEach var='cust' items='${custuser.rows}'>
<tr><td height="2" bgcolor="#0000ff" colspan="2"></td></tr>
 <tr bgcolor="#F6F6F6">
 <td>아 이 디</td>
 <td>${cust.id}</td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>이      름</td>
 <td> ${cust.name}</td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>주      소</td>
 <td>${cust.address}</td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>고 객 정 보 변 경</td>
 <td>
 <form action="custuserupdate.jsp" method='post'>
      <input type='hidden' name='id' value='${cust.id}'/>
      <input type='submit'  value='고객정보변경'/>
  </form>
</td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>삭      제</td>
 <td>
<form action="custdel.jsp" method='post'>
      <input type='hidden' name='id' value='${cust.id}'/>
    <input type='submit'  value='고객정보삭제'/>
</form>
</td>
 </tr>
 </c:forEach>
 </c:if>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
</table>
<br/>
<a href='index.jsp'>Home</a><br/>
</center>
</body>
</html>
