<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="utf-8"/>
<form action="writeboardAf.do" method="post">
<table border="1">
<tr>
<td>아이디</td>
<td><input type="text" name="id" 
   value='${login.id}'  readonly="readonly"   size="40"></td>
</tr>
<tr>
<td>제목</td>
<td><input type="text" name="title" size="50"></td>
</tr>
<tr>
<td>내용</td>
<td>
<textarea rows="10" cols="50" name="content"></textarea>
</td>
</tr>
<tr>
<td colspan="2">
<input type="submit" value="글쓰기" ></td>
</tr>
</table>
</form>
<a href='board.do'>일반게시판</a><br/>
