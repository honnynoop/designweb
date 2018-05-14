<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:requestEncoding value="UTF-8"/>

<form name="frmForm" id="_frmForm" action="" method="post" 
enctype="multipart/form-data">
<input type="hidden" name='seq' value="${pds.seq}"/>
<table class="list_table" style="width:85%;">
<colgroup>
<col style="width:200px;" />
<col style="width:auto;" />
</colgroup>
<tr>
<th>아이디</th>
<td style="text-align: left">${pds.id}</td>
</tr>
<tr>
<th>제목</th>
<td style="text-align: left">
<input type="text" name='title' value="${pds.title}" size="50"/></td>
</tr>
<tr>
<th>파일업로드</th>
<td style="text-align: left"><input type="text" name='namefile' value="${pds.filename}" size="50" readonly="readonly"/>
<input type="file" name="fileload"  style=" width : 400px;"></td>
</tr>
<tr>
<th>내용</th>
<td style="text-align: left">
<textarea rows="10" cols="50" 
name='content' id="_content">${pds.title}</textarea></td>
</tr>
<tr>
<td colspan="2" style="height:50px; text-align:center;">
	<span><a href="#none" id="_btnLogin" title="수정완료"><img src="image/bupdate.png" alt="수정완료" /></a>
</span>
</td>
</tr>
</table>
</form>
<script type="text/javascript">
$("#_btnLogin").click(function() {	
	alert('수정하기');	
	//submitContents($("#_frmForm"));
	$("#_frmForm").attr({ "target":"_self", "action":"pdsupdateAf.do" }).submit();
});
//'저장' 버튼을 누르는 등 저장을 위한 액션을 했을 때 submitContents가 호출된다고 가정한다.
function submitContents(elClickedObj) {
   // 에디터의 내용이 textarea에 적용된다.
   oEditors[0].exec("UPDATE_CONTENTS_FIELD", []);
 
   // 에디터에 입력된 내용의 검증은 이곳에서
   // document.getElementById("ir1").value 값을 이용해서 처리한다.
 
   try{
       // 이 라인은 현재 사용 중인 폼에 따라 달라질 수 있다.
       //elClickedObj.form.submit();
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
