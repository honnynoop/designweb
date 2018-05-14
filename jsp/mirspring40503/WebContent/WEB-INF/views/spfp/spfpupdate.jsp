<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:requestEncoding value="UTF-8"/>

<form name="frmForm" id="_frmForm" action="" method="post" 
enctype="multipart/form-data">
<input type="hidden" name="seq"   value="${spfpDiary.seq}"/>
<table class="list_table" style="width:85%;">
<colgroup>
<col style="width:200px;" />
<col style="width:auto;" />
</colgroup>
<tr>
<th>아이디</th>
<td style="text-align: left">${login.id}</td>
</tr>
<tr>
<th>참고 사이트</th>
<td style="text-align: left"><input type="text" name='ref' size="50" value="${spfpDiary.ref}"/></td>
</tr>
<tr>
<th>파일업로드</th>
<td style="text-align: left"><input type="text" name='namefile' value="${spfpDiary.img}" size="50" readonly="readonly"/>
<input type="file" name="fileload" style=" width : 400px;"></td>
</tr>
<tr>
<th>내용</th>
<td style="text-align: left"><textarea rows="10" cols="50" name='content' id="_content">${spfpDiary.content}</textarea></td>
</tr>
<tr>
<td colspan="2" style="height:50px; text-align:center;">
	<span><a href="#none" id="_btnLogin" title="수정하기"><img src="image/bupdate.png" alt="로그인" /></a>
</span>
</td>
</tr>
</table>
</form>
<script type="text/javascript">
$("#_btnLogin").click(function() {	
	alert('수정하기');	
	$("#_frmForm").attr({ "target":"_self", "action":"spfpupdateAf.do" }).submit();
});
</script>
