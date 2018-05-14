<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="utf-8"/>
<style>
<!--
.menu_table li.menu_item a:hover { background-image:url("<%=request.getContextPath()%>/image/arrow.gif"); background-repeat:no-repeat; background-position:5px 7px; background-color:#FFFFFF; }
-->
.team {
 text-align: left;
 border-color: #77B5FF;
 border-width: 1px;
 border-style: solid;
 background-color: #d1d1d1d;
 font-size: 15pt;
 font-weight: bold;
 background-color: #D1E7FC;
}
#team0 {
color:#4D6BB3;
}
#team1 {
color:#8700a5;
}
#team2 {
color:#a50000;
}
#team3 {
color:#fffa00;
background-color: SILVER;
}
#team4 {
color:#9effc9;
background-color: SILVER;
}
#prev {
 white-space: pre-wrap;
 word-break: break-all;
 width: 300px;
 background-color: white;
 border-style: solid;
 border-color: black;
 border-width: 1px;
}
</style>
<div class="menu_table">
	<ul style="width:100%;">
	<li class="title">일지게시판</li>
	<li class="subtitle">일지게시판</li>
	<li class="menu_item"><a href="#none" onclick="url_spfpcal();" title="글목록">일지 목록</a></li>		
	<li class="menu_item"><a href="#none" onclick="url_spfpwrite();" title="글쓰기">일지 쓰기</a></li>
	<c:if test="${login.auth eq '1'}">
	<li class="menu_item"><a href="#none" onclick="url_spfpteam();" title="팀 바꾸기">팀 바꾸기</a></li>
	</c:if>						
	</ul>				
</div>
<div class="team">
<br/>
<p>&nbsp;<font id="team0">■ 팀 없음</font></p>
<p>&nbsp;<font id="team1">■ 1 팀</font></p>
<p>&nbsp;<font id="team2">■ 2 팀</font></p>
<p>&nbsp;<font id="team3">■ 3 팀</font></p>
<p>&nbsp;<font id="team4">■ 4 팀</font></p>
<br/>
</div>
<div id="prev" style="visibility:hidden">
<p>prev</p>
</div>