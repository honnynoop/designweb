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
	//Class.forName("oracle.OracleDriver"); //�ε����� Ȯ��
} catch (ClassNotFoundException e) {
	System.out.println("1/6 �ε� ����");
	throw new ClassNotFoundException(
	 "custuserlist.jsp���� Ŭ���� �ε� �ϴ� ����, ����̹��� Ȯ���Ͻʽÿ�.");
}
String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
String user="hr";
String pwd="jungbo";
//String pwd="xxx";  //������� Ȯ��
//���� ����Ȯ��
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
<td>�� �� ��</td>
 <td>��     ��</td>
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
if(count==0){//�������� ������
	%>
<tr bgcolor='#F6F6D6'>
  <td colspan='3' align='center'>�� ����Ʈ�� �������� �ʽ��ϴ�.</td>
</tr>
	 <% 
}//end if count==0
} catch (SQLException e) {
	throw new CustUserSQLException("custuserlist.jsp���� ��� ��� �ϴ�  ����");
}finally{
	if(rs!=null)rs.close();
	if(psmt!=null)psmt.close();
	if(conn!=null)conn.close();
}
%>
<tr><td align='center' height="1" bgcolor="#c0c0c0" colspan="3">
 <input type='submit'  value='����������'/>
</td></tr>
<tr><td height="2" bgcolor="#0000ff" colspan="3"></td></tr>
<tr bgcolor='#F6F6D6'>
  <td colspan='3' ><a href='custaddform.jsp'>���߰��ϱ�</a></td>
</tr>
</table>
</form>
<br/>
<a href='index.jsp'>Home</a><br/>
</center>
</body>
</html>