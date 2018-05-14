<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="java.util.Vector"%>
<%@page import="com.jungbo.pagecentric.CustUserDto"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>CustUser Lists</title>
</head>
<jsp:useBean id="custbeanbf" 
 class="com.jungbo.pagecentric.CustUserBean" scope='request'/>
<body>
<center>
<form action="custusercontrol.jsp" method='post'>
<input type='hidden' name='command' value='update'/>
<table width="600" border="0" cellpadding="0" cellspacing="0">
<col width="200"><col width="400">
<tr><td height="2" bgcolor="#0000ff" colspan="2"></td></tr>
 <tr bgcolor="#F6F6F6">
 <td>아 이 디</td>
 <td><input size='30' type='text' name='id' 
value="<jsp:getProperty name="custbeanbf" property="id"/>" readonly/></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>이      름</td>
 <td><input size='30' type='text' name='name' 
 value="<jsp:getProperty name="custbeanbf" property="name" />"/></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>주      소</td>
 <td><input size='30' type='text' name='address' 
 value="<jsp:getProperty name="custbeanbf" property="address"/>"/></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td colspan='2'> <input type='submit'  value='고객정보변경'/></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
</table>
 </form>
<br/>
<a href='index.jsp'>Home</a><br/>
</center>
</body>
</html>
