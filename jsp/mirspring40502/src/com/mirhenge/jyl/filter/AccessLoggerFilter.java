package com.mirhenge.jyl.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class AccessLoggerFilter implements Filter {
	
	private Logger logger = LoggerFactory.getLogger(AccessLoggerFilter.class);
	

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		
		
		//String ssoUser = request.getHeader("SSO_USER");
		
				
		String remoteAddr = StringUtils.defaultString(request.getRemoteAddr(), "-");
		String uri = StringUtils.defaultIfEmpty(request.getRequestURI(), "");
		String url = (request.getRequestURL() == null) ? "" : request.getRequestURL().toString();
		String queryString = StringUtils.defaultIfEmpty(request.getQueryString(), "");
		String referer = StringUtils.defaultString(request.getHeader("Referer"), "-");
		String agent = StringUtils.defaultString(request.getHeader("User-Agent"),"-");
		
		String fullUrl = url;
		fullUrl += StringUtils.isNotEmpty(queryString) ? "?"+queryString : queryString;
				
		StringBuilder result = new StringBuilder();
		result
			//.append(" Tomcat SSO User : " + ssoUser)
			.append(" : ")
			.append(remoteAddr)
			.append(" :")
			.append(fullUrl)
			.append(" :")
			.append(referer)
			.append(":")
			.append(agent);
		
		logger.info("[LOG FILTER] " + result.toString());
		
		chain.doFilter(req, res);
	}
	
	
	public void init(FilterConfig fileterConfig) throws ServletException {}
	
	
	public void destroy() {}
	
}












