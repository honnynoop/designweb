package com.jungbo.servlet.centric;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jungbo.servlet.centric.dto.CustUserDto;
import com.jungbo.servlet.centric.model.CustUserService;
public class CustUserListControl extends HttpServlet { 
	private static final long serialVersionUID = -5569879474458053375L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess( request,  response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess( request,  response);
	}
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		response.setContentType("text/html; charset=euc-kr");
		String scmd=request.getParameter("command");
		if(scmd!=null && scmd.equals("list")){
			CustUserService manager=CustUserService.getInstance();
			List<CustUserDto> custs= manager.getCustUserList();
			request.setAttribute("custs", custs);
			//원하는 곳으로 보내기
			dispatch("./custuserlist.jsp" , request,  response);
		}else{
			CustError errors=new CustError();
			errors.setErrorMessage("잘못된 경로로 요청을 하였습니다.");
			errors.setErrorType("요청파라메터");
			request.setAttribute("errors", errors);
			dispatch("./custerror.jsp" , request,  response);
		}
	}//
	public void dispatch(String urls,HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		RequestDispatcher dispatch=request.getRequestDispatcher(urls);
		dispatch.forward(request, response);
	}//
}//
