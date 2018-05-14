<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:requestEncoding value="UTF-8"/>

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
font-size:20px;
color: #4D6BB3; 
text-align: center;
vertical-align: middle;
background-color:#4D6BB3; color:#FFFFFF; line-height:1.7em; font-weight:normal;
}

.innerTable {
    border: 0px ;
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
.box_border1 { width:40%; border-width:0px; border-style:hidden; 
border-color:#ffffff; background-color:#FFFFFF; text-align:right; 
padding:2px; margin-top:5px; margin-bottom:10px; 
margin-left:auto; margin-right:auto;}
</style>
<br/>
<div style="text-align: left">
${year}년  
<c:forEach begin="1" end="12" step="1" var="m">
<c:url var="url" value="calendar3.do">
<c:param name="year" value="${year}"/>
<c:param name="month" value="${m}"/>
</c:url>
<a href='${url}'>${m}월 </a>
</c:forEach>
</div>
<br/>
<table class="list_table" style="width:85%;">
<col width="100"/><col width="200"/><col width="450"/><col width="50"/>
<tr bgcolor='#09bbaa'>
<th>번호</th><th>시간</th><th>제목</th><th>삭제</th>
</tr>
<tr>
<th colspan="4">${year}년 ${month}월</th>
</tr>
<c:forEach items="${callist}" var="cal" varStatus="vs">
	<tr>
<td>${vs.count}</td>
<td>${cal.wdate}</td>

<td style="text-align: left"><a href='caldetail.do?seq=${cal.seq}'>${cal.title}</a></td>
<td>
<form action="caldel.do" method="post">
<input type="hidden" name='seq' value='${cal.seq}'/>
<input type="submit"  value='일정삭제'/>
</form>
</td>
</tr>
</c:forEach>
</table>

<br/>
<table  class="box_border1" style="margin:auto;">
<tr style="font-weight: bold;">
<td><button id="prevY" >&lt; &lt;</button>&nbsp;&nbsp;</td><td><button id="prevM" > &lt; </button>&nbsp;&nbsp;</td>
<td id='year2'></td><td>년</td><td id='mon2'></td><td>월</td>
<td>&nbsp;&nbsp;<button id="nextY" > &gt; </button></td><td>&nbsp;&nbsp;<button id="nextM" >&gt; &gt;</button></td>
</tr>
</table>
<br>
<div id="accordion" align="center" style="width: 900;"></div>

<script type="text/javascript">
$(document).ready(function() {
	//alert('ready');
	setDate();
});


function setDate() {	//페이지 열리자마자 현재 년도 자동입력
	var now = new Date();
	var year = now.getFullYear();
	var mon = now.getMonth()+1;
	var month =  mon.toString().length < 2 ? "0"+mon : mon;
	listsData(year, month);
};

    var year2=null;
    var month2=null;
    var icons = {
		      header: "ui-icon-circle-arrow-e",
		      activeHeader: "ui-icon-circle-arrow-s"
		    };
    
    $("div[align=center]").css("margin","0 auto");

    $("#prevY,#prevM,#nextY,#nextM").click(function(){	// 각 버튼이 눌려졌을때 year,month 값 변환
    	//alert("-----------------------");
    	if(this.id=="prevY"){
    		year2=year2-1;
    	}else if(this.id=="prevM"){
    		if(month2-1<1){
 				month2=12;
 				year2=year2-1;
    		}else{
    			month2=month2-1;	
    		}
    	}else if(this.id=="nextY"){
    		if(month2+1>12){
    			month2=1;
    			year2=year2+1;
    		}else{
    			month2=month2+1;	
    		}
    	}else if(this.id=="nextM"){
    		year2=year2+1;
    	}
    	listsData(year2,month2);
    });//

    function listsData(year, month){	//해당 년 월의 일정
    	year2=year;
    	month2=month;
    	
    	$("#year2").html(year);
    	$("#mon2").html(month);
    	
    	$.ajax({
    		type : "POST",
    		url : "./noctice_list.do",
    		data : "year=" + year + "&month=" + month,
    		async : true,
    		success : function(msg) {
    			showNocticeTable(msg.list);
    		}
    	});
    };
    
    function showNocticeTable(nodes){
    	var text="";
    	
    	if(nodes.length==0){
    		text+="<h3>일정이 없습니다.</h3>";
    		text+="<div><p>해당 년 월의 학사 일정이 없습니다.</p></div>";
    	}else{
    		$.each(nodes, function(i,item){
    			text+="<h3 align='left'>day: "+item.day+" 일 | 제목: "+item.title+"</h3>";
    			text+="<div align='left'><p>"+item.content+"</p></div>";
    		});
    	}
    	
    	$("#accordion").html(text);
		$( "#accordion" ).accordion("destroy")
		$( "#accordion" ).accordion({ icons: icons });
    	$( "#accordion" ).accordion( "option", "icons" );
    	
    };//showNocticeTable
    
    $( "#accordion" ).accordion({ icons: icons });
	$( "#accordion" ).accordion( "option", "icons" );
    
  </script>
