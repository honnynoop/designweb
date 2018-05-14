<%@page contentType="text/html; charset=euc-kr" %>
<%@page errorPage="custerror.jsp" %>
<%@page import="java.sql.SQLException"%>
<%@page import="com.jungbo.except.CustUserSQLException"%>
<%@page import="com.jungbo.sepa.CustUserBean"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>CustUser Lists</title>
</head>
<body>
<jsp:useBean id="cust" class="com.jungbo.sepa.CustUserBean"/>
<jsp:setProperty name="cust" property="id" />
<jsp:useBean id="manager" class="com.jungbo.sepa.CustUserManager"/>
<%
if(cust.getId()==null || cust.getId().equals("") ){
	%>
	<script type='text/javascript'>
	alert('�߸��� ��η� ��û�� �Ͽ����ϴ�.');
	location.href='index.jsp';
	</script>
	<% 
	return;
}
try {
	//useBean�� �������� �ִ°�ü, DAO���� ���� ��ü�� �������� ���°�ü
	//WL��  cust= manager.getCustUser()�� �����
	//��Ĺ���1
	//CustUserBean cust1= manager.getCustUser(cust.getId());
	//cust.setId(cust1.getId());
	//cust.setName(cust1.getName());
	//cust.setAddress(cust1.getAddress());
	//��Ĺ���2  �Ʒ��� ���� �������̿�
	cust= manager.getCustUser(cust.getId());
	pageContext.setAttribute("cust",cust);
} catch (SQLException e) {
	throw new CustUserSQLException(
	"custuserupdate.jsp���� ���� �غ��ϴ�  ����.");
}
%>
<center>
<div id="Content">
<form action="custupdate.jsp" method='post'>
<table width="600" border="0" cellpadding="0" cellspacing="0">
<col width="200"><col width="400">
<tr><td height="2" bgcolor="#0000ff" colspan="2"></td></tr>
 <tr bgcolor="#F6F6F6">
 <td>�� �� ��</td>
 <td><input size='30' type='text' name='id' 
 value="<jsp:getProperty name="cust" property="id" />" readonly/></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>��      ��</td>
 <td><input size='30' type='text' name='name' 
 value="<jsp:getProperty name="cust" property="name" />"/></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>��      ��</td>
 <td><input size='30' type='text' name='address' 
 value="<jsp:getProperty name="cust" property="address" />"/></td>
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
</div>
</center>
</body>
</html>
