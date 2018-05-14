<%@page import="com.mirhenge.jyl.calendar.help.CalendarUtil"%>
<%@page import="com.mirhenge.jyl.calendar.help.JYLCal"%>
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


	#calendar {
		width: 900px;
		margin: 0 auto;
		border-style: hidden;
		}
table.layout_table {
	width: 900px;
	align: center;
	font-size: 15px;
	font-face: 돋움;
	border-style: hidden;
	border-collapse: collapse;
}

table.data_table {
	width: 100%; 
	border-width: 1px;
	border-spacing: 2px;
	border-collapse: collapse;
	border-color: #ffffff;
	background-color: #ffffff;
}

td .title_td {
	cellpadding: 5px;
	height: 34px;
	width: 25%;
	font-size: 20px;
	font-weight: bold;
	border-width: 1px;
	border-color: #ffffff;
	padding: 1px;
	border-style: solid;
	background-color: #a0c9e3;
	padding-left: 10px;
}

td .tt_column_td {
	cellpadding: 5px;
	height: 30px;
	width: 50%;
	font-size: 20px;
	font-weight: bold;
	border-width: 1px;
	border-color: #ffffff;
	padding: 1px;
	border-style: solid;
	background-color: #a0c9e3;
	padding-left: 10px;
	padding-right: 10px;
}

td .column_td {
	cellpadding: 5px;
	height: 25px;
	width: 15%;
	font-size: 13px;
	font-weight: bold;
	border-width: 1px;
	border-color: #ffffff;
	padding: 1px;
	border-style: solid;
	background-color: #a0c9e3;
	padding-left: 10px;
	padding-right: 10px;
}

td .column_rd_td {
	cellpadding: 5px;
	height: 25px;
	width: 3%;
	font-size: 13px;
	font-weight: bold;
	border-width: 1px;
	border-color: #ffffff;
	padding: 1px;
	border-style: solid;
	background-color: #a0c9e3;
	padding-left: 10px;
	padding-right: 10px;
}


td .base_td {
	height: 25px;
	font-size: 15px;
	border-width: 1px;
	border-color: #ffffff;
	padding: 1px;
	border-style: solid;
	padding: 1px;
	border-style: solid;
	background-color: #d4e9f2;
	padding-left: 10px;
	padding-right: 10px;
}


td .ag_column_td {
	cellpadding: 5px;
	height: 25px;
	font-size: 12px;
	font-weight: bold;
	border-width: 1px;
	border-color: #ffffff;
	padding: 1px;
	border-style: solid;
	background-color: #a0c9e3;
	padding-left: 2px;
	padding-right: 2px;
	align: center;
	text-align: center;
}

td .ag_base_td {
	height: 25px;
	font-size: 12px;
	border-width: 1px;
	border-color: #ffffff;
	padding: 1px;
	border-style: solid;
	padding: 1px;
	border-style: solid;
	background-color: #d4e9f2;
	padding-left: 2px;
	padding-right: 2px;
	text-align: center;
}

tr .ag_base_tr {
	height: 25px;
	font-size: 12px;
	border-width: 1px;
	border-color: #ffffff;
	padding: 1px;
	border-style: solid;
	padding: 1px;
	border-style: solid;
	background-color: #d4e9f2;
	padding-left: 2px;
	padding-right: 2px;
	text-align: center;
}

tr .ag_column_tr {
	cellpadding: 5px;
	height: 25px;
	font-size: 12px;
	font-weight: bold;
	border-width: 1px;
	border-color: #ffffff;
	padding: 1px;
	border-style: solid;
	background-color: #a0c9e3;
	padding-left: 2px;
	padding-right: 2px;
	align: center;
	text-align: center;
}


a.rollover {
	height: 25px;
	font-size: 12px;
	border-width: 1px;
	border-color: #ffffff;
	padding: 1px;
	border-style: solid;
	padding: 1px;
	border-style: solid;
	background-color: #d4e9f2;
	padding-left: 2px;
	padding-right: 2px;
	text-align: center;
}

