<%@ page contentType="text/html; charset=euc-kr" %>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>Add CustUser </title>
</head>
<body>
<center>
���߰�
<form name='formAdd' action="custusercontrol.jsp" method='post'>
<input type='hidden' name='command' value='add'/>
<table width="600" border="0" cellpadding="0" cellspacing="0">
<col width="200"><col width="400">
<tr><td height="2" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
<td align='center'>���̵�</td> 
<td><input type='text' name='id' size='60' value='cust'/> </td>
</tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
<td align='center'>��      ��</td> 
<td><input type='text' name='name' size='60'/> </td>
</tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
 <tr bgcolor="#F6F6F6">
<td align='center'>��     ��</td>
<td><input type='text' name='address' size='60'/> </td>
</tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor='#F6F6D6'>
<td align='center' colspan='2'>
<input type='submit' value='���߰�' />
<input type='reset' value='�߰����' />
</td>
</tr>
<tr><td height="2" bgcolor="#0000ff" colspan="2"></td></tr>
</table>
</form>
<br/>
<a href='index.jsp'>Home</a><br/>
</center>
</body>
</html>
