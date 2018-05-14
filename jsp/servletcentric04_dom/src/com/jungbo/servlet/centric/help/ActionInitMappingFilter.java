package com.jungbo.servlet.centric.help;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.jungbo.servlet.centric.config.ActionClassMapping;
import com.jungbo.servlet.centric.config.ActionConfigMapping;

/**
 * Servlet Filter implementation class ActionInitMappingFilter
 */
public class ActionInitMappingFilter implements Filter {
	
    /**
     * Default constructor. 
     */
    public ActionInitMappingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("----filter----"+new java.util.Date());
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		String contextPath= ".";
		System.out.println("ActionInitMappingFilter contextPath:    "+contextPath);
		ActionConfigMapping acm=new ActionConfigMapping(fConfig.getServletContext());
		acm.setMapping("/config/resouce.xml");
		//System.out.println("ActionInitMappingFilter contextPath:    "+acm.maps());
		ActionMapping maps=acm.getActionMapping(contextPath);
		System.out.println("ActionInitMappingFilter maps : 저장"+maps+" : "+new Date());
		fConfig.getServletContext().setAttribute("maps", maps);
		
		ActionClassMapping aclm=new ActionClassMapping(fConfig.getServletContext());
		aclm.setMapping("/config/actionclass.xml");
		System.out.println("ActionClassMapping maps : 저장"+aclm+" : "+new Date());
		fConfig.getServletContext().setAttribute("actionmaps", aclm);
	}

}
