package com.jungbo.servlet.centric.control;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jungbo.servlet.centric.help.ActionForward;
import com.jungbo.servlet.centric.help.ActionMapping;
import com.jungbo.servlet.centric.help.Command;
public class CustUserControlServlet extends HttpServlet {
	private static final long serialVersionUID = -4431910470067877726L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess( request,  response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess( request,  response);
	}
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		response.setContentType("text/html; charset=euc-kr");
		//컨텍스트 얻기
		ServletContext application=getServletContext();
		//요청분석
		//1)요청 분기를 위한 파라메터 받기
		Command command=new Command(request);
		//2)Map 얻기 -> jspInit 에서 컨텍스트에 저장했던 경로
		//포워드할 화면 경로 얻기
		ActionMapping maps=(ActionMapping)application.getAttribute("maps");
		//ActionFactory 생성
		ActionFactory factory=ActionFactory.getInstance();
		//커멘드 별 XXXAction 얻어오기
		Action comm=factory.getCommand(command);
		//4)XXXAction 실행
		ActionForward forword=comm.execute(request, response ,maps);
		//5)Action 결과를 파악후 원하는 뷰로 이동
		forword.go(request, response);
	}//
}