<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:requestEncoding value="utf-8"/>

<script>
function setDate() {	//페이지 열리자마자 현재 년도 자동입력
	var now = new Date();
	var year = now.getFullYear();
	var mon = now.getMonth()+1;
	var month =  mon.toString().length < 2 ? "0"+mon : mon;
	listsData(year, month);
}

  $(function() {
    var year2=null;
    var month2=null;
    var icons = {
		      header: "ui-icon-circle-arrow-e",
		      activeHeader: "ui-icon-circle-arrow-s"
		    };
    
    $("div[align=center]").css("margin","0 auto");

    $("#prevY,#prevM,#nextY,#nextM").button().click(function(){	// 각 버튼이 눌려졌을때 year,month 값 변환
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
    	listsData2(year2,month2);
    });//
   
    
    
    
    function listsData2(year, month){	//해당 년 월의 일정
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
    }//
    
    listsData=listsData2;	//javascript 와 jquery 메서드 연결
    
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
    	
    }//showNocticeTable
    
    $( "#accordion" ).accordion({ icons: icons });
	$( "#accordion" ).accordion( "option", "icons" );
    
	
	
  });//jquery
  </script>

<div onload="setDate()"> </div>
<table align="center" >
<tr style="font-weight: bold;">
<td><button id="prevY" >&lt; &lt;</button>&nbsp;&nbsp;</td><td><button id="prevM" > &lt; </button>&nbsp;&nbsp;</td>
<td id='year2'></td><td>년</td><td id='mon2'></td><td>월</td>
<td>&nbsp;&nbsp;<button id="nextY" > &gt; </button></td><td>&nbsp;&nbsp;<button id="nextM" >&gt; &gt;</button></td>
</tr>
</table>
<br>
<div id="accordion" align="center" style="width: 900"></div>