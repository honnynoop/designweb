<%@page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pagingtag" uri="/WEB-INF/tld/pageingtag.tld" %>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>CustUser Lists</title>
<script type="text/javascript">
function deletechecks(e){
	var x=document.getElementsByName("delck");
	var lng=x.length;
	for(i=0;i<lng;++i){
		x(i).checked=e;		
	}
}

function mulSearch(){
	var frm = document.forms[0];
	if(frm.name.value==''){
		frm.name.value='%';
	}
	frm.command.value='list';
	frm.submit();
}
</script>
</head>
<body>

<center>
<form action="custusercontrol.jsp" method='post'>
<input type='hidden' name='command' value='muldel'/>
<table width="670" border="0" cellpadding="0" cellspacing="0">
<tr>
	<td align="right">
아이디 :<select name="id">
			<option value="%">검색</option>
			<c:forEach var='idList' items="${requestScope.idList}">
			<option value="${idList.id}">${idList.id}</option>
			</c:forEach>
		</select>
     이름 : <input type='text' name='name'>
		<img src='image/btn_search01.gif' style='cursor:hand;' alt='serarch' value='%' onclick="mulSearch()"/>
	</td>
</tr>
</table>

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
<c:set var='custlists' value='${requestScope.custs}'/>
<c:if test="${empty custlists}">
<tr bgcolor='#F6F6D6'>
  <td colspan='3' align='center'>고객 리스트가 존재하지 않습니다.</td>
</tr>
</c:if>
<c:forEach var='cust' items='${custlists.results}'>
		<tr bgcolor='#F6F6D6'>
          <td align='center' bgcolor='yellow'>
<input type='checkbox' name='delck' value='${cust.id}'/></td>
		   <td>${cust.id}</td>
		  <td> <a href='custusercontrol.jsp?command=detail&id=${cust.id}'>${cust.name}</a>   </td>
		</tr>
		<tr><td height="1" bgcolor="#c0c0c0" colspan="3"></td></tr>
</c:forEach>
<tr><td align='center' height="1" bgcolor="#c0c0c0" colspan="3">
 <input type='submit'  value='고객정보삭제'/>
</td></tr>
<tr><td height="2" bgcolor="#0000ff" colspan="3"></td></tr>
<tr bgcolor='#F6F6D6'>
  <td colspan='3'><c:url value="custaddform.jsp" var='custadd'/>
<a href='${custadd}'>고객추가하기</a></td>
</tr>
<tr bgcolor='#F6F6D6'>
<td colspan='3'>
<pagingtag:pagelist pbean='${custlists.pageBean}'
             urls='custusercontrol.jsp?command=list'/>
</td>
</tr>
</table>
</form>
<br/>
<c:url value="index.jsp" var='home'/>
<a href='${home}'>Home</a><br/>
</center>
</body>
</html>