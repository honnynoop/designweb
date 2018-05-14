<%@page import="com.hk.mobile.help.JiNaCal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hk.mobile.member.model.HKCalendar"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%request.setCharacterEncoding("utf-8") ;%>

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
</style>

<%! //데클러레이션 : 메소드 선언
public String callist(int year,int month, int day){
	String s="";
	s+=String.format("<a href='%s?year=%d&month=%d&day=%d'>", 
			"callist.do",year,month,day);
	s+=String.format("%2d",day); //2자리
	s+="</a>";
	return s;
}//
//pen이미지를 선택하면 일정을 작성할수 있다.
public String showPen(int year,int month, int day){
	String s="";
	String url="calwrite.do";
	String image="<img src='image/pen.gif'/>";
	s=String.format("<a href='%s?year=%d&month=%d&day=%d'>%s</a>",
			url,year,month,day,image);
	return s;
}
//1자라면 0을 붙여 두자로 만들기 1->01
public String two(String msg){
	return msg.trim().length()<2?"0"+msg:msg.trim();
}//
//15자 이상되면 ... 를 이용하여 줄임표시
public String dot3(String msg){
	String s="";
	if(msg.length()>=15){
		s=msg.substring(0,15);
		s+="...";
	}else{
		s=msg.trim();
	}
	return s;
}
public String makeTable(int year,int month, int day,
		List<HKCalendar> lcdtos){
	String s="";
	String dates=(year + "") + two(month + "") + two(day + "");//년월일 8글자 만드는거
	s="<table class='innerTable'>";
	s+="<col width='100px'/>";
	for(HKCalendar lcd:lcdtos){ //향상된 for
		if(lcd.getWdate().substring(0,8).equals(dates)){
			s+="<tr bgcolor='#4D6BB3'>";
			s+="<td>";
			
			s+="<a href='caldetail.do?seq="+lcd.getSeq()+"'>";
			s+="<font style='font-size:8px;color:#090000'>"; //글씨작게해서15자 들어가게끔
			s+=dot3(lcd.getTitle());
			s+="</font>";
			s+="</a>";
			s+="</td>";
			s+="</tr>";
		}
	}
	s+="</table>";
	return s;
}
%>

<%
List<HKCalendar> list=new ArrayList<HKCalendar>();
Object Oflist=request.getAttribute("flist");
if(Oflist!=null){
	list=(List<HKCalendar>)Oflist;
}

JiNaCal jcal=(JiNaCal)request.getAttribute("jcal");

int dayOfWeek=jcal.getDayOfweek();//1일 요일1~7
int lastDayOfMonth=jcal.getLastDay();

int year=jcal.getYear();
int month=jcal.getMonth();

String pp=String.format("<a href='./%s?year=%d&month=%d'><img src='image/left.gif'/></a>",
		"calendar.do",year-1,month);
String p=String.format("<a href='./%s?year=%d&month=%d'><img src='image/prec.gif'/></a>",
		"calendar.do",year,month-1);
String n=String.format("<a href='./%s?year=%d&month=%d'><img src='image/next.gif'/></a>",
		"calendar.do",year,month+1);
String nn=String.format("<a href='./%s?year=%d&month=%d'><img src='image/last.gif'/></a>",
		"calendar.do",year+1,month);

String url=String.format("%s?year=%s&month=%s",
		"calendar3.do",year,month);
%>
<div style="text-align: left"><a href='<%=url%>'><%=year+"년 "+month+"월 " %>일정보기</a></div>
<div class="box_border" style="margin-top:5px; margin-bottom: 10px;">
<table style="width:85%;">
<col width="100px"/>
<col width="100px"/>
<col width="100px"/>
<col width="100px"/>
<col width="100px"/>
<col width="100px"/>
<col width="100px"/>
<thead>
<tr height="100px">
<td class="days2" colspan="7"><%=pp%><%=p%><font color="red" style="font-size: 20"><%=String.format("%d년&nbsp;&nbsp;%d월",year,month) %></font><%=n%><%=nn%></td>
</tr>
<tr height="100px" >
<th class="days3">일</th><th class="days3">월</th>
<th class="days3">화</th><th class="days3">수</th><th class="days3">목</th>
<th class="days3">금</th><th class="days3">토</th>
</tr>
</thead>
<tr height="100px">
<%
for(int i=1; i<dayOfWeek; i++){
	out.println("<td>&nbsp;</td>");
}
for(int i=1; i<=lastDayOfMonth; i++){
	String hhh=String.format(
"<a href='%s?year=%d&month=%d&day=%d'>"+
"<img src='./image/pen.gif' alt='write'/></a>",
"calwrite.do",year,month,i);
	
	if((i+dayOfWeek-1)%7==0){
		%>
		<td class="satday"><%=callist(year,month,i)%>&nbsp;<%=showPen(year,month,i)%>
		<%=makeTable(year,month,i,list)%>
		</td>
		<%
	}else if((i+dayOfWeek-1)%7==1){
		%>
		<td class="sunday"><%=callist(year,month,i)%>&nbsp;<%=showPen(year,month,i)%>
		<%=makeTable(year,month,i,list)%>
		</td>
		<%
	}else{
		%>
		<td class="otherday"><%=callist(year,month,i)%>&nbsp;<%=showPen(year,month,i)%>
		<%=makeTable(year,month,i,list)%>
		</td>
		<%
	}
	
	if((i+dayOfWeek-1)%7==0){
		%>
		</tr><tr height="100px">
		<%
	}
}
for(int i=0; i<(7-(dayOfWeek+lastDayOfMonth-1)%7)%7; i++){
	out.println("<td>&nbsp;</td>");
}
%>
</tr>
</table>
</div>





