<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="java.util.Vector"%>
<%@page import="com.jungbo.pagecentric.CustUserDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>CustUser Lists</title>
</head>
<c:set var='cust' value='${requestScope.cust}'/>
<body>
<center>
<div id="Content">

<table width="600" border="0" cellpadding="0" cellspacing="0">
<col width="200"><col width="400">
<c:if test="${empty cust}">
<tr bgcolor='#F6F6D6'>
  <td colspan='2' align='center'>고객 리스트가 존재하지 않습니다.</td>
</tr>
</c:if>
<c:if test="${not empty cust}">
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
<form action="custusercontrol.jsp" method='post'>
<input type='hidden' name='command' value='bfupdate'/>
      <input type='hidden' name='id' value='${cust.id}'/>
      <input type='submit'  value='고객정보변경'/>
  </form>
</td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>삭      제</td>
 <td>
<form action="custusercontrol.jsp" method='post'>
<input type='hidden' name='command' value='delete'/>
      <input type='hidden' name='id' value='${cust.id}'/>
    <input type='submit'  value='고객정보삭제'/>
</form>
</td>
 </tr>
 </c:if>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
</table>
<br/>
<a href='index.jsp'>Home</a><br/>
</div>
</center>
</body>
</html>
