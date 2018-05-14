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
<jsp:useBean id="manager" class="com.jungbo.sepa.CustUserWorkBean"/>
<jsp:setProperty name="manager" property="id" />
<%
//파라메터 받기  
//null 문자열인가 판별할 부분이 필요하다.
if(manager.getId()==null || manager.getId().equals("") ){
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
	count=manager.deleteCustUser();
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
	location.href='custuserdetail.jsp?id=<%=manager.getIds()%>';
	</script>
	<% 
}
%>
</body>
</html>
