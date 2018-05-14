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
<title>CustUser MULDEL</title>
</head>
<body>
<%
//파라메터 받기  
//null 문자열인가 판별할 부분이 필요하다.
String[] ids=request.getParameterValues("delck");

if(ids == null || ids.length==0){
	throw new CustUserSQLException("한개 이상 선택해야 합니다.");
}//
//DB에 저장하기 시작부분

try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
} catch (ClassNotFoundException e) {
	System.out.println("1/6 로딩 실패");
	throw new ClassNotFoundException(
	 "custmuldel.jsp에서 클래스 로딩 하다 실패, 드라이버를 확인하십시오.");
}
String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
String user="hr";
String pwd="jungbo";
Connection conn= null;
PreparedStatement psmt=null;
int[] count=new int[ids.length];
String sql="DELETE FROM CUSTUSER WHERE ID=?";
try{
	conn=DriverManager.getConnection(url,user,pwd);
	conn.setAutoCommit(false);
	psmt=conn.prepareStatement(sql);
	for(int i=0;i<ids.length; i++){
		psmt.setString(1,ids[i].trim()); 
		psmt.addBatch();
	}//
	//updateCustUser 실행하기
	count=psmt.executeBatch();
	conn.commit();
}catch(SQLException se){
	try {
		conn.rollback();
	} catch (SQLException e) {
		
	}
	throw new CustUserSQLException(
			"custmuldel.jsp에서 데이터를 삭제하다 실패");
}finally{
	try {
		conn.setAutoCommit(true);
	} catch (SQLException e) {	}
	if(psmt!=null)psmt.close();
	if(conn!=null)conn.close();
}
boolean isS=true;
for(int i=0; i<count.length; i++){
	if(count[i]!=-2){
		isS=false;
		break;
	}
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
