package com.jungbo.servlet.centric.help;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class ActionForward {
	private String url;
	private boolean isRedirect=false;
	public ActionForward() {
		isRedirect=false;
		url="";
	}
	public ActionForward(String url, boolean isRed) {
		this.url = url;
		this.isRedirect = isRed;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRed) {
		this.isRedirect = isRed;
	}
	public void go(HttpServletRequest request, 
			       HttpServletResponse response)
	throws ServletException, IOException {
		if(isRedirect()){
			redirect(getUrl() , request,  response);
		}else{
			dispatch(getUrl() , request,  response);
		}
	}//
	private void dispatch(String urls,HttpServletRequest request,
			                       HttpServletResponse response)
	throws ServletException, IOException {
		RequestDispatcher dispatch=request.getRequestDispatcher(urls);
		dispatch.forward(request, response);
	}//
	private void redirect(String urls,HttpServletRequest request,
			                        HttpServletResponse response)
	throws ServletException, IOException {
		response.sendRedirect(urls);
	}//
}//
