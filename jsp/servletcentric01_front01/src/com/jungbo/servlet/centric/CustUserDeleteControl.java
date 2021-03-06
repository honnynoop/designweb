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
public class CustUserDeleteControl extends HttpServlet {
	private static final long serialVersionUID = -2318624037923946157L;
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
		int count= manager.deleteCustUser(id);
		if(count>0){
			List<CustUserDto> custs= manager.getCustUserList();
			request.setAttribute("custs", custs);
			//원하는 곳으로 보내기
			dispatch("./custuserlist.jsp" , request,  response);
		}else{
			CustError errors=new CustError();
			errors.setErrorMessage("입력에 실패하였습니다.");
			errors.setErrorType("DB 작업");
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
