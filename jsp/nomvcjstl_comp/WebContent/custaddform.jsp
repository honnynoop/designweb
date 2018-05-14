<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="euc-kr"/>
<html>
<head>
<title>Add CustUser </title>
</head>
<body>
<center>
고객추가
<form name='formAdd' action="custadd.jsp" method='POST'>
<table width="600" border="0" cellpadding="0" cellspacing="0">
<col width="200"><col width="400">
<tr><td height="2" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
<td align='center'>아이디</td> 
<td><input type='text' name='id' size='60' value='cust'/> </td>
</tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
<td align='center'>이      름</td> 
<td><input type='text' name='name' size='60'/> </td>
</tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
 <tr bgcolor="#F6F6F6">
<td align='center'>주     소</td>
<td><input type='text' name='address' size='60'/> </td>
</tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor='#F6F6D6'>
<td align='center' colspan='2'>
<input type='submit' value='고객추가' />
<input type='reset' value='추가취소' />
</td>
</tr>
<tr><td height="2" bgcolor="#0000ff" colspan="2"></td></tr>
</table>
</form>
<br/>
<c:url value="index.jsp" var='home'/>
<a href='${home}'>Home</a><br/>
</center>
</body>
</html>
