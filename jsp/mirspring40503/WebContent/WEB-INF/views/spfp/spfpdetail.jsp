<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="utf-8"/>
<form name="frmForm" id="_frmForm" method="post" action="">
<input type="hidden" name="seq"   value="${spfpDiary.seq}"/>
<table class="list_table" style="width:85%;">
<colgroup>
<col style="width:200px;" />
<col style="width:auto;" />
</colgroup>
<tbody>	
<tr class="id">
<th>아이디</th>
<td style="text-align: left">
<c:if test="${spfpDiary.team eq 0 }">
<font style='color:#4D6BB3'>■ ${spfpDiary.id}</font>
</c:if>
<c:if test="${spfpDiary.team eq 1 }">
<font style='color:#8700a5'>■ ${spfpDiary.id}</font>
</c:if>
<c:if test="${spfpDiary.team eq 2 }">
<font style='color:#a50000'>■ ${spfpDiary.id}</font>
</c:if>
<c:if test="${spfpDiary.team eq 3 }">
<font style='color:#fffa00'>■ ${spfpDiary.id}</font>
</c:if>
<c:if test="${spfpDiary.team eq 4 }">
<font style='color:#9effc9'>■ ${spfpDiary.id}</font>
</c:if>
</td>
</tr>
<tr>
<th>작성일</th><td style="text-align: left">${spfpDiary.wdate}</td>
</tr>
<tr>
<th>참고 사이트</th><td style="text-align: left">${spfpDiary.ref}</td>
</tr>
<tr>
<th>사진</th>
<td style="text-align: left">
<c:if test='${fn:contains(spfpDiary.img, "back" )}'>
<span>등록된 사진이 없어요...</span>
</c:if>
<c:if test='${!fn:contains(spfpDiary.img, "back") }'>
<img src="${path}${spfpDiary.img}" />
</c:if>
</td>
</tr>
<tr>
<th>내용</th>
<td style="text-align: left"><textarea rows="10" cols="50" 
name='content' id="_content">${spfpDiary.content}</textarea></td>
</tr>
<tr>
<td colspan="2" style="height:50px; text-align:center;">
<span>
<c:if test="${spfpDiary.id eq login.id}">
    <a href="#none" id="_btnUpdate" title="글수정하기"><img src="image/bupdate.png" alt="수정하기" /></a>
    <a href="#none" id="_btnDelete" title="글삭제하기"><img src="image/del.png" alt="삭제하기" /></a>
	</c:if>
	<br/><a href="#none" id="_btnReply" title="PDF">PDF 저장</a>
</span>
</td>
</tr>
</tbody>
</table>
</form>
<script type="text/javascript">
$("#_btnUpdate").click(function() {	
	alert('글수정하기');	
	//submitContents($("#_frmForm"),'bbsupdate.do');
	$("#_frmForm").attr({ "target":"_self", "action":"spfpupdate.do" }).submit();
});
$("#_btnDelete").click(function() {	
	alert('글삭제하기');	
	//submitContents($("#_frmForm"),'bbsupdate.do');
	$("#_frmForm").attr({ "target":"_self", "action":"spfpdelete.do" }).submit();
});
$("#_btnReply").click(function() {	
	alert('저장');	
	//submitContents($("#_frmForm"),'bbsreply.do');
	$("#_frmForm").attr({ "target":"_self", "action":"spfpPDF.do" }).submit();
});
</script>
