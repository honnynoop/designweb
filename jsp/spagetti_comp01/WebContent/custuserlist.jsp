<%@page contentType="text/html; charset=euc-kr" %>
<%@page errorPage="custerror.jsp"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.jungbo.except.CustUserSQLException"%>
<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<title>CustUser Lists</title>
<script type="text/javascript">
function deletechecks(e){
	var x=document.getElementsByName("delck");
	var lng=x.length;
	for(i=0;i<lng;++i){
		x(i).checked=e;		
	}
}
</script>
</head>
<body>
<%
try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	//Class.forName("oracle.OracleDriver"); //로딩실패 확인
} catch (ClassNotFoundException e) {
	System.out.println("1/6 로딩 실패");
	throw new ClassNotFoundException(
	 "custuserlist.jsp에서 클래스 로딩 하다 실패, 드라이버를 확인하십시오.");
}
String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
String user="hr";
String pwd="jungbo";
//String pwd="xxx";  //연결실패 확인
//쿼리 실패확인
//String sql=" SELECT ID,NAME,ADDRESS FORM CUSTUSER "+    
String sql=" SELECT ID,NAME,ADDRESS FROM CUSTUSER "+
" ORDER BY ID DESC ";
Connection conn= null;
PreparedStatement psmt=null;
ResultSet rs=null;
try {
%>
<center>
<form action="custmuldel.jsp" method='post'>
<table width="700" border="0" cellpadding="0" cellspacing="0">
<col width="100"><col width="300"><col width="300">
<tr><td height="2" bgcolor="#0000ff" colspan="3"></td></tr>
 <tr bgcolor="#F6F6F6">
 <td bgcolor='YELLOW' align='center'>
<input type='checkbox' name='alldel'   onclick="deletechecks(this.checked)"/>
</td>
<td>아 이 디</td>
 <td>이     름</td>
 </tr>
<tr><td height="1" bgcolor="#0000ff" colspan="3"></td></tr>
<% 
int count=0;
conn=DriverManager.getConnection(url,user,pwd);
psmt=conn.prepareStatement(sql);
rs=psmt.executeQuery();
while(rs.next()){
	count++;
	String id=rs.getString("ID");
	String name=rs.getString("NAME");
%>
<tr bgcolor='#F6F6D6'>
          <td align='center' bgcolor='yellow'>
<input type='checkbox' name='delck' value='<%=id%>'/>
      </td>
<td><%=id%></td>
<td> <a href='custuserdetail.jsp?id=<%=id%>'><%=name%></a></td>
</tr>
<tr><td height="1" bgcolor="#c0c0c0" colspan="3"></td></tr>

 <% 
}//while
if(count==0){//존재하지 않으면
	%>
<tr bgcolor='#F6F6D6'>
  <td colspan='3' align='center'>고객 리스트가 존재하지 않습니다.</td>
</tr>
	 <% 
}//end if count==0
} catch (SQLException e) {
	throw new CustUserSQLException("custuserlist.jsp에서 결과 출력 하다  실패");
}finally{
	if(rs!=null)rs.close();
	if(psmt!=null)psmt.close();
	if(conn!=null)conn.close();
}
%>
<tr><td align='center' height="1" bgcolor="#c0c0c0" colspan="3">
 <input type='submit'  value='고객정보삭제'/>
</td></tr>
<tr><td height="2" bgcolor="#0000ff" colspan="3"></td></tr>
<tr bgcolor='#F6F6D6'>
  <td colspan='3' ><a href='custaddform.jsp'>고객추가하기</a></td>
</tr>
</table>
</form>
<br/>
<a href='index.jsp'>Home</a><br/>
</center>
</body>
</html>