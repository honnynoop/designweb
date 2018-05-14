<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="utf-8"/>

<div class="box_border" style="margin-top:5px; margin-bottom: 10px;">
<form name="frmForm1" id="_frmForm" method="post" action="">
<table style="margin-left:auto; margin-right:auto; margin-top:3px; margin-bottom:3px; border:0; padding:0;">
<tr>
	<td>검색 : </td>
<td style="padding-left:5px;"><input type="text" id="_s_keyword" name="s_keyword" value="${empty s_keyword ? '':s_keyword}"/></td>
<td style="padding-left:5px;"><span class="button blue"><button type="button" id="_btnSearch"> 검색 </button></span></td>
		</tr>
	</table>
	</form>
</div>

<div id='_youtube_'>
<iframe id='_youtube' width="640" height="360" src='http://www.youtube.com/embed/'  frameborder="0"  allowfullscreen></iframe>
</div>

<table class="list_table" style="width:85%;">
<colgroup>
<col style="width:70px;" />
<col style="width:auto;" />
<col style="width:100px;" />
<col style="width:30px;" />
</colgroup>
<thead>
<tr>
<th>순서</th> <th>제목</th> <th>유투브 고유번호</th> <th>저장</th> 
</tr>
</thead>
<tbody>	
<c:if test="${empty bbslist}">
<tr>
	<td colspan="4">작성된 글이 없습니다.</td>
</tr>
</c:if>
<c:forEach items="${bbslist}" var="bbs" varStatus="vs">
	<tr class="_hover_tr">
	<td>${vs.count}</td> 
	<td style="text-align: left">${bbs.title}</td>
	<td id='_v${bbs.vname}ed2'   onclick="getyoutube2('${bbs.vname}')"><div class='c_vname' vname='${bbs.vname}'>${bbs.vname}</div></td> 
	<td onmouseover="getyoutube('${login.id}','${bbs.vname}')" >
	<img alt="저장" src="./image/save.png" class='ck_seq' vname='${bbs.vname}' id='_v${bbs.vname}ed'
	onclick="saveyou('${login.id}','${bbs.vname}','${bbs.title}','${empty s_keyword ? '':s_keyword}')"/></td> 
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

$("#_btnSearch").click(function() {
	alert('검색');	
	$("#_frmForm").attr({ "target":"_self", "action":"yutube.do" }).submit();
});
$(".c_vname").click(function() {
	$("#_youtube_").show();
	$("#_youtube").attr({ "src":"http://www.youtube.com/embed/"+$(this).attr("vname")});
});

function saveyou(id, vname, title, category){
	$.ajax({
		   type: "POST",
url: "<%=application.getContextPath()%>/youtubesave.do",
		   async: true,	
		   data: "id="+id+"&vname="+vname+"&title="+title+"&category="+category,	
		   success: function(msg){
			    $("#_v"+vname+"ed").hide();
				alert('성공적으로 '+msg.vname+'가 저장되었습니다.');
		   }
	 });
}
function getyoutube2(vname){
	$("#_v"+vname+"ed2").css("background-color","#FF0000");
}
function getyoutube(id, vname){
	$.ajax({
		   type: "POST",
url: "<%=application.getContextPath()%>/getyoutube.do",
		   async: true,	
		   data: "id="+id+"&vname="+vname,	
		   success: function(msg){
			  // alert(msg.message);
				if(msg.message=='SUCS'){
					//$("#_v"+vname).show();
					$("#_v"+vname+"ed").hide();
				}else{
					//$("#_v"+vname).hide();
					$("#_v"+vname+"ed").show();
				}
		   }
	 });
}
function goPage(pageNumber) {	
	$("#_pageNumber").val(pageNumber) ;
	$("#_frmFormSearch").attr("target","_self").attr("action","bbslist.do").submit();
}

</script>