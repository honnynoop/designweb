<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>
<table class="list_table" style="width:85%;">
<colgroup>
<col width="50"/><col width="100"/><col width="300"/><col width="50"/>
<col width="50"/><col width="50"/><col width="100"/><col width="50"/>
</colgroup>
<thead>
<tr>
<th>번호</th><th>작성자</th><th>제목</th><th>다운로드</th>
<th>조회수</th><th>다운수</th><th>작성일</th><th>삭제</th>
</tr>
</thead>


<c:forEach var="pds" items="${pdslist}" varStatus="pdsS">
<tr class="_hover_tr">
<td>${pdsS.count}</td><td>${pds.id}</td>
<td style="text-align: left"><a href='pdsdetail.do?seq=${pds.seq}'>${pds.title}</a></td>
<td>
<input type="button" name="btnDown2" value="다운로드"
onclick="filedowns('${pds.filename}','${pds.seq}')"/>
</td>
<td>${pds.readcount}</td><td>${pds.downcount}</td><td>
<font size="1">
${pds.regdate}</font>
</td>
<td>
<img src="./image/del.png"  data-attache_file_name="${pds.seq}"
class="btn_fileDelete"/>
</td>
</tr>
</c:forEach>
</table>
<form name="frmForm" id="_frmForm" method="get" action="">
</form>
<form name="delfileup" action="fileDownload.do" method="post">
<input type="hidden" name="filename" />
<input type="hidden" name="seq" />
</form>
<form name="frmForm2" id="_frmForm2" action="pdsdel.do" method="post">
<input type="hidden" id="_seq" name='seq' />
</form>
<div id="buttons_wrap">
	<span class="button blue">
	<button type="button" id="_btnAdd">글쓰기</button></span>
</div>


<script type="text/javascript">
function filedowns(filename,seq){
	alert(filename+'  '+seq);
	var doc=document.delfileup;
	doc.filename.value=filename;
	doc.seq.value=seq;
	doc.submit();
}
$("#_btnAdd").click(function() {	
	alert('글쓰기');	
	$("#_frmForm").attr({ "target":"_self", "action":"pdswrite.do" }).submit();
});
$(".btn_fileDelete").click(function() {
	if (window.confirm("파일을 삭제 하시겠습니까?"))
	{			
		$("#_seq").val($(this).attr("data-attache_file_name"));
		$("#_frmForm2").attr( {"action":"pdsdel.do"} ).submit();
	}
});
</script>