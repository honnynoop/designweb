<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="utf-8"/>
<table class="list_table" style="width: 85%;">
<colgroup>
<col style="width: 70px;" />
<col style="width: auto;" />
<%-- <col style="width: 100px;" /> --%>
</colgroup>
<thead>
<tr>
<th>아이디</th>
<th>기존 팀</th>
<!-- <th>팀 변경</th> -->
</tr>
</thead>
<c:forEach items="${members }" var="member">
<tr>
<td><input type="hidden" name="id" value="${member.id }" />${member.id }</td>
<td>${member.team }</td>
<!-- <td> -->
<!-- <select name='team'> -->
<%-- <c:forEach begin="1" end="4" step="1" varStatus="vs"> --%>
<%-- <c:if test="${vs.count eq member.team }"> --%>
<%-- <option value="${vs.count }" selected="selected">${vs.count }</option> --%>
<%-- </c:if> --%>
<%-- <c:if test="${vs.count ne member.team }"> --%>
<%-- <option value="${vs.count }">${vs.count }</option> --%>
<%-- </c:if> --%>
<%-- </c:forEach> --%>
<!-- </select> -->
<!-- </td> -->
</tr>
</c:forEach>
</table>
<form name="frmForm" id="_frmForm" method="post" action="">
<select name='id'>
<c:forEach items="${members }" var="member" varStatus="vss">
<option value="${member.id }">${member.id }</option>
</c:forEach>
</select>
<select name='team'>
<c:forEach begin="1" end="4" step="1" varStatus="vs">
<option value="${vs.count }">${vs.count }</option>
</c:forEach>
<!-- <option value="1">1</option> -->
<!-- <option value="2">2</option> -->
<!-- <option value="3">3</option> -->
<!-- <option value="4">4</option> -->
</select>
</form>
<a href="#none" id="_btnUpdate" title="글수정하기"><img src="image/bupdate.png" alt="수정하기" /></a>
<script type="text/javascript">
$("#_btnUpdate").click(function() {	
	alert('팀 변경');
	$("#_frmForm").attr({ "target":"_self", "action":"spfpteamAf.do" }).submit();
});
</script>