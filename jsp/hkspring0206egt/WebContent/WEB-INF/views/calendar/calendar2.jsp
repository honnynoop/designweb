<%@page import="com.hk.mobile.help.JiNaCal"%>
<%@page import="com.hk.mobile.help.CalendarUtil"%>
<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="utf-8"/>
<style>
table {
    border-collapse: collapse;
}

table, td, th {
    border: 1px solid black;
}
.sunday{
color: red; 
text-align: left;
vertical-align: top;
}
.satday{
color: blue; 
text-align: left;
vertical-align: top;
}
.otherday{
color: black; 
text-align: left;
vertical-align: top;
}
.days2{
font-size:20px;
color: #4D6BB3; 
text-align: center;
vertical-align: middle;
}
.days3{
font-size:10px;
color: #4D6BB3; 
text-align: center;
vertical-align: middle;
}
.days4{
align: center;
text-align: center;
vertical-align: middle;
}
.innerTable {
    border: 0px ;
}
</style>
<h3>일정관리</h3>
<%
JiNaCal jcal=(JiNaCal)request.getAttribute("jcal");

int dayOfWeek=jcal.getDayOfweek();//1일 요일1~7
int lastDayOfMonth=jcal.getLastDay();

int year=jcal.getYear();
int month=jcal.getMonth();

String pp=String.format("<a href='./%s?year=%d&month=%d'><img src='image/left.gif'/></a>",
		"calendar2.do",year-1,month);
String p=String.format("<a href='./%s?year=%d&month=%d'><img src='image/prec.gif'/></a>",
		"calendar2.do",year,month-1);
String n=String.format("<a href='./%s?year=%d&month=%d'><img src='image/next.gif'/></a>",
		"calendar2.do",year,month+1);
String nn=String.format("<a href='./%s?year=%d&month=%d'><img src='image/last.gif'/></a>",
		"calendar2.do",year+1,month);

Calendar cal=Calendar.getInstance();

cal.set(year, month-1, 1);
int lastDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
%>
<div class="list_table" >
<table align="center">
<col width="40px"/>
<col width="40px"/>
<col width="40px"/>
<col width="40px"/>
<col width="40px"/>
<col width="40px"/>
<col width="40px"/>
<tr height="40px">
<td class="days2" colspan="7"><%=pp%><%=p%><font color="red" style="font-size: 20"><%=String.format("%d년&nbsp;&nbsp;%d월",year,month) %></font><%=n%><%=nn%></td>
</tr>
<tr height="40px" >
<th class="days2">일</th><th class="days2">월</th>
<th class="days2">화</th><th class="days2">수</th><th class="days2">목</th>
<th class="days2">금</th><th class="days2">토</th>
</tr>
<tr height="40px">
    <%
   for(int i=1; i<dayOfWeek; i++){
	   %>
	   <td>&nbsp;</td>
	   <% 
   }
    for(int i=1; i<= lastDay; i++){
    	String wdate=CalendarUtil.yyyymmdd(year, month, i);
 	   %>
 	   <td  id='jinatd<%=i%>' class="days3"
 	   onmouseout="mout('<%=i%>')"
 	   onmouseover="ajaxcalendar('<%=i%>','${login.id}','<%=wdate%>')"><%=i%>
 	   <div id="jina<%=i%>"></div></td>
 	   <% 
 	   if((i+dayOfWeek-1)%7==0){
 		  %>
 	 	   </tr><tr height="40px">
 	 	   <%  
 	   }
    }
    for(int i=0; i<(7-(dayOfWeek+lastDay-1)%7)%7; i++){
    	out.println("<td>&nbsp;</td>");
    }
    %>
  </tr>
</table>

<div id="hello"></div>
</div>
<img src="./image/ajax001.PNG"/>
<script type="text/javascript">

function ajaxcalendar(i, id, wdate){
	//alert(i+" "+id+" "+wdate);
	$("#jinatd"+i).css("background-color","#FF0000");
	showallcust(i,id,wdate);//ajax
}
function mout(i){
	$("#jinatd"+i).css("background-color","#FFFFFF");
	 $("#jina"+i).hide();
}
$(document).ready(function(){
     $("#jina").html("&#xA9;jina.com 지나는 미래다. since 1995.");
 }); 
 
function showallcust(i, id, wdate){
	$.ajax({
		   type: "POST",
url: "<%=application.getContextPath()%>/calendarjson.do",
		   async: true,		   
		   data: "id="+id+"&wdate="+wdate,	
		   success: function(msg){
				outputList2(i,msg);
		   }
	 });
}
function outputList2(j,custlists){
	  var count=	custlists.jina.length;
	  var custList=	custlists.jina;
	  $("#hello").hide();
	  var str="<table align='center'>";
	  str+="<col width='400px'/>";
	  str+="<col width='200px'/>";
	  for(i=0; i<count; i++){
		  str+="<tr  height='30px'>";
		  str+="<td>"+custList[i].wdate+"</td> ";
		  str+="<td>"+custList[i].content+"</td> ";
		  str+="</tr>";
	  }
	  str+="</table>";
	  //$("#jina"+j).show();
	  //$("#jina"+j).html(str);
	   $("#hello").show();
	  $("#hello").html(str);
} 

</script>
