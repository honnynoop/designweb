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

		//1)���丮 -> �̱����������� ����
		ActionFactory factory=ActionFactory.getInstance();
		//Ŭ���� ȯ�� ���� 
		ActionClassMapping actionmaps=(ActionClassMapping)getServletContext().getAttribute("actionmaps");
		//System.out.println("ActionClassMapping actionmaps : ���"+actionmaps+" : "+new Date());
		//Ŭ���� ȯ�溯�� �Է�
		factory.setActionmaps(actionmaps); 
		//System.out.println(actionmaps.maps());
		//2)Ŀ��� �ľ�
		Command command=new Command(request);
		//3)Ŀ��� �� XXXAction ������
		Action cmd=factory.getCommand(command);
		//4)Map ��� -> init �������� ���ؽ�Ʈ�� �����ߴ� ���
		ActionMapping maps=(ActionMapping)getServletContext().getAttribute("maps");
		//System.out.println("CustUserControlServlet maps : ���"+maps+" : "+new Date());
		//System.out.println(maps.maps());
		//5)XXXAction ����
		ActionForward forword=cmd.execute(request, response ,maps);
		//6)Action ����� �ľ��� ���ϴ� ��� �̵�
		forword.go(request, response);
	}//
}
