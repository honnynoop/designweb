package com.jungbo.only;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jungbo.servlet.centric.dto.CustUserDto;
import com.jungbo.servlet.centric.model.CustUserService;

public class CustUpdateServlet  extends HttpServlet {

	private static final long serialVersionUID = 8632210818951469253L;
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
		//--------파라메터 받기
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		if(isNull(id) ||isNull(name)||isNull(address) ){
			response.sendRedirect("./index.html");
		}//
	    int count=service.updateCustUser(new CustUserDto(id,name,address));
		if(count>=1){//입력에 성공하면
			out.println("<script type='text/javascript'>");
			out.println("alert('성공적으로 정보를  수정하였습니다.');");
			out.println("location.href='./custuserlist';");
			out.println("</script>");
		}else{
			out.println("<script type='text/javascript'>");
			out.println("alert('정보 수정에 실패하였습니다.');");
			out.println("location.href='./custaddform.html';");
			out.println("</script>");
		}
		out.println("</body></html>");
	}//
}