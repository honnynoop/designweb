<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="utf-8"/>

<h3>글목록</h3>
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
<hr/>


<table border="1" >
<col width="100px"/>
<col width="100px"/>
<col width="200px"/>
<tr>
	<td>번호</td>
	<td>작성자</td>
	<td>제목</td>
</tr>
<c:forEach items="${boardlist}" var="board" varStatus="vs">
<tr>
<td>[${vs.count}]${board.seq}</td>
<td>${board.id}</td>
<td><a href='boarddetail.do?seq=${board.seq}'>${board.title}</a></td>
</tr>
</c:forEach>
</table>

<table>
<col width="400px"/>
<tr align="right">
	<td><a href='writeboard.do'>글쓰기</a></td>
</tr>
</table>
<hr/>
<jsp:include page="/WEB-INF/views/common/paging.jsp" flush="false">
	<jsp:param value="${pageNumber}" name="pageNumber"/>
	<jsp:param value="${pageCountPerScreen}" name="pageCountPerScreen"/>
	<jsp:param value="${recordCountPerPage}" name="recordCountPerPage"/>
	<jsp:param value="${totalRecordCount}" name="totalRecordCount"/>							
</jsp:include>	
<hr/>

<script type="text/javascript">
$("#_btnSearch").click(function() {					
	$("#_frmFormSearch").attr({ "target":"_self", "action":"board.do" }).submit();
});
function goPage(pageNumber) {	
	$("#_pageNumber").val(pageNumber) ;
	$("#_frmFormSearch").attr("target","_self").attr("action","board.do").submit();
}
</script>