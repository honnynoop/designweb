<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="java.util.Vector"%>
<%@page import="com.jungbo.pagecentric.CustUserDto"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>CustUser Lists</title>
</head>
<body>
<%
//request�� ����� ��� �� ��������
Object obj=request.getAttribute("cust");
CustUserDto cust=null;
if(obj!=null){
	cust= (CustUserDto )obj;
}else{
	%>
	<script type='text/javascript'>
	alert('�߸��� ��η� ��û�� �Ͽ����ϴ�.');
	location.href='index.jsp';
	</script>
	<% 
	return;
}
%>
<center>
<table width="600" border="0" cellpadding="0" cellspacing="0">
<col width="200"><col width="400">
<tr><td height="2" bgcolor="#0000ff" colspan="2"></td></tr>
 <tr bgcolor="#F6F6F6">
 <td>�� �� ��</td>
 <td><%=cust.getId() %></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>��      ��</td>
 <td> <%=cust.getName() %></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>��      ��</td>
 <td><%=cust.getAddress() %></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>�� �� �� �� �� ��</td>
 <td>
<form action="custusercontrol.jsp" method='post'>
<input type='hidden' name='command' value='bfupdate'/>
      <input type='hidden' name='id' value='<%=cust.getId()%>'/>
      <input type='submit'  value='����������'/>
  </form>
</td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>��      ��</td>
 <td>
<form action="custusercontrol.jsp" method='post'>
<input type='hidden' name='command' value='delete'/>
      <input type='hidden' name='id' value='<%=cust.getId()%>'/>
    <input type='submit'  value='����������'/>
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
