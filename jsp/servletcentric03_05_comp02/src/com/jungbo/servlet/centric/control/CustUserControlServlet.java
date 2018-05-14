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
		//���ؽ�Ʈ ���
		ServletContext application=getServletContext();
		//��û�м�
		//1)��û �б⸦ ���� �Ķ���� �ޱ�
		Command command=new Command(request);
		//2)Map ��� -> jspInit ���� ���ؽ�Ʈ�� �����ߴ� ���
		//�������� ȭ�� ��� ���
		ActionMapping maps=(ActionMapping)application.getAttribute("maps");
		//ActionFactory ����
		ActionFactory factory=ActionFactory.getInstance();
		//Ŀ��� �� XXXAction ������
		Action comm=factory.getCommand(command);
		//4)XXXAction ����
		ActionForward forword=comm.execute(request, response ,maps);
		//5)Action ����� �ľ��� ���ϴ� ��� �̵�
		forword.go(request, response);
	}//
}