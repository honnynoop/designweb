<%@ page contentType="text/html; charset=euc-kr" %>
<%@page import="java.sql.SQLException"%>
<%@page import="com.jungbo.pagecentric.CustUserManager"%>
<%@page import="java.util.Vector"%>
<%@page import="com.jungbo.pagecentric.CustUserDto"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.jungbo.pagecentric.CustUserService"%>
<%@page import="com.jungbo.paging.ResultLists"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
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
//command�� ���̸� custerror.jsp��  ���µ� ���� �޼����� ��������.
if(isNull(command)){
	request.setAttribute("errormsg","�߸��� ��û�Դϴ�.�ٽ� ��û�Ͻʽÿ�.");
	request.setAttribute("url","index.jsp");
%>
<jsp:forward page="custerror.jsp"/>
<% 
}//if null 

CustUserService service=CustUserService.getInstance();
if(command.equalsIgnoreCase("list")){
	/*
	int startPage=0;
	int startBlock=0;
	startPage=request.getParameter("startPage")==null? 1:Integer.parseInt(request.getParameter("startPage"));
	startBlock=request.getParameter("startBlock")==null? 1:Integer.parseInt(request.getParameter("startBlock"));
	//�� ��������
	ResultLists<CustUserDto> custs=service.getCustUserList( startPage,  startBlock);
	*/
	%>
	<jsp:useBean id="pagebean" class="com.jungbo.paging.PageBean"/>
	<jsp:setProperty name="pagebean" property="startPage" />
	<jsp:setProperty name="pagebean" property="startBlock" />
	<% 
	ResultLists<CustUserDto> custs=service.getCustUserList( 
			pagebean.getStartPage(),  pagebean.getStartBlock());
	//Vector<CustUserDto> custs= manager.getCustUserList();
	//request�� ���
	request.setAttribute("custs",custs);
	//���ϴ� ������ ������ 
	%>
	<jsp:forward page="custuserlist.jsp"/>
	<% 
}else if(command.equalsIgnoreCase("add")){
	//--------�Ķ���� �޾� �� �ֱ�
	%>
	<jsp:useBean id="custbean" class="com.jungbo.pagecentric.CustUserBean"/>
	<jsp:setProperty name="custbean" property="id" />
	<jsp:setProperty name="custbean" property="name" />
	<jsp:setProperty name="custbean" property="address" />
	<% 
	if(isNull(custbean.getId()) ||isNull(custbean.getName()) 
			||isNull(custbean.getAddress())){
		request.setAttribute("errormsg","�߸��� ��û�Դϴ�.�ٽ� ��û�Ͻʽÿ�.");
		request.setAttribute("url","index.jsp");
	%>
	<jsp:forward page="custerror.jsp"/>
	<% 
	}//if null 
	//------------------------------------------
	//�� �߰��ϱ�
	CustUserDto uDto=new CustUserDto(custbean.getId(),
			custbean.getName(),custbean.getAddress());
	int count=service.addCustUser(uDto);
	if(count<1){
		request.setAttribute("errormsg","�� ���� �߰��� �����Ͽ����ϴ�.");
		request.setAttribute("url","index.jsp");
		%>
		<jsp:forward page="custerror.jsp"/>
		<% 
	}else{
		CustUserDto custdto=service.getCustUser(custbean.getId());
		request.setAttribute("cust",custdto);
		%>
	<jsp:forward page="custuserdetail.jsp"/>
	<% 
	}
}else if(command.equalsIgnoreCase("bfupdate")){
	%>
	<jsp:useBean id="custbeanbf" class="com.jungbo.pagecentric.CustUserBean"/>
	<jsp:setProperty name="custbeanbf" property="id" />
	<% 
	
	if(isNull(custbeanbf.getId()) ){
		request.setAttribute("errormsg","id�� �Է��ϼž� �մϴ�.");
		request.setAttribute("url","index.jsp");
		%>
		<jsp:forward page="custerror.jsp"/>
		<% 
	}//if
	CustUserDto custdto=service.getCustUser(custbeanbf.getId());
	request.setAttribute("cust",custdto);
	%>
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
		request.setAttribute("errormsg","��� ������ �Է��ϼž� �մϴ�.");
		request.setAttribute("url","index.jsp");
		%>
		<jsp:forward page="custerror.jsp"/>
		<% 
	}//if
	CustUserDto uDto=new CustUserDto(custbeanup.getId(),
			custbeanup.getName(),custbeanup.getAddress());
	int count=service.updateCustUser(uDto);
	
	if(count==0){
		request.setAttribute("errormsg","�� �߰��� �����Ͽ����ϴ�.");
		request.setAttribute("url","index.jsp");
		%>
		<jsp:forward page="custerror.jsp"/>
		<% 
	}else{
		CustUserDto custdto=service.getCustUser(custbeanup.getId());
		request.setAttribute("cust",custdto);
		%>
	<jsp:forward page="custuserdetail.jsp"/>
	<% 
	}
}else if(command.equalsIgnoreCase("detail")){
	%>
	<jsp:useBean id="custbeande" class="com.jungbo.pagecentric.CustUserBean" />
	<jsp:setProperty name="custbeande" property="id" />
	<% 
	if(isNull(custbeande.getId()) ){
		request.setAttribute("errormsg","id�� �Է��ϼž� �մϴ�.");
		request.setAttribute("url","index.jsp");
		%>
		<jsp:forward page="custerror.jsp"/>
		<% 
	}//if
	CustUserDto custdto=service.getCustUser(custbeande.getId());
	request.setAttribute("cust",custdto);
	%>
<jsp:forward page="custuserdetail.jsp"/>
<% 
}else if(command.equalsIgnoreCase("delete")){
	%>
	<jsp:useBean id="custbeandel" class="com.jungbo.pagecentric.CustUserBean" />
	<jsp:setProperty name="custbeandel" property="id" />
	<% 
	if(isNull(custbeandel.getId()) ){
		request.setAttribute("errormsg","id�� �Է��ϼž� �մϴ�.");
		request.setAttribute("url","index.jsp");
		%>
		<jsp:forward page="custerror.jsp"/>
		<% 
	}//if
	int count=service.deleteCustUser(custbeandel.getId());
	if(count==0){
		request.setAttribute("errormsg","�� ������ �����Ͽ����ϴ�.");
		request.setAttribute("url","index.jsp");
		%>
		<jsp:forward page="custerror.jsp"/>
		<% 
	}else{
		ResultLists<CustUserDto> custs=service.getCustUserList( 1,1);
		request.setAttribute("custs",custs);
		%>
		<jsp:forward page="custuserlist.jsp"/>
		<% 
	}
}else if(command.equalsIgnoreCase("muldel")){
	%>
	<jsp:useBean id="custbeanmuldel" class="com.jungbo.pagecentric.CustUserBean" />
	<jsp:setProperty name="custbeanmuldel" property="delck" />
	<% 
	String[] ids=custbeanmuldel.getDelck();
	if(ids == null || ids.length==0){
		request.setAttribute("errormsg","id�� �Ѱ��̻� �����ϼž�  �մϴ�.");
		request.setAttribute("url","index.jsp");
		%>
		<jsp:forward page="custerror.jsp"/>
		<% 
	}//if
	 boolean isS=service.deleteCustUsers(ids);
	if( ! isS ){
		request.setAttribute("errormsg","�� ������ �����Ͽ����ϴ�.");
		request.setAttribute("url","index.jsp");
		%>
		<jsp:forward page="custerror.jsp"/>
		<% 
	}else{
		ResultLists<CustUserDto> custs=service.getCustUserList( 1,1);
		request.setAttribute("custs",custs);
		%>
		<jsp:forward page="custuserlist.jsp"/>
		<% 
	}
}
%>
</body>
</html>
