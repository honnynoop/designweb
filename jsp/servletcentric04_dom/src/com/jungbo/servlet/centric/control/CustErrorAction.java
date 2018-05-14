package com.jungbo.servlet.centric.control;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jungbo.servlet.centric.help.ActionForward;
import com.jungbo.servlet.centric.help.ActionMapping;
import com.jungbo.servlet.centric.help.CustError;
public class CustErrorAction implements Action {
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response, ActionMapping maps) {
		ActionForward forword=new ActionForward();
		CustError errors=new CustError();
		errors.setErrorMessage("잘못된 요청입니다.");
		errors.setErrorType(" command가 널일 수 없습니다. ");
		request.setAttribute("errors", errors);
		forword.setUrl(maps.getKey("errors"));//경로
		forword.setRedirect(false);
		return forword;
	}
}
