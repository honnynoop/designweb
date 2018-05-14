<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="java.util.Vector"%>
<%@page import="com.jungbo.pagecentric.CustUserDto"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>CustUser Lists</title>
</head>
<jsp:useBean id="custbeande" class="com.jungbo.pagecentric.CustUserBean" scope='request'/>
<body>
<center>
<table width="600" border="0" cellpadding="0" cellspacing="0">
<col width="200"><col width="400">
<tr><td height="2" bgcolor="#0000ff" colspan="2"></td></tr>
 <tr bgcolor="#F6F6F6">
 <td>아 이 디</td>
 <td><jsp:getProperty name="custbeande" property="id" /></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>이      름</td>
 <td><jsp:getProperty name="custbeande" property="name" /></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>주      소</td>
 <td><jsp:getProperty name="custbeande" property="address" /></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>고 객 정 보 변 경</td>
 <td>
<form action="custusercontrol.jsp" method='post'>
<input type='hidden' name='command' value='bfupdate'/>
      <input type='hidden' name='id' value='<jsp:getProperty name="custbeande" property="id" />'/>
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
      <input type='hidden' name='id' value='<jsp:getProperty name="custbeande" property="id" />'/>
    <input type='submit'  value='고객정보삭제'/>
</form>
</td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
</table>
<br/>
<a href='index.jsp'>Home</a><br/>
</center>
</body>
</html>
