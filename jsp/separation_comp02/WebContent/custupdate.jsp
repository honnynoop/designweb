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
<jsp:useBean id="cust" class="com.jungbo.sepa.CustUserBean"/>
<jsp:setProperty name="cust" property="id" />
<jsp:setProperty name="cust" property="name" />
<jsp:setProperty name="cust" property="address" />
<jsp:useBean id="manager" class="com.jungbo.sepa.CustUserManager"/>
<body>
<%
//workbean을 이용하여 insert 실행하기
int count=0;
try {
	count=manager.updateCustUser(cust);
} catch (SQLException e) {
	throw new CustUserSQLException(
	"custuserupdate.jsp에서   실패.");
}
if(count>0){
	%>
	<script type="text/javascript">
	alert('성공적으로 고객정보 변경하였습니다.');
	location.href='custuserlist.jsp';
	</script>
	<% 
}else{
	%>
	<script type="text/javascript">
	alert('고객정보 변경에  실패하였습니다.');
	location.href='custuserupdate.jsp?id=<%=cust.getId()%>';
	</script>
	<% 
}
%>
</body>
</html>
