<%@ page contentType="text/html; charset=euc-kr" %>
<%@page import="java.sql.SQLException"%>
<%@page import="com.jungbo.pagecentric.CustUserManager"%>
<%@page import="java.util.Vector"%>
<%@page import="com.jungbo.pagecentric.CustUserDto"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>control</title>
</head>

<body>
<%!
private boolean isNull(String str){
	return str==null || str.trim().equals("");
}
%>
<%
String command=request.getParameter("command");
System.out.println(command);
//command가 널이면 custerror.jsp로  가는데 에러 메세지도 가져가자.
if(isNull(command)){
	request.setAttribute("errormsg","잘못된 요청입니다.다시 요청하십시오.");
	request.setAttribute("url","index.jsp");
	pageContext.forward("custerror.jsp");
}//if null 
%>
<jsp:useBean id="manager" class="com.jungbo.pagecentric.CustUserManager"/>
<% 
if(command.equalsIgnoreCase("list")){
	%>
	<jsp:useBean id="custvector" scope='request' class="java.util.Vector"/>
	<% 
	//고객 가져오기
	custvector= manager.getCustUserList();
	//request 스코프에 담기
	request.setAttribute("custvector",custvector);
	//request 스코프에 담기
	pageContext.forward("custuserlist.jsp");
}else if(command.equalsIgnoreCase("add")){
	//--------파라메터 받아 빈에 넣기
	%>
	<jsp:useBean id="custbean2" class="com.jungbo.pagecentric.CustUserBean"/>
	<jsp:setProperty name="custbean2" property="id" />
	<jsp:setProperty name="custbean2" property="name" />
	<jsp:setProperty name="custbean2" property="address" />
	<% 
	if(isNull(custbean2.getId()) 
			||isNull(custbean2.getName())
			||isNull(custbean2.getAddress()) ){
		request.setAttribute("errormsg","모든 정보를 입력하셔야 합니다.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	//고객 추가하기
	int count=manager.addCustUser(custbean2.getId(),
			custbean2.getName(),custbean2.getAddress());
	if(count<1){
		request.setAttribute("errormsg","고객 정보 추가에 실패하였습니다.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}
	response.sendRedirect("custusercontrol.jsp?command=list");
}else if(command.equalsIgnoreCase("bfupdate")){
	%>
	<jsp:useBean id="custbeanbf" 
	class="com.jungbo.pagecentric.CustUserBean" scope='request'/>
	<jsp:setProperty name="custbeanbf" property="id" />
	<% 
	if(isNull(custbeanbf.getId()) ){
		request.setAttribute("errormsg","id를 입력하셔야 합니다.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	CustUserDto custdto=manager.getCustUser(custbeanbf.getId());
	%>
	<jsp:setProperty name="custbeanbf" 
	  property="name" value="<%=custdto.getName()%>"/>
	<jsp:setProperty name="custbeanbf" 
	  property="address" value="<%=custdto.getAddress()%>"/>
	<jsp:forward page="custuserupdate.jsp"/>
<% 
}else if(command.equalsIgnoreCase("update")){
	%>
<jsp:useBean id="custbeanup" class="com.jungbo.pagecentric.CustUserBean"/>
	<jsp:setProperty name="custbeanup" property="id" />
	<jsp:setProperty name="custbeanup" property="name" />
	<jsp:setProperty name="custbeanup" property="address" />
	<% 
	if(isNull(custbeanup.getId()) 
			||isNull(custbeanup.getName())
			||isNull(custbeanup.getAddress()) ){
		request.setAttribute("errormsg","모든 정보를 입력하셔야 합니다.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	int count=manager.updateCustUser(custbeanup.getId(), 
			custbeanup.getName(), custbeanup.getAddress());
	
	if(count==0){
		request.setAttribute("errormsg","고객 추가에 실패하였습니다.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	response.sendRedirect("custusercontrol.jsp?command=detail&id="+custbeanup.getId());
}else if(command.equalsIgnoreCase("detail")){
	%>
	<jsp:useBean id="custbeande" class="com.jungbo.pagecentric.CustUserBean" scope='request'/>
	<jsp:setProperty name="custbeande" property="id" />
	<% 
	if(isNull(custbeande.getId()) ){
		request.setAttribute("errormsg","id를 입력하셔야 합니다.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	CustUserDto custdto=manager.getCustUser(custbeande.getId());
	%>
	<jsp:setProperty name="custbeande"
	   property="name" value="<%=custdto.getName()%>"/>
	<jsp:setProperty name="custbeande" 
	   property="address" value="<%=custdto.getAddress()%>"/>
	<jsp:forward page="custuserdetail.jsp"/>
<% 
}else if(command.equalsIgnoreCase("delete")){
	%>
    <jsp:useBean id="custbeandel" class="com.jungbo.pagecentric.CustUserBean" />
	<jsp:setProperty name="custbeandel" property="id" />
	<% 
	if(isNull(custbeandel.getId()) ){
		request.setAttribute("errormsg","id를 입력하셔야 합니다.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	int count=manager.deleteCustUser(custbeandel.getId());
	if(count==0){
		request.setAttribute("errormsg","고객 삭제에 실패하였습니다.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	response.sendRedirect("custusercontrol.jsp?command=list");
}else if(command.equalsIgnoreCase("muldel")){
	%>
	<jsp:useBean id="custbeanmuldel" class="com.jungbo.pagecentric.CustUserBean" />
	<jsp:setProperty name="custbeanmuldel" property="delck" />
	<% 
	String[] ids=custbeanmuldel.getDelck();
	if(ids == null || ids.length==0){
		request.setAttribute("errormsg","id를 한개이상 선택하셔야  합니다.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	boolean isS=manager.deleteCustUsers(ids);
	if( ! isS ){
		request.setAttribute("errormsg","고객 삭제에 실패하였습니다.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	response.sendRedirect("custusercontrol.jsp?command=list");
}
%>
</body>
</html>
