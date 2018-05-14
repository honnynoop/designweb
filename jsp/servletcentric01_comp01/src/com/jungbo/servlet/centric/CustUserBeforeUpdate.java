package com.jungbo.servlet.centric;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jungbo.servlet.centric.dto.CustUserDto;
import com.jungbo.servlet.centric.model.CustUserService;
public class CustUserBeforeUpdate extends HttpServlet {
	private static final long serialVersionUID = -6345830762709837004L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess( request,  response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess( request,  response);
	}
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		response.setContentType("text/html; charset=euc-kr");
		//Dao ����
		CustUserService manager=CustUserService.getInstance();
		//��û�м�
		String scmd=request.getParameter("command");
		if(scmd!=null && scmd.equals("bfupdate")){
			String id=request.getParameter("id");
			if(PageUtil.isNull(id)){
				CustError errors=new CustError();
				errors.setErrorMessage("���̵� �Է��Ͻʽÿ�.");
				errors.setErrorType("��û�Ķ����");
				request.setAttribute("errors", errors);
				dispatch("./custerror.jsp" , request,  response);
			}
			CustUserDto cust= manager.getCustUser(id);
			request.setAttribute("cust", cust);
			//���ϴ� ������ ������
			dispatch("./custuserupdate.jsp" , request,  response);
		}else{
			CustError errors=new CustError();
			errors.setErrorMessage("�߸��� ��η� ��û�� �Ͽ����ϴ�.");
			errors.setErrorType("��û�Ķ����");
			request.setAttribute("errors", errors);
			dispatch("./custerror.jsp" , request,  response);
		}
	}//
	public void dispatch(String urls,HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		RequestDispatcher dispatch=request.getRequestDispatcher(urls);
		dispatch.forward(request, response);
	}//
}
