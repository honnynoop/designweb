<%@page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="euc-kr"/>
<html>
<head>
<title>CustUser Lists</title>
</head>
<c:set var='custerror' value='${requestScope.errors}'/>
<body>
<center>
<div id="Content">
<table width="600" border="0" cellpadding="0" cellspacing="0">
<col width="150"><col width="150"><col width="300">
<tr><td height="2" bgcolor="#c0c0c0" colspan="3"></td></tr>
<tr bgcolor='#F6F6D6'>
		  <td>���� �޼���</td>
		  <td><c:out value='${custerror.errorMessage}'/></td>
</tr>
<tr><td height="1" bgcolor="#c0c0c0" colspan="3"></td></tr>
<tr bgcolor='#F6F6D6'>
		  <td>���� ����</td>
		  <td><c:out value='${custerror.errorType}'/></td>
</tr>
<tr><td height="1" bgcolor="#c0c0c0" colspan="3"></td></tr>
<tr><td  colspan="3">��뿡 ������ ����� �˼��մϴ�. �ٽ� ������ ���ð� , 
���� ������ �߻��ϸ� ������Ź�帳�ϴ�.</td></tr>

<tr><td height="1" bgcolor="#c0c0c0" colspan="3"></td></tr>
</table>
<br/>
<c:url value="./custCtr" var='controls'>
<c:param name="command" value="list"/>
</c:url>
<a href='${controls}'>Home</a><br/>
</div>
</center>
</body>
</html>