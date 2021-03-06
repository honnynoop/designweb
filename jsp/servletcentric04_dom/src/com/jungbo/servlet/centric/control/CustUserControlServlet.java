package com.jungbo.servlet.centric.control;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jungbo.servlet.centric.config.ActionClassMapping;
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

		//1)팩토리 -> 싱글톤패턴으로 생성
		ActionFactory factory=ActionFactory.getInstance();
		//클래스 환경 변수 
		ActionClassMapping actionmaps=(ActionClassMapping)getServletContext().getAttribute("actionmaps");
		//System.out.println("ActionClassMapping actionmaps : 얻기"+actionmaps+" : "+new Date());
		//클래스 환경변수 입력
		factory.setActionmaps(actionmaps); 
		//System.out.println(actionmaps.maps());
		//2)커멘드 파악
		Command command=new Command(request);
		//3)커멘드 별 XXXAction 얻어오기
		Action cmd=factory.getCommand(command);
		//4)Map 얻기 -> init 서블릿에서 컨텍스트에 저장했던 경로
		ActionMapping maps=(ActionMapping)getServletContext().getAttribute("maps");
		//System.out.println("CustUserControlServlet maps : 얻기"+maps+" : "+new Date());
		//System.out.println(maps.maps());
		//5)XXXAction 실행
		ActionForward forword=cmd.execute(request, response ,maps);
		//6)Action 결과를 파악후 원하는 뷰로 이동
		forword.go(request, response);
	}//
}
