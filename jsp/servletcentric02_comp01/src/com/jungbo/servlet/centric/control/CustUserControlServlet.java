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
		//���ؽ�Ʈ ���
		ServletContext application=getServletContext();
		//1)��û �б⸦ ���� �Ķ���� �ޱ�
		Command command=new Command(request);
		//2)Map ��� -->�������� ȭ�� ��� ���
		ActionMapping maps=(ActionMapping)application.getAttribute("maps");
		//3)�б⺰ ����
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
		//4)XXXAction ����
		ActionForward forword=comm.execute(request, response ,maps);
		//5)Action ����� �ľ��� ���ϴ� ��� �̵�
		forword.go(request, response);
	}//
}
