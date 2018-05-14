<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.mirhenge.jyl.spfp.model.SpfpDiary"%>
<%@page import="com.mirhenge.jyl.calendar.help.JYLCal"%>
<%@page import="com.mirhenge.jyl.calendar.model.JYLCalendar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>

<style>
table {
	border-collapse: collapse;
}

table, td, th {
	border: 1px solid black;
}

.sunday {
	color: red;
	text-align: left;
	vertical-align: top;
}

.satday {
	color: blue;
	text-align: left;
	vertical-align: top;
}

.otherday {
	color: black;
	text-align: left;
	vertical-align: top;
}

.days2 {
	font-size: 20px;
	color: #4D6BB3;
	text-align: center;
	vertical-align: middle;
}

.days3 {
	font-size: 20px;
	color: #4D6BB3;
	text-align: center;
	vertical-align: middle;
	background-color: #4D6BB3;
	color: #FFFFFF;
	line-height: 1.7em;
	font-weight: normal;
}

.innerTable {
	border: 0px;
}

#prev {
	visibility: hidden;
	position: absolute;
	top: 60%;
	text-align: left;
}
</style>

<%!//데클러레이션 : 메소드 선언
	public String callist(int year, int month, int day) {
		String s = "";
		String dates = (year + "") + two(month + "") + two(day + "");
		s += String.format("<a href='%s?yyyymmdd=%s'>", "spfplist.do", dates);
		s += String.format("%2d", day); //2자리
		s += "</a>";
		return s;
	}//
		//1자라면 0을 붙여 두자로 만들기 1->01
	public String two(String msg) {
		return msg.trim().length() < 2 ? "0" + msg : msg.trim();
	}//
	public String makeTable(int year, int month, int day, List<SpfpDiary> lcdtos) {
		DateFormat spfpFormat = new SimpleDateFormat("yyyyMMdd");
		String s = "";
		String dates = (year + "") + two(month + "") + two(day + "");//년월일 8글자 만드는거
		s = "<table class='innerTable'>";
		s += "<col width='100px'/>";
		for (SpfpDiary lcd : lcdtos) { //향상된 for
			if (spfpFormat.format(lcd.getWdate()).substring(0, 8).equals(dates)) {
				s += "<tr ";
				s += "bgcolor='";
				if(lcd.getTeam()==0) {
					s += "#4D6BB3'>";					
				}else if(lcd.getTeam()==1) {
					s += "#8700a5'>";
				}else if(lcd.getTeam()==2) {
					s += "#a50000'>";
				}else if(lcd.getTeam()==3) {
					s += "#fffa00'>";
				}else if(lcd.getTeam()==4) {
					s += "#9effc9'>";
				}
				s += "<td onmouseover='hover(" + lcd.getSeq() + ")' onmouseout='down()'>";

				s += "<a href='spfpdetail.do?seq=" + lcd.getSeq() + "'>";
				s += "<font style='font-size:8px;color:"; //글씨작게해서15자 들어가게끔
				if(lcd.getTeam()==0) {
					s += "#ffffff'>";					
				}else if(lcd.getTeam()==1) {
					s += "#ffffff'>";
				}else if(lcd.getTeam()==2) {
					s += "#ffffff'>";
				}else if(lcd.getTeam()==3) {
					s += "#090000'>";
				}else if(lcd.getTeam()==4) {
					s += "#090000'>";
				}
				s += lcd.getId() + "의 일지";
				s += "</font>";
				s += "</a>";
				s += "</td>";
				s += "</tr>";
			}
		}
		s += "</table>";
		return s;
	}%>

<%
	List<SpfpDiary> list = new ArrayList<SpfpDiary>();
	Object Oflist = request.getAttribute("flist");
	if (Oflist != null) {
		list = (List<SpfpDiary>) Oflist;
	}

	JYLCal jcal = (JYLCal) request.getAttribute("jcal");

	int dayOfWeek = jcal.getDayOfweek();//1일 요일1~7
	int lastDayOfMonth = jcal.getLastDay();

	int year = jcal.getYear();
	int month = jcal.getMonth();

	String pp = String.format("<a href='./%s?year=%d&month=%d'><img src='image/left.gif'/></a>", "spfpcal.do",
			year - 1, month);
	String p = String.format("<a href='./%s?year=%d&month=%d'><img src='image/prec.gif'/></a>", "spfpcal.do",
			year, month - 1);
	String n = String.format("<a href='./%s?year=%d&month=%d'><img src='image/next.gif'/></a>", "spfpcal.do",
			year, month + 1);
	String nn = String.format("<a href='./%s?year=%d&month=%d'><img src='image/last.gif'/></a>", "spfpcal.do",
			year + 1, month);

	String url = String.format("%s?year=%s&month=%s", "calendar3.do", year, month);
