package com.jungbo.servlet.centric;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jungbo.servlet.centric.dto.CustUserDto;
import com.jungbo.servlet.centric.model.CustUserService;

public class CustUserDetailControl extends HttpServlet {
	
	private static final long serialVersionUID = -1716330707614372527L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess( request,  response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess( request,  response);
	}
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		response.setContentType("text/html; charset=euc-kr");
		//Dao 생성
		CustUserService manager=CustUserService.getInstance();
		//요청분석
		String id=request.getParameter("id");
		if(PageUtil.isNull(id)){
			CustError errors=new CustError();
			errors.setErrorMessage("아이디를 입력하십시오.");
			errors.setErrorType("요청파라메터");
			request.setAttribute("errors", errors);
			dispatch("./custerror.jsp" , request,  response);
		}
		CustUserDto cust= manager.getCustUser(id);
		request.setAttribute("cust", cust);
		//원하는 곳으로 보내기
		dispatch("./custuserdetail.jsp" , request,  response);
	}//
	
	public void dispatch(String urls,HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		RequestDispatcher dispatch=request.getRequestDispatcher(urls);
		dispatch.forward(request, response);
	}//
}
