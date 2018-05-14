package com.jungbo.servlet.centric.control;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jungbo.servlet.centric.dto.CustUserDto;
import com.jungbo.servlet.centric.help.ActionForward;
import com.jungbo.servlet.centric.help.ActionMapping;
import com.jungbo.servlet.centric.help.CustError;
import com.jungbo.servlet.centric.model.CustUserService;
public class CustUserBeforeUpdateAction implements Action {
	public ActionForward execute(
			HttpServletRequest request, 
			HttpServletResponse response,
			ActionMapping maps) {
		CustUserService manager=CustUserService.getInstance();
		String id=request.getParameter("id");
		CustUserDto cust= manager.getCustUser(id);
		ActionForward forword=new ActionForward();
		if(cust!=null){
			request.setAttribute("cust", cust);
			forword.setUrl(maps.getKey("update"));
			forword.setRedirect(false);
		}else{
			CustError errors=new CustError();
			errors.setErrorMessage("고객 정보를 가져올때 실패했습니다.");
			errors.setErrorType("요청파라메터, DB관련 ");
			request.setAttribute("errors", errors);
			forword.setUrl(maps.getKey("errors"));
			forword.setRedirect(false);
		}
		return forword;
	}//
}
