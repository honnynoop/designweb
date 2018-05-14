<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="utf-8"/>
<div id="top_menu_wrap">
	<div id="_top_menu">
		<ul class="navi">
<c:if test="${login.auth eq '3'}">
<li><a href="#none" onclick="url_bbslist();" title="답변형게시판">답변형게시판</a></li>							
<li><a href="#none" onclick="url_pdslist();" title=자료실>자료실</a></li>	
<li><a href="#none" onclick="url_calendar();" title="일정관리">일정관리</a></li>
<li><a href="#none" onclick="url_polllist();" title="투표하기">투표하기</a></li>	
<li><a href="#none" onclick="url_ajax();" title="ajax">ajax</a></li>
</c:if>
<c:if test="${login.auth eq '1'}">
<li><a href="#none" onclick="url_bbslist();" title="답변형게시판">답변형게시판</a></li>							
<li><a href="#none" onclick="url_pdslist();" title=자료실>자료실</a></li>	
<li><a href="#none" onclick="url_polllist();" title="투표만들기">투표만들기</a></li>							
</c:if>
		</ul>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	if ($("#content_wrap").height() < 600) $("#content_wrap").height(600);
});
</script>