<%@ page contentType="text/html; charset=euc-kr" %>
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
String name=request.getParameter("name");
String address=request.getParameter("address");
if(isNull(id) ||isNull(name)||isNull(address) ){
	throw new CustUserSQLException("파라메터가 널입니다. 모두 입력하시기 바랍니다.");
}//
//DB에 저장하기 시작부분

Class.forName("oracle.jdbc.driver.OracleDriver");

String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
String user="hr";
String pwd="jungbo";

Connection conn= null;
PreparedStatement psmt=null;
int count=0;

String sql="INSERT INTO CUSTUSER VALUES(?,?,?)";
conn=DriverManager.getConnection(url,user,pwd);
psmt=conn.prepareStatement(sql);
//받은 파라메터 쿼리의 ?에 넣기 
psmt.setString(1,id); 
psmt.setString(2,name); 
psmt.setString(3,address); 
//insert 실행하기
count=psmt.executeUpdate();
//실행후 닫기
if(psmt!=null)psmt.close();
if(conn!=null)conn.close();
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
