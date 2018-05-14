package com.jungbo.only;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jungbo.servlet.centric.dto.CustUserDto;
import com.jungbo.servlet.centric.model.CustUserService;

public class CustDetailServlet extends HttpServlet {
	private static final long serialVersionUID = -5581757997721323939L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess( request,  response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess( request,  response);
	}
	private boolean isNull(String str){
		return str==null || str.trim().equals("");
	}
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out=response.getWriter();
		CustUserService service=CustUserService.getInstance();
		out.println("<html><head>");
		out.println("<title>CustUser Lists</title>");
		out.println("</head><body>");
		String id=request.getParameter("id");
		if(isNull(id)){
			response.sendRedirect("./index.html");
		}//
		out.println("<center>");
		out.println("<table width='600' border='0' cellpadding='0' cellspacing='0'>");
		out.println("<col width='200'><col width='400'>");
		out.println("<tr><td height='2' bgcolor='#0000ff' colspan='2'></td></tr>");
		CustUserDto dto= service.getCustUser(id);
		if(dto!=null && dto.getId()!=null){
				out.println(" <tr bgcolor='#F6F6F6'>");
				out.println(" <td>�� �� ��</td>");
				out.println(" <td>"+dto.getId()+"</td>");
				out.println(" </tr>");
				out.println("<tr><td height='1' bgcolor='#0000ff' colspan='2'></td></tr>");
				out.println("<tr bgcolor='#F6F6F6'>");
				out.println(" <td>��      ��</td>");
				out.println(" <td>"+dto.getName()+"</td>");
				out.println(" </tr>");
				out.println("<tr><td height='1' bgcolor='#0000ff' colspan='2'></td></tr>");
				out.println("<tr bgcolor='#F6F6F6'>");
				out.println(" <td>��      ��</td>");
				out.println(" <td>"+dto.getAddress()+"</td>");
				out.println(" </tr>");
				out.println("<tr><td height='1' bgcolor='#0000ff' colspan='2'></td></tr>");
				out.println("<tr bgcolor='#F6F6F6'>");
				out.println(" <td>�� �� �� �� �� ��</td>");
				out.println(" <td>");
				out.println("<form action='./custbfupdate' method='post'>");
				out.println("      <input type='hidden' name='id' value='"+dto.getId()+"'/>");
				out.println("      <input type='submit'  value='����������'/>");
				out.println("  </form>");
				out.println("</td>");
				out.println(" </tr>");
				out.println("<tr><td height='1' bgcolor='#0000ff' colspan='2'></td></tr>");
				out.println("<tr bgcolor='#F6F6F6'>");
				out.println(" <td>��      ��</td>");
				out.println(" <td>");
				out.println("<form action='./delcustuser' method='post'>");
				out.println("      <input type='hidden' name='id' value='"+dto.getId()+"'/>");
				out.println("    <input type='submit'  value='����������'/>");
				out.println("</form>");
				out.println("</td>");
				out.println(" </tr>");
				out.println("<tr><td height='1' bgcolor='#0000ff' colspan='2'></td></tr>");
				out.println("</table>");
				out.println("<br/>");
				out.println("<a href='index.html'>Home</a><br/>");
			}
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}//

}