a.rollover:hover {
	cellpadding: 5px;
	height: 25px;
	font-size: 12px;
	font-weight: bold;
	border-width: 1px;
	border-color: #ffffff;
	padding: 1px;
	border-style: solid;
	background-color: #a0c9e3;
	padding-left: 2px;
	padding-right: 2px;
	align: center;
	text-align: center;
}
.box_border1 { width:40%; border-width:0px; border-style:hidden; border-color:#ffffff; background-color:#FFFFFF; text-align:right; padding:2px; margin-top:5px; margin-bottom:10px; margin-left:auto; margin-right:auto;}

</style>
<h3>일정관리</h3>
<%
JYLCal jcal=(JYLCal)request.getAttribute("jcal");

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
<table style="width:85%;margin-left:auto; margin-right:auto; margin-top:3px; margin-bottom:3px; border:0; padding:0;">
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
<link href='<%=request.getContextPath()%>/fullcalendar/fullcalendar.css' rel='stylesheet' />
<link href='<%=request.getContextPath()%>/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src='<%=request.getContextPath()%>/fullcalendar/fullcalendar.min.js'></script>
<script>

	$(document).ready(function() {
	
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		var detail=null;
		
		var calendar= $('#calendar').fullCalendar({
			header: {
				left: 'today',
				center: 'prevYear ,prev ,title ,next ,nextYear',
				right: 'month,basicWeek,basicDay'
			},
			titleFormat:{
				month:'yyyy년 MMMM',
				week: "yyyy년 MMMM d[ yyyy]{'일~'[ MMM] dd일}",
				day:'yyyy년 MMM d dddd'
			},
			columnFormat:{
				month:'dddd',
				week:'M/d ddd',
				day: 'M월d일 dddd'
			},
			allDayText:'시간',
			axisFormat:'tt hh',
			monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			monthNamesShort:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			dayNames:['일요일','월요일','화요일','수요일','목요일','금요일','토요일'],
			dayNamesShort:['일','월','화','수','목','금','토'],
			editable: true,
			selectable: true,
			selectHelper: true,
			eventStartEditable :false,
			/*
		 	dayClick: function(date, allDay, jsEvent, view) {
				window.alert("dayClick");
				calendar.fullCalendar('unselect');
			}, */
			
			eventClick: function(event) {
				detailForm(event);
				$( "#dialog_detail" ).dialog("open");
			},

			editable: true,
		    events: function(start, end, callback) {
		    	//alert(start+"\n"+ start.getTime()+"\n"+start.toLocaleDateString());
		        $.ajax({
		            url: "<%=request.getContextPath()%>/getNotice.do",
		            type: "POST",
		            dataType: "json",
		            async: true,
		            data: {
		                // our hypothetical feed requires UNIX timestamps
		            	start: start.toLocaleDateString(),
		                end: end.toLocaleDateString()
		            },
		            success: function(doc) {
		            	detail=doc.list;
		                var events = [];
						$.each(doc.list, function(i,item){
		                    events.push({
		                        title: item.title,
		                        start: new Date(item.year, item.month, item.day), // will be parsed
		                        end: new Date(item.year, item.month, item.day)
// 		                    start:''+item.year+''+item.month+''+item.day, // will be parsed
// 	                        end: ''+item.year+''+item.month+''+item.day
		                    });
		                });
		                callback(events);
		            }
		        });
		    }

		});
		
		
		$( "#dialog_detail" ).dialog({
			autoOpen: false,
			height: 600,
			width: 700,
			modal: true,
			close: function() {
				$("#title2").val("");
				$("#content2").val("");
			} 
			
		});
		
		function detailForm(msg){
			var sy= msg.start.getFullYear();
			var sm= msg.start.getMonth()+1;
			if(sm-1==0){	// 달력에 데이터를 바로 넣으면 1달이 추가되어 출력되는 오류가 생기므로
				sy=sy-1;	// detail창의 데이터 출력을 위해 클릭했을때 들어오는 월을 조정하여 비교해 준다.
				sm=12;
			}else{
				sm=sm-1;				
			}
			
			$.each(detail,function(i,item){
// 				alert(item.year+"년월"+item.month+"제목"+item.title+"비교년도"+sy+"년월"+sm);
				if(item.year==sy && item.month==sm && item.day==msg.start.getDate()){
					$("#title2").val(item.title);
					$("#content2").val(item.content);
				}
				
			});
		}
		
		
	});

</script>

<div id='calendar'></div>

<div id='dialog_detail' title="일정 상세보기">
	<table width='100%'>
	<tr align="center" >
		<td align="center">제목: <input type="text" id="title2" readonly="readonly" size="50"/></td>
	</tr>
	<tr>
	<td class='column_td'><textarea rows="30" cols="92" 
	id="content2" readonly="readonly"></textarea></td>
	</tr>
	</table>	
</div>