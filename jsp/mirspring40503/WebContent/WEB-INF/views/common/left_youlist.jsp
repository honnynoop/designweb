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
	<li class="title">유투브</li>
	<li class="subtitle">유투브</li>
	<li class="menu_item"><a href="#none" onclick="url_yutube();" title="유투브찾기">유투브찾기</a></li>		
	<li class="menu_item"><a href="#none" onclick="url_yutubelist();" title="저장된유투브">저장된유투브</a></li>		
	</ul>				
</div>
			