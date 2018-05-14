package com.mirhenge.jyl.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ControllerLoggingInterceptor extends HandlerInterceptorAdapter {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ControllerLoggingInterceptor.class);

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object controller, Exception e)
					throws Exception {}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object controller,
			ModelAndView modelAndView)
					throws Exception {
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
					throws Exception {
		Object siteLogin = request.getSession().getAttribute("dto");
		if (siteLogin == null) {
			System.out.println("dddddddddddddddddddddddddd");
			if (logger.isDebugEnabled()) {
				logger.debug(" ?ù∏Ï¶ùÍ∞í?ù¥ ?óÜ?äµ?ãà?ã§. ");
			}
			response.sendRedirect("login.jsp");
			return false;
		}
		return true;
		
	}
}