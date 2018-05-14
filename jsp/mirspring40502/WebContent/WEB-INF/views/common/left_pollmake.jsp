<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="utf-8"/>
<style>
<!--
.menu_table li.menu_item a:hover { background-image:url("<%=request.getContextPath()%>/image/arrow.gif"); background-repeat:no-repeat; background-position:5px 7px; background-color:#FFFFFF; }
-->
</style>
<div class="menu_table">
	<ul style="width:100%;">
<c:if test="${login.auth eq '1'}">	
	<li class="title">투표만들기</li>
	<li class="subtitle">투표</li>
	<li class="menu_item"><a href="#none" onclick="url_pollmake();" title="투표만들기">투표만들기</a></li>			
	<li class="menu_item"><a href="#none" onclick="url_polllist();" title="투표목록">투표목록</a></li>													
</c:if>
<c:if test="${login.auth eq '3'}">	
	<li class="title">투표만들기</li>
	<li class="subtitle">투표</li>		
	<li class="menu_item"><a href="#none" onclick="url_polllist();" title="투표목록">투표목록</a></li>													
</c:if>
	</ul>				
</div>
			