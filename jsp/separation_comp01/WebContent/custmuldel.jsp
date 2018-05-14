<%@ page contentType="text/html; charset=euc-kr" %>
<%@page errorPage="custerror.jsp" %>
<%@page import="com.jungbo.except.CustUserSQLException"%>
<%@page import="java.sql.SQLException"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>CustUser MULDEL</title>
</head>
<body>
<jsp:useBean id="custwb" class="com.jungbo.sepa.CustUserWorkBean"/>
<jsp:setProperty name="custwb" property="ids" />
<%
//파라메터 받기  
//null 문자열인가 판별할 부분이 필요하다.
if(custwb.getIds() == null || custwb.getIds() .length==0){
	throw new CustUserSQLException("한개 이상 선택해야 합니다.");
}//
boolean isS=false;
try {
	isS=custwb.deleteCustUsers();
} catch (SQLException e) {
	throw new CustUserSQLException(
	"custmuldel.jsp에서   실패.");
}
//결과 행의 수를 이용
if(isS){
	%>
	<script type="text/javascript">
	alert('성공적으로 고객을  삭제하였습니다.');
	location.href='custuserlist.jsp';
	</script>
	<% 
}else{
	%>
	<script type="text/javascript">
	alert('고객 삭제에 실패하였습니다.');
	location.href='custuserlist.jsp';
	</script>
	<% 
}
%>
</body>
</html>