%>
<div style="text-align: left">
	<a href='<%=url%>'><%=year + "년 " + month + "월 "%>일정보기</a>
</div>
<form name="frmForm" id="_frmForm" method="get" action=""></form>
<div class="box_border" style="margin-top: 5px; margin-bottom: 10px;">
	<table style="width:85%; margin-left:auto; margin-right:auto; margin-top:3px; margin-bottom:3px; border:0; padding:0;">
		<col width="100px" />
		<col width="100px" />
		<col width="100px" />
		<col width="100px" />
		<col width="100px" />
		<col width="100px" />
		<col width="100px" />
		<thead>
			<tr height="100px">
				<td class="days2" colspan="7"><%=pp%><%=p%><font color="red"
					style="font-size: 20"><%=String.format("%d년&nbsp;&nbsp;%d월", year, month)%></font><%=n%><%=nn%></td>
			</tr>
			<tr height="100px">
				<th class="days3">일</th>
				<th class="days3">월</th>
				<th class="days3">화</th>
				<th class="days3">수</th>
				<th class="days3">목</th>
				<th class="days3">금</th>
				<th class="days3">토</th>
			</tr>
		</thead>
		<tr height="100px">
			<%
				for (int i = 1; i < dayOfWeek; i++) {
					out.println("<td>&nbsp;</td>");
				}
				for (int i = 1; i <= lastDayOfMonth; i++) {
					String hhh = String.format(
							"<a href='%s?year=%d&month=%d&day=%d'>" + "<img src='./image/pen.gif' alt='write'/></a>",
							"calwrite.do", year, month, i);

					if ((i + dayOfWeek - 1) % 7 == 0) {
			%>
			<td class="satday"><%=callist(year, month, i)%>&nbsp; <%=makeTable(year, month, i, list)%>
			</td>
			<%
				} else if ((i + dayOfWeek - 1) % 7 == 1) {
			%>
			<td class="sunday"><%=callist(year, month, i)%>&nbsp; <%=makeTable(year, month, i, list)%>
			</td>
			<%
				} else {
			%>
			<td class="otherday"><%=callist(year, month, i)%>&nbsp; <%=makeTable(year, month, i, list)%>
			</td>
			<%
				}

					if ((i + dayOfWeek - 1) % 7 == 0) {
			%>
		</tr>
		<tr height="100px">
			<%
				}
				}
				for (int i = 0; i < (7 - (dayOfWeek + lastDayOfMonth - 1) % 7) % 7; i++) {
					out.println("<td>&nbsp;</td>");
				}
			%>
		</tr>
	</table>
	<div id="buttons_wrap">
		<span class="button blue">
			<button type="button" id="_btnAdd">글쓰기</button>
		</span>
	</div>
</div>

<script>
$("#_btnAdd").click(function() {	
	alert('글쓰기');	
	$("#_frmForm").attr({ "target":"_self", "action":"spfpwrite.do" }).submit();
});
function hover(msg) {
	$("#prev").css("visibility","visible");
	$.ajax({
		data:{seq:msg},
		url:"spfpprev.do",
		success:function(data){

			var ss="<p>내용: "+data.content+"</p>";
			ss=ss+"<p>작성자: "+data.id+"</p>";
			if(data.img!=null) {
				
				//ss=ss+"<p><img src='./upload/1493742196494.png' width='300px' /></p>";
				//ss=ss+"<p><img src='./upload/"+data.img+"' width='300px' /></p>";
 				ss=ss+"<p><img src='"+data.path+data.img+"' width='300px' /></p>";
			}else {
				ss=ss+"<p>사진이 없어유...</p>";
			}
			$("#prev").html(ss);
		}
	});
}
function down() {
	$("#prev").css("visibility","hidden");
}
</script>







