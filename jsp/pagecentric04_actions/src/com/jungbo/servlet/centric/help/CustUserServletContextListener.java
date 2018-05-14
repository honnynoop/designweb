package com.jungbo.servlet.centric.help;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class CustUserServletContextListener 
                        implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("contextDestroyed bye! :"
				+new java.util.Date());
	}
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String contextPath= ".";
		ActionMapping maps=new ActionMapping(contextPath);
		maps.set("viewlist", "/custuserlist.jsp");
		maps.set("errors", "/custerror.jsp");
		maps.set("update", "/custuserupdate.jsp");
		maps.set("detail", "/custuserdetail.jsp");
		//ServletContextEvent에서 서블릿컨텍스트 얻기
		sce.getServletContext().setAttribute("maps", maps);
		System.out.println("contextInitialized : "+maps);
	}
}