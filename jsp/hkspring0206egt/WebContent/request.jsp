<%@page import="java.util.Enumeration"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="utf-8"/>
<!DOCTYPE html>
<html>
<head>
<title>mobile or web</title>
</head>
<body>
<table border="1">
<colgroup>
<col style="width:200px;" />
<col style="width:auto;" />
</colgroup>
<%
Enumeration<String> enums= request.getHeaderNames();
while(enums.hasMoreElements()){
	
	String key=enums.nextElement();
	String value=request.getHeader(key);
	%>
	<tr>
		<td><%=key %></td>
		<td><%=value %></td>
	</tr>
	<% 
}
%>
</table>
<% 
String agent=request.getHeader("user-agent");
if(agent.toLowerCase().indexOf("android")!=-1 ||
agent.toLowerCase().indexOf("iphone")!=-1){
	%>
	<%=agent %><br/><%="mobile" %>
	<% 
}else{
	%>
	<%=agent %><br/><%="web" %>
	<% 
}
%>

</body>
</html>