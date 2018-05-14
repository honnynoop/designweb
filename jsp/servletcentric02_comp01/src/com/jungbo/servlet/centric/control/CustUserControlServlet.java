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
	private static final long serialVersionUID = 6951053077064101953L;
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
		//1)요청 분기를 위한 파라메터 받기
		Command command=new Command(request);
		//2)Map 얻기 -->포워드할 화면 경로 얻기
		ActionMapping maps=(ActionMapping)application.getAttribute("maps");
		//3)분기별 실행
		Action comm=null;
		if(command.getCommand().equalsIgnoreCase("list")){
			comm=new CustUserListAction();
		}else if(command.getCommand().equalsIgnoreCase("add")){
			comm=new CustUserAddAction();
		}else if(command.getCommand().equalsIgnoreCase("detail")){
			comm=new CustUserDetailAction();
		} else if(command.getCommand().equalsIgnoreCase("bfupdate")){
			comm=new CustUserBeforeUpdateAction();
		} else if(command.getCommand().equalsIgnoreCase("update")){
			comm=new CustUserUpdateAction();
		} else if(command.getCommand().equalsIgnoreCase("delete")){
			comm=new CustUserDeleteAction();
		} else if(command.getCommand().equalsIgnoreCase("muldel")){
			comm=new CustUserMultiDeleteActioin();
		} else {comm=new CustUserNullAction();}
		//4)XXXAction 실행
		ActionForward forword=comm.execute(request, response ,maps);
		//5)Action 결과를 파악후 원하는 뷰로 이동
		forword.go(request, response);
	}//
}
