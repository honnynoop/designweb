<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="utf-8"/>

<c:if test="${(empty login ) or (login.id eq '') }">
<%
	session.invalidate();
%>
<script type="text/javascript">
alert("로그인해 주십시요.");		
self.location.href="login.do";
</script>
<%
if (true) return;
%>
</c:if>
