<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="com.jungbo.pagecentric.CustUserManager"%>
<%@page import="java.util.List"%>
<%@page import="com.jungbo.pagecentric.CustUserDto"%>
<%@page import="com.jungbo.pagecentric.CustUserService"%>
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
}//
%>
<%
String command=request.getParameter("command");
if(isNull(command)){
	request.setAttribute("errormsg","잘못된 요청입니다.다시 요청하십시오.");
	request.setAttribute("url","index.jsp");
	pageContext.forward("custerror.jsp");
}//if null
CustUserService manager=CustUserService.getInstance();
if(command.equalsIgnoreCase("list")){
	//모든 고객을 다오에서 가져온다.
	List<CustUserDto> custs=manager.getCustUserList();
	//리쿼스트 스코프에 담기
	request.setAttribute("custs",custs);
	pageContext.forward("custuserlist.jsp");
}else if(command.equalsIgnoreCase("add")){
	String id=request.getParameter("id");
	String name=request.getParameter("name");
	String address=request.getParameter("address");
	if(isNull(id) ||isNull(name)||isNull(address) ){
		request.setAttribute("errormsg","모든 정보를 입력하셔야 합니다.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	int count=manager.addCustUser(new CustUserDto(id, name, address));
	if(count==0){
		request.setAttribute("errormsg","고객 추가에 실패하였습니다.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	response.sendRedirect("custusercontrol.jsp?command=list");
}else if(command.equalsIgnoreCase("bfupdate")){
	//고객을 다오에서 가져온다.
	String id=request.getParameter("id");
	if(isNull(id) ){
		request.setAttribute("errormsg","id를 입력하셔야 합니다.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	CustUserDto custdto=manager.getCustUser(id);
	//리쿼스트 스코프에 담기
	request.setAttribute("cust",custdto);
	pageContext.forward("custuserupdate.jsp");
}else if(command.equalsIgnoreCase("update")){
	String id=request.getParameter("id");
	String name=request.getParameter("name");
	String address=request.getParameter("address");
	if(isNull(id) ||isNull(name)||isNull(address) ){
		request.setAttribute("errormsg","모든 정보를 입력하셔야 합니다.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	int count=manager.updateCustUser(new CustUserDto(id, name, address));
	if(count==0){
		request.setAttribute("errormsg","고객 추가에 실패하였습니다.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	response.sendRedirect("custusercontrol.jsp?command=detail&id="+id.trim()); 
}else if(command.equalsIgnoreCase("detail")){
	//고객을 다오에서 가져온다.
	String id=request.getParameter("id");
	if(isNull(id) ){
		request.setAttribute("errormsg","id를 입력하셔야 합니다.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	CustUserDto custdto=manager.getCustUser(id);
	//리쿼스트 스코프에 담기
	request.setAttribute("cust",custdto);
	pageContext.forward("custuserdetail.jsp");
}else if(command.equalsIgnoreCase("delete")){
	//고객을 다오에서 가져온다.
	String id=request.getParameter("id");
	if(isNull(id) ){
		request.setAttribute("errormsg","id를 입력하셔야 합니다.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	int count=manager.deleteCustUser(id);
	if(count==0){
		request.setAttribute("errormsg","고객 삭제에 실패하였습니다.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	response.sendRedirect("custusercontrol.jsp?command=list");
}else if(command.equalsIgnoreCase("muldel")){
	//고객을 다오에서 가져온다.
	String[] ids=request.getParameterValues("delck");
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
