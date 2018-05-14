<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="utf-8"/>


<div id='_youtube_'>
<iframe id='_youtube' width="640" height="360" src='http://www.youtube.com/embed/'  frameborder="0"  allowfullscreen></iframe>
</div>

<table class="list_table" style="width:85%;">
<colgroup>
<col style="width:70px;" />
<col style="width:auto;" />
<col style="width:100px;" />
</colgroup>
<thead>
<tr>
<th>순서</th> <th>제목</th> <th>유투브 고유번호</th> 
</tr>
</thead>
<tbody>	
<c:if test="${empty bbslist}">
<tr>
	<td colspan="3">작성된 글이 없습니다.</td>
</tr>
</c:if>
<c:forEach items="${bbslist}" var="bbs" varStatus="vs">
	<tr class="_hover_tr">
	<td>${vs.count}</td> 
	<td style="text-align: left">${bbs.title}</td>
	<td><div class='c_vname' vname='${bbs.vname}'>${bbs.vname}</div></td> 
	</tr>
</c:forEach>
</tbody>
</table>

<script type="text/javascript">
$(document).ready(function() {
	$("#_youtube_").hide();
	$("._hover_tr").mouseover(function() {
		$(this).children().css("background-color","#F0F5FF");
	}).mouseout(function() {
		$(this).children().css("background-color","#FFFFFF");
	});				
});
$(".c_vname").click(function() {
	$("#_youtube_").show();
	$("#_youtube").attr({ "src":"http://www.youtube.com/embed/"+$(this).attr("vname")});
});


function goPage(pageNumber) {	
	$("#_pageNumber").val(pageNumber) ;
	$("#_frmFormSearch").attr("target","_self").attr("action","bbslist.do").submit();
}

</script>