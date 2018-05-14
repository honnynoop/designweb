package com.jungbo.servlet.centric;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustUserControl extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		response.setContentType("text/html; charset=euc-kr");		
		String command=request.getParameter("command");
		if (command.equalsIgnoreCase("add")){
			dispatch("/custAddCtr", request, response);
		}else if(command.equalsIgnoreCase("bfupdate")){
			dispatch("/custBfUpdateCtr", request, response);
		}else if(command.equalsIgnoreCase("update")){
			dispatch("/custUpdateCtr", request, response);
		}else if(command.equalsIgnoreCase("delete")){
			dispatch("/custDeleteCtr", request, response);
		}else if(command.equalsIgnoreCase("list")){
			dispatch("/custListCtr", request, response);
		}else if(command.equalsIgnoreCase("muldel")){
			dispatch("/custMulDelCtr", request, response);
		}else if(command.equalsIgnoreCase("detail")){
			dispatch("/custDetailCtr", request, response);
		}else{
			CustError errors=new CustError();
			errors.setErrorMessage("잘못된 경로로 요청을 하였습니다.");
			errors.setErrorType("요청파라메터");
			request.setAttribute("errors", errors);
			dispatch("./custerror.jsp" , request,  response);
		}
	}
	public void dispatch(String urls,HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		RequestDispatcher dispatch=request.getRequestDispatcher(urls);
		dispatch.forward(request, response);
	}//
}
