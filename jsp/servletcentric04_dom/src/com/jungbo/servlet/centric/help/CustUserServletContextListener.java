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
		System.out.println("ActionInitMappingFilter contextPath:    "+contextPath);
		ActionConfigMapping acm=new ActionConfigMapping(context);
		acm.setMapping("/config/resouce.xml");
		//System.out.println("ActionInitMappingFilter contextPath:    "+acm.maps());
		ActionMapping maps=acm.getActionMapping(contextPath);
		//System.out.println("ActionInitMappingFilter maps : 저장"+maps+" : "+new Date());
		context.setAttribute("maps", maps);
		
		ActionClassMapping aclm=new ActionClassMapping(context);
		aclm.setMapping("/config/actionclass.xml");
		//System.out.println("ActionClassMapping maps : 저장"+aclm+" : "+new Date());
		context.setAttribute("actionmaps", aclm);
	}
}