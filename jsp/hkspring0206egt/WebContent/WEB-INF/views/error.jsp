<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:requestEncoding value="UTF-8"/>
<html>
<head>
<title>error</title>
</head>
<body> 

<font color='red'><b>에러.</b> 
</font>가 발생햇습니다
<h1>msg:<font color='red'>
${exception}</font></h1><br />
<a href='${url}' > 전 페이지 로  </a> 
</body>
</html>