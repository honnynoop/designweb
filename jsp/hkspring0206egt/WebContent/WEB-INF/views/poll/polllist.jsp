<%@page import="com.hk.mobile.help.DateUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hk.mobile.member.model.HKPoll"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="utf-8"/>


<%
List<HKPoll> plists=new ArrayList<HKPoll>();
Object Oflist=request.getAttribute("plists");
if(Oflist!=null){
	plists=(List<HKPoll>)Oflist;
}
%>
<c:if test="${login.auth eq '1'}">
<table class="list_table" style="width:95%;">
<col width="50"/><col width="50"/><col width="300"/>
<col width="100"/><col width="100"/><col width="50"/>
<col width="50"/><col width="100"/>
   <tr>
      <th>번호</th><th>아이디</th><th>질문</th>
      <th>시작일</th><th>끝나는날</th><th>질문항수</th>
      <th>투표수</th><th>등록일</th>
   </tr>
   <%
   for(int i=0; i<plists.size();i++)
   {
	   HKPoll poll = plists.get(i);
      %>
      <tr bgcolor='#aabbcc'>
         <td><%=(i+1) %></td>
         <td><%=poll.getId()%></td>
         <td><%=poll.getQuestion()%></td>
         <td><%=poll.getSdate() %></td>
         <td><%=DateUtil.pollState(poll.getEdate())%><%=poll.getEdate() %></td>
         <td><%=poll.getItemcount() %></td>
         <td><%=poll.getPolltotal() %></td>
         <td><%=poll.getRegdate() %></td>
      </tr>
      <%
   }
   %>
   </table>
</c:if>
<c:if test="${login.auth eq '3'}">
<table class="list_table" style="width:95%;">
   <col width="50"/><col width="50"/><col width="300"/>
   <col width="100"/><col width="100"/><col width="50"/>
   <col width="50"/><col width="100"/>
      <tr>
         <th>번호</th><th>아이디</th><th>질문</th><th>결과</th>
         <th>시작일</th><th>끝나는날</th><th>질문항수</th>
         <th>투표수</th><th>등록일</th>
      </tr>
   <%
   for(int i=0; i<plists.size();i++)
   {
	   HKPoll poll = plists.get(i);
      %>
      <tr bgcolor='#aabbcc'>
         <td><%=(i+1) %></td>
         <td><%=poll.getId()%></td>
       <%
      boolean isS = poll.isVote();
      if(isS || DateUtil.isEnd(poll.getEdate())){//이미 투표 기한이 지남
         %>
         <td>[마감]<%=poll.getQuestion() %></td>
         <% 
      }else{//투표할 수 있다.
         %>
         <td><a href='./polldetail.do?pollid=<%=poll.getPollid()%>'><%=poll.getQuestion() %></a></td>
         <% 
      }
      %>
         <td>
 <%
         if(isS || DateUtil.isEnd(poll.getEdate()) ){
            %>
               <a href='./pollresult.do?pollid=<%=poll.getPollid()%>'>결과</a>
            <% 
         }else{//투표안했다.
            //못본다.
            %>
               <img alt="투표" src="./image/pen.gif"/>
            <% 
         }
         %>
         </td>
         <td><%=poll.getSdate() %></td>
         <td><%=DateUtil.pollState(poll.getEdate())%><%=poll.getEdate() %></td>
         <td><%=poll.getItemcount() %></td>
         <td><%=poll.getPolltotal() %></td>
         <td><%=poll.getRegdate() %></td>
      </tr>
      <%
   }
%>
</table>
</c:if>
<c:if test="${login.auth eq '1'}">
      <a href='pollmake.do'>투표만들기</a>
     <a href='./polllist.do'>HOME</a><br/>
</c:if>

<c:if test="${login.auth eq '3'}">
      <a href='./polllist.do'>HOME</a><br/>
</c:if>
