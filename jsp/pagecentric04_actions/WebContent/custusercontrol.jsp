<%@ page contentType="text/html; charset=euc-kr" %>
<%@page import="com.jungbo.servlet.centric.control.Action"%>
<%@page import="com.jungbo.servlet.centric.help.Command"%>
<%@page import="com.jungbo.servlet.centric.help.ActionMapping"%>
<%@page import="java.util.Date"%>
<%@page import="com.jungbo.servlet.centric.help.CustError"%>
<%@page import="com.jungbo.servlet.centric.control.CustErrorAction"%>
<%@page import="com.jungbo.servlet.centric.control.CustUserListAction"%>
<%@page import="com.jungbo.servlet.centric.control.CustUserAddAction"%>
<%@page import="com.jungbo.servlet.centric.control.CustUserDetailAction"%>
<%@page import="com.jungbo.servlet.centric.control.CustUserBeforeUpdateAction"%>
<%@page import="com.jungbo.servlet.centric.control.CustUserUpdateAction"%>
<%@page import="com.jungbo.servlet.centric.control.CustUserDeleteAction"%>
<%@page import="com.jungbo.servlet.centric.control.CustUserMultiDeleteActioin"%>
<%@page import="com.jungbo.servlet.centric.help.ActionForward"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>control</title>
</head>
<body>
<%
//1)��û �б⸦ ���� �Ķ���� �ޱ�
Command command=new Command(request);
//2)Map ��� -> jspInit ���� ���ؽ�Ʈ�� �����ߴ� ���
//�������� ȭ�� ��� ���
ActionMapping maps=(ActionMapping)application.getAttribute("maps");
//System.out.println("maps : "+maps+" : "+new Date());
//3)�б⺰ ����
Action comm=null;
if(command.getCommand().equalsIgnoreCase("list")){
	comm=new CustUserListAction();
}else if(command.getCommand().equalsIgnoreCase("add")){
	comm=new CustUserAddAction();
}else if(command.getCommand().equalsIgnoreCase("detail")){
	comm=new CustUserDetailAction();
} else if(command.getCommand().equalsIgnoreCase("bfupdate")){
	comm=new CustUserBeforeUpdateAction();
} else if(command.getCommand().equalsIgnoreCase("update")){
	comm=new CustUserUpdateAction();
} else if(command.getCommand().equalsIgnoreCase("delete")){
	comm=new CustUserDeleteAction();
} else if(command.getCommand().equalsIgnoreCase("muldel")){
	comm=new CustUserMultiDeleteActioin();
} else {
	comm=new CustErrorAction();
}
//4)XXXAction ����
ActionForward forword=comm.execute(request, response ,maps);
//5)Action ����� �ľ��� ���ϴ� ��� �̵�
forword.go(request, response);
%>
</body>
</html>
