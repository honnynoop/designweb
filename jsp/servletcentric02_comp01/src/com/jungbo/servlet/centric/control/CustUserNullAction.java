package com.jungbo.servlet.centric.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jungbo.servlet.centric.help.ActionForward;
import com.jungbo.servlet.centric.help.ActionMapping;
import com.jungbo.servlet.centric.help.CustError;

public class CustUserNullAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response, ActionMapping maps) {
		ActionForward forword=new ActionForward();
		CustError errors=new CustError();
		errors.setErrorMessage("��ü ������ ���� �߽��ϴ�.");
		errors.setErrorType("��û�Ķ����, Action Class  ������");
		request.setAttribute("errors", errors);
		forword.setUrl(maps.getKey("errors"));
		forword.setRedirect(false);
		return forword;
	}//
}
