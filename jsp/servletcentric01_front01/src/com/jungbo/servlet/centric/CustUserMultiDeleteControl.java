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
public class CustUserMultiDeleteControl extends HttpServlet {
	private static final long serialVersionUID = 5127200045451258675L;
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
		String[] ids=request.getParameterValues("delck");
		if(ids==null ||ids.length==0){
			CustError errors=new CustError();
			errors.setErrorMessage("여러 고객 삭제에 실패했습니다.");
			errors.setErrorType("요청파라메터, DB관련 ");
			request.setAttribute("errors", errors);
			dispatch("./custerror.jsp" , request,  response);
		}else{
			boolean isS=manager.deleteCustUsers(ids);
			if(isS){
				List<CustUserDto> custs= manager.getCustUserList();
				request.setAttribute("custs", custs);
				//원하는 곳으로 보내기
				dispatch("./custuserlist.jsp" , request,  response);
			}else{
				CustError errors=new CustError();
				errors.setErrorMessage("고객 삭제에 실패했습니다.");
				errors.setErrorType("요청파라메터, 한개 이상을 반드시 선택하십시오. ");
				request.setAttribute("errors", errors);
				dispatch("./custerror.jsp" , request,  response);
			}
		}	
	}//
	public void dispatch(String urls,HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		RequestDispatcher dispatch=request.getRequestDispatcher(urls);
		dispatch.forward(request, response);
	}//
}
