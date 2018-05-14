package com.jungbo.servlet.centric.help;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.jungbo.servlet.centric.config.ActionClassMapping;
import com.jungbo.servlet.centric.config.ActionConfigMapping;
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
		ServletContext context=sce.getServletContext();
		//view mapping
		ActionConfigMapping acm=new ActionConfigMapping(context);
		acm.setMapping("/config/resouce.xml");
		ActionMapping maps=acm.getActionMapping(contextPath);
		context.setAttribute("maps", maps);
		//action class mapping
		ActionClassMapping aclm=new ActionClassMapping(context);
		aclm.setMapping("/config/actionclass.xml");
		context.setAttribute("actionmaps", aclm);
	}
}