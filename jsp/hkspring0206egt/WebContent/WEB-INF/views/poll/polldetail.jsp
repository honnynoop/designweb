<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="utf-8"/>

<form action="polling.do" method="post">
<table class="list_table" style="width:95%;">
<colgroup>
<col width="200px"/><col width="500px"/>
</colgroup>
<tr>
<th>투표번호</th>
<td style="text-align: left"><input type="text" name='pollid' value='${poll.pollid}' size="50"
readonly="readonly"/></td>
</tr>
<tr>
<th>아이디</th>
<td style="text-align: left"><input type="text" name='id' value='${login.id}' size="50"
readonly="readonly"/></td>
</tr>
<tr>
<th>투표기한</th>
<td style="text-align: left">
${poll.sdate}~${poll.edate}
</td>
</tr>
<tr>
<th>투표 내용</th>
<td style="text-align: left"><textarea name='question' rows="10" readonly="readonly"
cols="50">${poll.question}</textarea></td>
</tr>

<tr>
<th>투표 문항수</th>
<td style="text-align: left">
${poll.itemcount}개
</td>
</tr>

<tr>
<th>투표 상세 문항</th>
<td style="text-align: left">
<c:forEach items="${pollsublist}" var="psub" varStatus="vs">
${vs.count}번 :<input type="radio" name='pollsubid' 
 ${vs.count==1 ?"checked='checked'":""} 
value='${psub.pollsubid}'/>
<input type="text"   size='60' readonly="readonly" 
name='answer'  value='${psub.answer}'/>
<br/>
</c:forEach>
</td>
</tr>

<tr align="center">
<td colspan="2"><input type="submit" value='투표하기'/></td>
</tr>
</table>
</form>
<a href='polllist.do'>투표목록</a><br/>
</body>
</html>




