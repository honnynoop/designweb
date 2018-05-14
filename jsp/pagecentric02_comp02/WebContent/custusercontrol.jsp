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
//command�� ���̸� custerror.jsp��  ���µ� ���� �޼����� ��������.
if(isNull(command)){
	request.setAttribute("errormsg","�߸��� ��û�Դϴ�.�ٽ� ��û�Ͻʽÿ�.");
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
	//�� ��������
	custvector= manager.getCustUserList();
	//request �������� ���
	request.setAttribute("custvector",custvector);
	//request �������� ���
	pageContext.forward("custuserlist.jsp");
}else if(command.equalsIgnoreCase("add")){
	//--------�Ķ���� �޾� �� �ֱ�
	%>
	<jsp:useBean id="custbean2" class="com.jungbo.pagecentric.CustUserBean"/>
	<jsp:setProperty name="custbean2" property="id" />
	<jsp:setProperty name="custbean2" property="name" />
	<jsp:setProperty name="custbean2" property="address" />
	<% 
	if(isNull(custbean2.getId()) 
			||isNull(custbean2.getName())
			||isNull(custbean2.getAddress()) ){
		request.setAttribute("errormsg","��� ������ �Է��ϼž� �մϴ�.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	//�� �߰��ϱ�
	int count=manager.addCustUser(custbean2.getId(),
			custbean2.getName(),custbean2.getAddress());
	if(count<1){
		request.setAttribute("errormsg","�� ���� �߰��� �����Ͽ����ϴ�.");
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
		request.setAttribute("errormsg","id�� �Է��ϼž� �մϴ�.");
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
		request.setAttribute("errormsg","��� ������ �Է��ϼž� �մϴ�.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	int count=manager.updateCustUser(custbeanup.getId(), 
			custbeanup.getName(), custbeanup.getAddress());
	
	if(count==0){
		request.setAttribute("errormsg","�� �߰��� �����Ͽ����ϴ�.");
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
		request.setAttribute("errormsg","id�� �Է��ϼž� �մϴ�.");
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
		request.setAttribute("errormsg","id�� �Է��ϼž� �մϴ�.");
		request.setAttribute("url","index.jsp");
		pageContext.forward("custerror.jsp");
	}//if
	int count=manager.deleteCustUser(custbeandel.getId());
	if(count==0){
		request.setAttribute("errormsg","�� ������ �����Ͽ����ϴ�.");
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
