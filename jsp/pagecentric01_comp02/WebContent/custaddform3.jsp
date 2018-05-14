<%@ page contentType="text/html; charset=euc-kr" %>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>Add CustUser </title>
<script>
function check() {
     if (document.formAdd.id.value == "") {
		 alert("아이디를 입력하세요.");
		 document.formAdd.id.focus();
		 return;
	  }
     if (document.formAdd.name.value == "") {
		 alert("이름을 입력하세요.");
		 document.formAdd.name.focus();
		 return;
	  }
     if (document.formAdd.address.value == "") {
		 alert("주소를 입력하세요.");
		 document.formAdd.address.focus();
		 return;
	  }
     document.formAdd.action="custusercontrol.jsp";  
     document.formAdd.method="post";  
     document.formAdd.command.value="add";
     document.formAdd.submit();
 }
</script>
</head>
<body>
<center>
고객추가
<form name='formAdd'>
<input type='hidden' name='command'/>
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
<input type="button"  value="고객추가" onClick="check()">
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