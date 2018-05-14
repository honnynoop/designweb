<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="euc-kr"/>
<html>
<head>
<title>CustUser Update</title>
</head>
<c:set var='cust' value='${requestScope.cust}'/>
<body>
<center>
<div id="Content">
<form action="./custusercontrol.jsp" method='post'>
<input type='hidden' name='command' value='update'/>
<table width="600" border="0" cellpadding="0" cellspacing="0">
<col width="200"><col width="400">
<tr><td height="2" bgcolor="#0000ff" colspan="2"></td></tr>
<c:if test="${empty cust}">
<tr bgcolor='#F6F6D6'>
  <td colspan='2' align='center'>고객 리스트가 존재하지 않습니다.</td>
</tr>
</c:if>
<c:if test="${not empty cust}">
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
</c:if>

<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
</table>
 </form>
<br/>
<a href='index.jsp'>Home</a><br/>
</div>
</center>
</body>
</html>
