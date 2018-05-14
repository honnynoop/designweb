package com.jungbo.servlet.centric.control;
import java.io.IOException;
import javax.servlet.ServletContext;
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
	public void doGet(HttpServletRequest request, 
	HttpServletResponse response) throws ServletException, IOException {
		doProcess( request,  response);
	}
	public void doPost(HttpServletRequest request, 
	HttpServletResponse response) throws ServletException, IOException {
		doProcess( request,  response);
	}
	public void doProcess(HttpServletRequest request, 
	HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		response.setContentType("text/html; charset=euc-kr");
		//ServletContext
		ServletContext application=getServletContext();
		//1)factory -> single tone
		ActionFactory factory=ActionFactory.getInstance();
		//class config
		ActionClassMapping actionmaps=
	     (ActionClassMapping)application.getAttribute("actionmaps");
		//set class config
		factory.setActionmaps(actionmaps); 
		//2)command kind
		Command command=new Command(request);
		//3)command -> XXXAction
		Action cmd=factory.getCommand(command);
		//4)Map -> init in servlet , setted in Listener
		ActionMapping maps=
		 (ActionMapping)application.getAttribute("maps");
		//5)XXXAction execute
		ActionForward forword=cmd.execute(request, response ,maps);
		//6)Action -> view forward
		forword.go(request, response);
	}//
}