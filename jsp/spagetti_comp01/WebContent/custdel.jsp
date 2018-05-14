<%@ page contentType="text/html; charset=euc-kr" %>
<%@page errorPage="custerror.jsp" %>
<%@page import="com.jungbo.except.CustUserSQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.SQLException"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>CustUser Lists</title>
</head>
<body>
<%!
private boolean isNull(String str){
	return str==null || str.trim().equals("");
}
%>
<%
//파라메터 받기  
//null 문자열인가 판별할 부분이 필요하다.
String id=request.getParameter("id");
if(isNull(id)  ){
	throw new CustUserSQLException("파라메터가 널입니다. 모두 입력하시기 바랍니다.");
}//
//DB에 저장하기 시작부분
try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
} catch (ClassNotFoundException e) {
	System.out.println("1/6 로딩 실패");
	throw new ClassNotFoundException(
	 "custdel.jsp에서 클래스 로딩 하다 실패, 드라이버를 확인하십시오.");
}
String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
String user="hr";
String pwd="jungbo";
Connection conn= null;
PreparedStatement psmt=null;
int count=0;
String sql="DELETE FROM CUSTUSER WHERE ID=?";
try {
	conn=DriverManager.getConnection(url,user,pwd);
	psmt=conn.prepareStatement(sql);
	//받은 파라메터 쿼리의 ?에 넣기 
	psmt.setString(1,id); 
	//insert 실행하기
	count=psmt.executeUpdate();
} catch (SQLException e) {
	throw new CustUserSQLException(
	"custdel.jsp에서 쿼리 실행하다  실패, SQL 쿼리 확인하세요");
}finally{
	//실행후 닫기
	if(psmt!=null)psmt.close();
	if(conn!=null)conn.close();
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
	location.href='custuserdetail.jsp?id=<%=id%>';
	</script>
	<% 
}
%>
</body>
</html>
