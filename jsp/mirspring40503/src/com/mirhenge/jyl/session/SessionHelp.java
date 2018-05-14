package com.mirhenge.jyl.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mirhenge.jyl.member.model.JYLMember;

public class SessionHelp {
	/*
	 * 아래와 같이 사용하세요.
		String id="";
	 try{
			id=SessionHelp.getId(request);//위에 메서드로 만듬
		}catch (Exception e) {
			return "redirect:/login.do";
		}
	 */
	private String getId(HttpServletRequest request) throws Exception{
		HttpSession session=request.getSession();
		Object oLogin=session.getAttribute("login");
		if(oLogin==null){
			throw new Exception("session이 없다.");
		}		
		return ((JYLMember)oLogin).getId();
	}
}
