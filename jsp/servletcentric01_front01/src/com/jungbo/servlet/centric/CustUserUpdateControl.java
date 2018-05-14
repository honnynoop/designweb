package com.jungbo.servlet.centric;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jungbo.servlet.centric.dto.CustUserDto;
import com.jungbo.servlet.centric.model.CustUserService;
public class CustUserUpdateControl extends HttpServlet {
	private static final long serialVersionUID = 8365830815777907815L;
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
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		if(isNull(id) ||isNull(name)||isNull(address) ){
			CustError errors=new CustError();
			errors.setErrorMessage("공백이거나 잘못된 문자형식입니다..");
			errors.setErrorType("요청파라메터");
			request.setAttribute("errors", errors);
			dispatch("./custerror.jsp" , request,  response);
		}else{
			int count=manager.updateCustUser(
					new CustUserDto(id,name,address));
			if(count>0){
				CustUserDto cust= manager.getCustUser(id);
				request.setAttribute("cust", cust);
				//원하는 곳으로 보내기
				dispatch("./custuserdetail.jsp" , request,  response);
			}else{
				CustError errors=new CustError();
				errors.setErrorMessage("입력에 실패하였습니다.");
				errors.setErrorType("DB 작업");
				request.setAttribute("errors", errors);
				dispatch("./custerror.jsp" , request,  response);
			}
		}
	}//	
	private boolean isNull(String str){
		return str==null || str.trim().equals("");
	}
	public void dispatch(String urls,HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		RequestDispatcher dispatch=request.getRequestDispatcher(urls);
		dispatch.forward(request, response);
	}//
}