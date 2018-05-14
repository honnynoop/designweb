<%@ page contentType="text/html; charset=euc-kr" %>
<%@page errorPage="custerror.jsp" %>
<%@page import="java.sql.SQLException"%>
<%@page import="com.jungbo.except.CustUserSQLException"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>CustUser ADD</title>
</head>
<jsp:useBean id="custwb" class="com.jungbo.sepa.CustUserWorkBean"/>
<jsp:setProperty name="custwb" property="id" />
<jsp:setProperty name="custwb" property="name" />
<jsp:setProperty name="custwb" property="address" />
<body>
<%
//workbean을 이용하여 insert 실행하기
int count=0;
if(custwb.getId()==null || custwb.getId().equals("")
||custwb.getName()==null || custwb.getName().equals("")
||custwb.getAddress()==null || custwb.getAddress().equals("")){
	throw new CustUserSQLException(
	"custadd.jsp  공백을 추가할 수 없습니다.");
}	
try {
	count=custwb.addCustUser();
} catch (SQLException e) {
	throw new CustUserSQLException(
	"custadd.jsp에서 쿼리 준비하다  실패.");
}
//결과 행의 수를 이용
if(count>0){
	%>
	<script type="text/javascript">
	alert('성공적으로 고객을 추가하였습니다.');
	location.href='custuserlist.jsp';
	</script>
	<% 
}else{
	%>
	<script type="text/javascript">
	alert('고객 추가에 실패하였습니다.');
	location.href='custaddform.jsp';
	</script>
	<% 
}
%>
</body>
</html>
