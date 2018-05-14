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
<script type="text/javascript">
function deletechecks(e){
	var x=document.getElementsByName("ids");
	var lng=x.length;
	for(i=0;i<lng;++i){
		x(i).checked=e;		
	}
}
</script>
</head>
<body>
<jsp:useBean id="manager" class="com.jungbo.sepa.CustUserWorkBean"/>
<%
//모든 고객 가져오기 DL 관련 
Vector<CustUserWorkBean> custs=new  Vector<CustUserWorkBean> (5,5);
try {
	custs= manager.getCustUserList();
} catch (SQLException e) {
	throw new CustUserSQLException(
	"custuserlist.jsp에서 쿼리 준비하다  실패.");
}
%>
<center>
<form action="custmuldel.jsp" method='post'>
<table width="700" border="0" cellpadding="0" cellspacing="0">
<col width="100"><col width="300"><col width="300">
<tr><td height="2" bgcolor="#0000ff" colspan="3"></td></tr>
 <tr bgcolor="#F6F6F6">
 <td bgcolor='YELLOW' align='center'>
<input type='checkbox' name='alldel'   onclick="deletechecks(this.checked)"/>
</td>
<td>아 이 디</td>
 <td>이     름</td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="3"></td></tr>
<%
if(custs.size()==0){//존재하지 않으면
%>
<tr bgcolor='#F6F6D6'>
  <td colspan='3' align='center'>고객 리스트가 존재하지 않습니다.</td>
</tr>
<% 
}else{
	for(int i=0; i<custs.size();i++){
		CustUserWorkBean cust=custs.get(i);
		%>
		<tr bgcolor='#F6F6D6'>
          <td align='center' bgcolor='yellow'>
<input type='checkbox' name='ids' value='<%=cust.getId()%>'/>
          </td>
		  <td><%=cust.getId() %></td>
		  <td> <a href='custuserdetail.jsp?id=<%=cust.getId()%>'><%=cust.getName() %></a>   </td>
		</tr>
		<tr><td height="1" bgcolor="#c0c0c0" colspan="3"></td></tr>
		 <% 
	}//for
}//else
%>
<tr><td align='center' height="1" bgcolor="#c0c0c0" colspan="3">
<input type='submit'  value='고객정보삭제'/>
</td></tr>
<tr><td height="2" bgcolor="#0000ff" colspan="3"></td></tr>
<tr bgcolor='#F6F6D6'>
  <td colspan='3' ><a href='custaddform.jsp'>고객추가하기</a></td>
</tr>
</table>
</form>
<br/>
<a href='index.jsp'>Home</a><br/>
</center>
</body>
</html>
