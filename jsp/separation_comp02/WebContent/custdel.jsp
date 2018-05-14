<%@ page contentType="text/html; charset=euc-kr" %>
<%@page errorPage="custerror.jsp" %>
<%@page import="com.jungbo.except.CustUserSQLException"%>
<%@page import="java.sql.SQLException"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>CustUser DELETE</title>
</head>
<body>
<jsp:useBean id="cust" class="com.jungbo.sepa.CustUserBean"/>
<jsp:setProperty name="cust" property="id" />
<jsp:useBean id="manager" class="com.jungbo.sepa.CustUserManager"/>
<%
if(cust.getId()==null || cust.getId().equals("") ){
	%>
	<script type='text/javascript'>
	alert('잘못된 경로로 요청을 하였습니다.');
	location.href='index.jsp';
	</script>
	<% 
	return;
}
int count=0;
try {
	count=manager.deleteCustUser(cust.getId());
} catch (SQLException e) {
	throw new CustUserSQLException(
	"custdel.jsp에서   실패.");
}
//결과 행의 수를 이용
if(count>0){
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
	location.href='custuserdetail.jsp?id=<%=cust.getId()%>';
	</script>
	<% 
}
%>
</body>
</html>
