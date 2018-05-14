<%@page contentType="text/html; charset=euc-kr" %>
<%@page errorPage="custerror.jsp" %>
<%@page import="java.util.Vector"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.jungbo.except.CustUserSQLException"%>
<%@page import="com.jungbo.sepa.CustUserWorkBean"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>CustUser Lists</title>
</head>
<body>
<jsp:useBean id="manager" class="com.jungbo.sepa.CustUserWorkBean"/>
<jsp:setProperty name="manager" property="id" />
<%
if(manager.getId()==null || manager.getId().equals("") ){
	%>
	<script type='text/javascript'>
	alert('�߸��� ��η� ��û�� �Ͽ����ϴ�.');
	location.href='index.jsp';
	</script>
	<% 
	return;
}
CustUserWorkBean cust=new CustUserWorkBean();
try {
	cust= manager.getCustUser();
} catch (SQLException e) {
	throw new CustUserSQLException(
	"custuserupdate.jsp���� ���� �غ��ϴ�  ����.");
}
%>
<center>
<form action="custupdate.jsp" method='post'>
<table width="600" border="0" cellpadding="0" cellspacing="0">
<col width="200"><col width="400">
<tr><td height="2" bgcolor="#0000ff" colspan="2"></td></tr>
 <tr bgcolor="#F6F6F6">
 <td>�� �� ��</td>
 <td><input size='30' type='text' name='id' value="<%=cust.getId() %>" readonly/></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>��      ��</td>
 <td><input size='30' type='text' name='name' value="<%=cust.getName() %>"/></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>��      ��</td>
 <td><input size='30' type='text' name='address' value="<%=cust.getAddress() %>"/></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td colspan='2'> <input type='submit'  value='����������'/></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
</table>
 </form>
<br/>
<a href='index.jsp'>Home</a><br/>
</center>
</body>
</html>
