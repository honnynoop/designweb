<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="com.jungbo.pagecentric.CustUserDto"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>CustUser Lists</title>
<script type="text/javascript">
function deletechecks(e){
	var x=document.getElementsByName("delck");
	var lng=x.length;
	for(i=0;i<lng;++i){
		x(i).checked=e;		
	}
}
</script>
</head>
<jsp:useBean id="custvector" scope='request' class="java.util.Vector"/>
<body>
<center>
<form action="custusercontrol.jsp" method='post'>
<input type='hidden' name='command' value='muldel'/>
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
if(custvector==null || custvector.size()==0){//존재하지 않으면
%>
<tr bgcolor='#F6F6D6'>
  <td colspan='3' align='center'>고객 리스트가 존재하지 않습니다.</td>
</tr>
<% 
}else{
	for(int i=0; i<custvector.size();i++){
		CustUserDto cust=(CustUserDto)custvector.get(i);
		%>
		<tr bgcolor='#F6F6D6'>
          <td align='center' bgcolor='yellow'>
<input type='checkbox' name='delck' value='<%=cust.getId()%>'/>
          </td>
		  <td><%=cust.getId() %></td>
		  <td> <a href='custusercontrol.jsp?command=detail&id=<%=cust.getId()%>'><%=cust.getName() %></a>   </td>
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

