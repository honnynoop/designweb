<%@page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="euc-kr"/>
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
<body>
<sql:query var="custuser" dataSource="${sessionScope.oracleDS}"  >
SELECT ID, NAME, ADDRESS  FROM CUSTUSER  ORDER BY ID DESC
</sql:query>
<center>
<form action="custmuldel.jsp" method='post'>
<table width="700" border="0" cellpadding="0" cellspacing="0">
<col width="100"><col width="300"><col width="300">
<tr><td height="2" bgcolor="#0000ff" colspan="3"></td></tr>
 <tr bgcolor="#F6F6F6">
 <td  align='center'>
<input type='checkbox' name='alldel'   onclick="deletechecks(this.checked)"/>
</td>
<td>아 이 디</td>
 <td>이     름</td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="3"></td></tr>
<c:if test="${empty custuser.rows}">
<tr bgcolor='#F6F6D6'>
  <td colspan='3' align='center'>고객 리스트가 존재하지 않습니다.</td>
</tr>
</c:if>
<!-- varStatus="status"를 이용하여 숫자 파악 자동으로 0부터 인덱스를 부여한다 -->
<c:forEach var='cust' items='${custuser.rows}' varStatus="status">
        <c:choose>
          <c:when test="${status.count%2==0}">
          <c:set var="bgcol" value="#F6F6D6"/>
          </c:when>
          <c:otherwise>
          <c:set var="bgcol" value="#F4F4a4"/>
          </c:otherwise>
        </c:choose>
        <tr bgcolor='${bgcol}'>
          <td align='center' >
<input type='checkbox' name='delck' value='${cust.id}'/></td>
		   <td>${cust.id}</td>
		  <td> <a href='custuserdetail.jsp?id=${cust.id}'>${cust.name}</a>   </td>
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
</table>
</form>
<br/>
<c:url value="index.jsp" var='home'/>
<a href='${home}'>Home</a><br/>
</center>
</body>
</html>