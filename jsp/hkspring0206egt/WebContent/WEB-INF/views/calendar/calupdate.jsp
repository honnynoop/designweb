<%@page import="com.hk.mobile.member.model.HKCalendar"%>
<%@page import="com.hk.mobile.help.JiNaCal"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="java.util.Calendar"%>
<% request.setCharacterEncoding("utf-8") ;%>

<script type="text/javascript">
function modify(seq){
	var f=document.forms[0];
	f.submit();
}
</script>

<%!
//yyyy-MM-dd hh:mm:ss  //Timestamp<-String
//yyyy-MM-dd  //Date<-String
public String toDates(String Rdate){
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분");
	String s=Rdate.substring(0,4)
	+"-"+Rdate.substring(4,6)+"-"+Rdate.substring(6,8)
	+" "+Rdate.substring(8,10)+":"+Rdate.substring(10,12)+":00";
	//java.sql.Date d=java.sql.Date.valueOf(Rdate);
	Timestamp d=Timestamp.valueOf(s);
	return sdf.format(d);
}
public String toOne(String msg){
	return msg.charAt(0)=='0'?msg.charAt(1)+"":msg.trim();
}
%>

<%

Calendar cal=Calendar.getInstance();
int tyear=cal.get(Calendar.YEAR);

HKCalendar dto=(HKCalendar)request.getAttribute("cal");
String year=dto.getWdate().substring(0,4);
String month=toOne(dto.getWdate().substring(4,6));
String day=toOne(dto.getWdate().substring(6,8));
String hour=toOne(dto.getWdate().substring(8,10));
String min=toOne(dto.getWdate().substring(10,12));
%>
<form action="calupdateaf.do" method="post">
<table class="list_table" style="width:85%;">
<colgroup>
<col style="width:200px;" />
<col style="width:auto;" />
</colgroup>
<tr>
<th>아이디<input type="text" name='seq' value='<%=dto.getSeq()%>'/></th>
<td style="text-align: left"><%=dto.getId()%></td>
</tr>
<tr>
<th>제목</th>
<td style="text-align: left" width="50"><input type="text" name='title' size="60" value='<%=dto.getTitle()%>'/></td>
</tr>
<tr>

<tr>
<th>일정</th>
<td style="text-align: left">
<select name='year'>
<%
for(int i=tyear-5; i<tyear+6; i++){
	%>
	<option    <%= year.equals(i+"")?"selected='selected'":"" %>
	    value="<%=i%>"><%=i%></option>
	<% 
}
%>
</select>년
<select name='month'>
<%
for(int i=1; i<=12; i++){
	%>
	<option    <%= month.equals(i+"")?"selected='selected'":"" %>
	    value="<%=i%>"><%=i%></option>
	<% 
}
%>
</select>월
<select name='day'>
<%
for(int i=1; 
i<=cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
	%>
	<option    <%= day.equals(i+"")?"selected='selected'":"" %>
	    value="<%=i%>"><%=i%></option>
	<% 
}
%>
</select>일
<select name='hour'>
<%
for(int i=0; i<24; i++){
	%>
	<option   <%= (hour+"").equals(i+"")?"selected='selected'":"" %>
	 value="<%=i%>"><%=i%></option>
	<% 
}
%>
</select>시

<select name='min'>
<%
for(int i=0; i<60; i++){
	%>
	<option    <%= (min+"").equals(i+"")?"selected='selected'":"" %>
	value="<%=i%>"><%=i%></option>
	<% 
}
%>
</select>분</td>
</tr>

<tr>
<th>내용</th>
<td style="text-align: left"><textarea  name='content' rows="20" cols="60"><%=dto.getContent()%></textarea></td>
</tr>
<tr>
<td colspan="2" align="center">
<input type="button" value='수정' onclick="modify();"/>
</td>
</tr>
</table>
</form>
<%
String url=String.format("%s?year=%s&month=%s",
		"calendar.do",year,month);
%>
<a href='<%=url%>'>일정보기</a>
</body>
</html>