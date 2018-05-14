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
	request.setAttribute("errormsg","�߸��� ��û�Դϴ�.�ٽ� ��û�Ͻʽÿ�.");
	request.setAttribute("url","index.jsp");
	pageContext.forward("custerror.jsp");
}//if null
CustUserService manager=CustUserService.getInstance();
if(command.equalsIgnoreCase("list")){
	//��� ���� �ٿ����� �����´�.
	List<CustUserDto> custs=manager.getCustUserList();
	//������Ʈ �������� ���
	request.setAttribute("custs",custs);
	pageContext.forward("custuserlist.jsp");
}else if(command.equalsIgnoreCase("add")){
	String id=request.getParameter("id");
	String name=request.getParameter("name");
	String address=request.getParameter("address");
	if(isNull(id) ||isNull(name)||isNull(address) ){
		request.setAttribute("errormsg","��� ������ �Է��ϼž� �մϴ�.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	int count=manager.addCustUser(new CustUserDto(id, name, address));
	if(count==0){
		request.setAttribute("errormsg","�� �߰��� �����Ͽ����ϴ�.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	response.sendRedirect("custusercontrol.jsp?command=list");
}else if(command.equalsIgnoreCase("bfupdate")){
	//���� �ٿ����� �����´�.
	String id=request.getParameter("id");
	if(isNull(id) ){
		request.setAttribute("errormsg","id�� �Է��ϼž� �մϴ�.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	CustUserDto custdto=manager.getCustUser(id);
	//������Ʈ �������� ���
	request.setAttribute("cust",custdto);
	pageContext.forward("custuserupdate.jsp");
}else if(command.equalsIgnoreCase("update")){
	String id=request.getParameter("id");
	String name=request.getParameter("name");
	String address=request.getParameter("address");
	if(isNull(id) ||isNull(name)||isNull(address) ){
		request.setAttribute("errormsg","��� ������ �Է��ϼž� �մϴ�.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	int count=manager.updateCustUser(new CustUserDto(id, name, address));
	if(count==0){
		request.setAttribute("errormsg","�� �߰��� �����Ͽ����ϴ�.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	response.sendRedirect("custusercontrol.jsp?command=detail&id="+id.trim()); 
}else if(command.equalsIgnoreCase("detail")){
	//���� �ٿ����� �����´�.
	String id=request.getParameter("id");
	if(isNull(id) ){
		request.setAttribute("errormsg","id�� �Է��ϼž� �մϴ�.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	CustUserDto custdto=manager.getCustUser(id);
	//������Ʈ �������� ���
	request.setAttribute("cust",custdto);
	pageContext.forward("custuserdetail.jsp");
}else if(command.equalsIgnoreCase("delete")){
	//���� �ٿ����� �����´�.
	String id=request.getParameter("id");
	if(isNull(id) ){
		request.setAttribute("errormsg","id�� �Է��ϼž� �մϴ�.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	int count=manager.deleteCustUser(id);
	if(count==0){
		request.setAttribute("errormsg","�� ������ �����Ͽ����ϴ�.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	response.sendRedirect("custusercontrol.jsp?command=list");
}else if(command.equalsIgnoreCase("muldel")){
	//���� �ٿ����� �����´�.
	String[] ids=request.getParameterValues("delck");
	if(ids == null || ids.length==0){
		request.setAttribute("errormsg","id�� �Ѱ��̻� �����ϼž�  �մϴ�.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp"); 
	}//if
	 boolean isS=manager.deleteCustUsers(ids);
	if( ! isS ){
		request.setAttribute("errormsg","�� ������ �����Ͽ����ϴ�.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	response.sendRedirect("custusercontrol.jsp?command=list");
}
%>
</body>
</html>
