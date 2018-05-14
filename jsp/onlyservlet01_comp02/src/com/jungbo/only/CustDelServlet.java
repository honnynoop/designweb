package com.jungbo.only;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jungbo.servlet.centric.model.CustUserService;
public class CustDelServlet extends HttpServlet {
	private static final long serialVersionUID = 5058882855094577085L;
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
		int count=service.deleteCustUser(id);
		if(count>=1){//입력에 성공하면
			out.println("<script type='text/javascript'>");
			out.println("alert('성공적으로 고객을 삭제하였습니다.');");
			out.println("location.href='./custuserlist';");
			out.println("</script>");
		}else{
			out.println("<script type='text/javascript'>");
			out.println("alert('고객 삭제에 실패하였습니다.');");
			out.println("location.href='./custaddform.html';");
			out.println("</script>");
		}
		out.println("</body></html>");
	}//

}
