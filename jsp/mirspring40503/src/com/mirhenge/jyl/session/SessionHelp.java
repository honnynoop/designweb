package com.mirhenge.jyl.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mirhenge.jyl.member.model.JYLMember;

public class SessionHelp {
	/*
	 * �Ʒ��� ���� ����ϼ���.
		String id="";
	 try{
			id=SessionHelp.getId(request);//���� �޼���� ����
		}catch (Exception e) {
			return "redirect:/login.do";
		}
	 */
	private String getId(HttpServletRequest request) throws Exception{
		HttpSession session=request.getSession();
		Object oLogin=session.getAttribute("login");
		if(oLogin==null){
			throw new Exception("session�� ����.");
		}		
		return ((JYLMember)oLogin).getId();
	}
}
