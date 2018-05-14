<%@page contentType="text/html; charset=euc-kr" %>
<%@page errorPage="custerror.jsp" %>
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
	alert('잘못된 경로로 요청을 하였습니다.');
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
	"custuserdetail.jsp에서 쿼리 준비하다  실패.");
}
%>
<center>
<table width="600" border="0" cellpadding="0" cellspacing="0">
<col width="200"><col width="400">
<tr><td height="2" bgcolor="#0000ff" colspan="2"></td></tr>
 <tr bgcolor="#F6F6F6">
 <td>아 이 디</td>
 <td><%=cust.getId() %></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>이      름</td>
 <td> <%=cust.getName() %></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>주      소</td>
 <td><%=cust.getAddress() %></td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>고 객 정 보 변 경</td>
 <td>
<form action="custuserupdate.jsp" method='post'>
      <input type='hidden' name='id' value='<%=cust.getId()%>'/>
      <input type='submit'  value='고객정보변경'/>
  </form>
</td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="2"></td></tr>
<tr bgcolor="#F6F6F6">
 <td>삭      제</td>
 <td>
<form action="custdel.jsp" method='post'>
      <input type='hidden' name='id' value='<%=cust.getId()%>'/>
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