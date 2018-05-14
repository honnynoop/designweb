package com.jungbo.only;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jungbo.servlet.centric.dto.CustUserDto;
import com.jungbo.servlet.centric.model.CustUserService;
public class CustListServlet extends HttpServlet {
	private static final long serialVersionUID = -742219224855176087L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess( request,  response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess( request,  response);
	}
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out=response.getWriter();
		CustUserService service=CustUserService.getInstance();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>CustUser Lists</title>");
		out.println("<script type='text/javascript'>");
		out.println("function deletechecks(e){");
		out.println("	var x=document.getElementsByName('delck');");
		out.println("	var lng=x.length;");
		out.println("	for(i=0;i<lng;++i){");
		out.println("		x(i).checked=e;");
		out.println("	}");
		out.println("}");
		out.println("</script>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<div id='Content'>");
		out.println("<form action='./muldelcustuser' method='post'>");
		out.println("<table width='700' border='0' cellpadding='0' cellspacing='0'>");
		out.println("<col width='100'><col width='300'><col width='300'>");
		out.println("	<tr><td height='2' bgcolor='#0000ff' colspan='3'></td></tr>");
		out.println("	 <tr bgcolor='#F6F6F6'>");
		out.println("		<td bgcolor='YELLOW' align='center'>");
		out.println("		<input type='checkbox' name='alldel'   onclick='deletechecks(this.checked)'/>");
		out.println("		</td>");
		out.println("		<td>아 이 디</td>");
		out.println("		<td>이     름</td>");
		out.println("	 </tr>");
		out.println("	<tr><td height='1' bgcolor='#0000ff' colspan='3'></td></tr>");
		Vector<CustUserDto> custlist= service.getCustUserList();
		for(int i=0 ; i<custlist.size() ; i++){
			CustUserDto dto=custlist.get(i);
			out.println("  <tr bgcolor='#F6F6D6'>");
			out.println("	<td align='center' bgcolor='yellow'>");
			out.println("	<input type='checkbox' name='delck' value='"+dto.getId()+"'/>");
			out.println("	</td>");
			out.println("	<td>"+dto.getId()+"</td>");
			out.println("	<td><a href='./getcustuser?id="+dto.getId()+"'>"+dto.getName()+"</a></td>");
			out.println("	</tr>");
			out.println("  <tr><td height='1' bgcolor='#c0c0c0' colspan='3'></td></tr>");
		}
		if(custlist.size()==0){//존재하지 않으면
		  out.println("<tr bgcolor='#F6F6D6'>");
		  out.println("<td colspan='3' align='center'>고객 리스트가 존재하지 않습니다.</td></tr>");	
		}
		out.println("	<tr>");
		out.println("		<td align='center' height='1' bgcolor='#c0c0c0' colspan='3'>");
		out.println("		<input type='submit'  value='고객정보삭제'/>");
		out.println("		</td>");
		out.println("	</tr>");
		out.println("<tr><td height='2' bgcolor='#0000ff' colspan='3'></td></tr>");
		out.println("<tr bgcolor='#F6F6D6'>");
		out.println("<td colspan='3'><a href='custaddform.html'>고객추가하기</a></td>");
		out.println("</tr></table><br/>");
		out.println("<a href='index.html'>Home</a><br/>");
		out.println("</div></center></body></html>");
	}//
}//
