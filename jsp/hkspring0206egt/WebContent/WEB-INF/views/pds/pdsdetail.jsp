<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>

<table class="list_table" style="width:85%;">
<colgroup>
<col width="200"/><col width="500"/>
</colgroup>
<tr>
<th>게시자</th>
<td style="text-align: left">${pds.id}</td>
</tr>
<tr>
<th>제목</th>
<td style="text-align: left">${pds.title}</td>
</tr>
<tr>
<th>다운로드</th>
<td style="text-align: left">
<input type="button" name="btnDown2" value="다운로드"
onclick="filedowns('${pds.filename}','${pds.seq}')"/>
			 </td>
</tr>
<tr>
<th>조회수</th>
<td style="text-align: left">${pds.readcount}</td>
</tr>
<tr>
<th>다운수</th>
<td style="text-align: left">${pds.downcount}</td>
</tr>
<tr>
<th>파일이름</th>
<td style="text-align: left">${pds.filename}</td>
</tr>
<tr>
<th>등록일</th>
<td style="text-align: left">${pds.regdate}</td>
</tr>
<tr>
<th>내용</th>
<td style="text-align: left"><textarea rows="10" cols="50" 
name='content' id="_content">${pds.content}</textarea></td>
</tr>
</table>
<form name="delfileup" action="fileDownload.do" method="post">
<input type="hidden" name="filename" />
<input type="hidden" name="seq" />
</form>
<form name="frmForm2" id="_frmForm2" action="pdsupdate.do" method="post">
<input type="hidden" name="seq" value="${pds.seq}"/>
</form>
<div id="buttons_wrap">
	<span class="button blue">
	<button type="button" id="_btnAdd">수정하기</button></span>
</div>

<script type="text/javascript">
$("#_btnAdd").click(function() {	
	alert('수정하기');	
	$("#_frmForm2").attr({ "target":"_self", "action":"pdsupdate.do" }).submit();
});
function filedowns(filename,pdsid){
	alert(filename+'  '+pdsid);
	var doc=document.delfileup;
	doc.filename.value=filename;
	doc.seq.value=pdsid;
	doc.submit();
}

//'저장' 버튼을 누르는 등 저장을 위한 액션을 했을 때 submitContents가 호출된다고 가정한다.
function submitContents(elClickedObj) {
   // 에디터의 내용이 textarea에 적용된다.
   oEditors[0].exec("UPDATE_CONTENTS_FIELD", []);
 
   // 에디터에 입력된 내용의 검증은 이곳에서
   // document.getElementById("ir1").value 값을 이용해서 처리한다.
   //alert(ares+'------------------------');
   try{
       // 이 라인은 현재 사용 중인 폼에 따라 달라질 수 있다.
       //elClickedObj.action.value=ares;
       elClickedObj.submit();
   }catch(e){}
}

var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "_content",
    sSkinURI: "./se2/SmartEditor2Skin.html",
    fCreator: "createSEditor2"
});
</script>
