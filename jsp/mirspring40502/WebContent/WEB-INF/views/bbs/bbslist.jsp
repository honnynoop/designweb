<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="utf-8"/>
<!-- <head> -->
<!-- 	<link rel="stylesheet" href="./colorpicker/css/colorpicker.css" type="text/css" /> -->
<!--     <link rel="stylesheet" media="screen" type="text/css" href="./colorpicker/css/layout.css" /> -->
<!-- 	<script type="text/javascript" src="./colorpicker/js/jquery.js"></script> -->
<!-- 	<script type="text/javascript" src="./colorpicker/js/colorpicker.js"></script> -->
<!--     <script type="text/javascript" src="./colorpicker/js/eye.js"></script> -->
<!--     <script type="text/javascript" src="./colorpicker/js/utils.js"></script> -->
<!--     <script type="text/javascript" src="./colorpicker/js/layout.js?ver=1.0.2"></script> -->
<!-- </head> -->

<div class="box_border" style="margin-top:5px; margin-bottom: 10px;">
<form name="frmForm1" id="_frmFormSearch" method="post" action="">
<table style="margin-left:auto; margin-right:auto; margin-top:3px; margin-bottom:3px; border:0; padding:0;">
<tr>
	<td>검색 : </td>
	<td style="padding-left:5px;">
	<select id="_s_category" name="s_category">
		<option value=""  <c:if test="${(s_category eq '') or empty s_category}"> selected="selected" </c:if>>선택</option>
		<option value="title" <c:if test="${s_category eq 'title'}"> selected="selected" </c:if>>제목</option>
		<option value="contents" <c:if test="${s_category eq 'contents'}"> selected="selected" </c:if>>내용</option>								
	</select>
</td>
<td style="padding-left:5px;"><input type="text" id="_s_keyword" name="s_keyword" value="${s_keyword}"/></td>
<td style="padding-left:5px;"><span class="button blue"><button type="button" id="_btnSearch"> 검색 </button></span></td>
		</tr>
	</table>
	<input type="hidden" name="pageNumber" id="_pageNumber" value="${(empty pageNumber)?0:pageNumber}"/>						
	<input type="hidden" name="recordCountPerPage" id="_recordCountPerPage" value="${(empty recordCountPerPage)?10:recordCountPerPage}"/>						
	</form>
</div>
<hr/>
<a href='downloadPDF.do'>pdf</a>
<a href='downloadExcel.do'>excel</a>
<hr/>

<jsp:useBean id="ubbs" class="com.mirhenge.jyl.mboard.help.BbsBean"/>
<table class="list_table" style="width:85%;">
<colgroup>
<col style="width:70px;" />
<col style="width:auto;" />
<col style="width:100px;" />
</colgroup>
<thead>
<tr>
<th>순서</th> <th>제목</th> <th>작성자</th> 
</tr>
</thead>
<tbody>	
<c:if test="${empty bbslist}">
<tr>
	<td colspan="3">작성된 글이 없습니다.</td>
</tr>
</c:if>
<c:forEach items="${bbslist}" var="bbs" varStatus="vs">
<jsp:setProperty property="depth" name="ubbs" 
value="${bbs.depth}"/>
	<tr class="_hover_tr">
	<td>${vs.count}</td> 
	<td style="text-align: left"><jsp:getProperty property="reply" 
name="ubbs"/><a href='bbsdetail.do?seq=${bbs.seq}'>
${bbs.title}</a></td>
	<td>${bbs.id}</td> 
	</tr>
</c:forEach>
</tbody>
</table>
<div id="buttons_wrap">
	<span class="button blue">
	<button type="button" id="_btnAdd">글쓰기</button></span>
</div>
<!-- <a href='bbswrite.do'>글쓰기</a> -->

<div id="paging_wrap">
<jsp:include page="/WEB-INF/views/common/paging.jsp" flush="false">
	<jsp:param value="${pageNumber}" name="pageNumber"/>
	<jsp:param value="${pageCountPerScreen}" name="pageCountPerScreen"/>
	<jsp:param value="${recordCountPerPage}" name="recordCountPerPage"/>
	<jsp:param value="${totalRecordCount}" name="totalRecordCount"/>							
</jsp:include>				
</div>
<form name="frmForm2" id="_frmForm" method="get" action="bbswrite.do">
</form>

<!-- <hr/> -->
<!-- <hr/> -->
<!-- <p><input type="text" maxlength="6" size="6" id="colorpickerField1" value="00ff00" /></p> -->
<!-- <p><input type="text" maxlength="6" size="6" id="colorpickerField3" value="0000ff" /></p> -->
<!-- <p><input type="text" maxlength="6" size="6" id="colorpickerField2" value="ff0000" /></p> -->

<script type="text/javascript">
$(document).ready(function() {
	$("._hover_tr").mouseover(function() {
		$(this).children().css("background-color","#F0F5FF");
	}).mouseout(function() {
		$(this).children().css("background-color","#FFFFFF");
	});				
});
$("#_btnAdd").click(function() {	
	alert('글쓰기');	
	$("#_frmForm").attr({ "target":"_self", "action":"bbswrite.do" }).submit();
});
$("#_btnSearch").click(function() {
	//alert('search');						
	$("#_frmFormSearch").attr({ "target":"_self", "action":"bbslist.do" }).submit();
});
function goPage(pageNumber) {	
	$("#_pageNumber").val(pageNumber) ;
	$("#_frmFormSearch").attr("target","_self").attr("action","bbslist.do").submit();
}
</script>