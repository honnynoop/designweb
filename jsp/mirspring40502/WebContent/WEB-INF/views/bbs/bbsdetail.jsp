<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="utf-8"/>
<form name="frmForm" id="_frmForm" method="post" action="">
<table class="list_table" style="width:85%;">
<input type="hidden" name="seq"   value="${bbs.seq}"/>
<colgroup>
<col style="width:200px;" />
<col style="width:auto;" />
</colgroup>
<tbody>	
<tr class="id">
<th>아이디</th>
<td style="text-align: left">${bbs.id}</td>
</tr>
<tr>
<th>제목</th><td style="text-align: left">${bbs.title}</td>
</tr>
<tr>
<th>작성일</th><td style="text-align: left">${bbs.wdate}</td>
</tr>
<tr>
<th>내용</th>
<td style="text-align: left"><textarea rows="10" cols="50" 
name='content' id="_content">${bbs.content}</textarea></td>
</tr>
<tr>
<td colspan="2" style="height:50px; text-align:center;">
<span>
<c:if test="${bbs.id eq login.id}">
    <a href="#none" id="_btnUpdate" title="글수정하기"><img src="image/bupdate.png" alt="수정하기" /></a>
	</c:if>
	<a href="#none" id="_btnReply" title="답글달기"><img src="image/breply.png" alt="답글달기" /></a>
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
	$("#_frmForm").attr({ "target":"_self", "action":"bbsupdate.do" }).submit();
});
$("#_btnReply").click(function() {	
	alert('답글달기');	
	//submitContents($("#_frmForm"),'bbsreply.do');
	$("#_frmForm").attr({ "target":"_self", "action":"bbsreply.do" }).submit();
});
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
