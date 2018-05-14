package com.jungbo.servlet.centric.control;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jungbo.servlet.centric.dto.CustUserDto;
import com.jungbo.servlet.centric.help.ActionForward;
import com.jungbo.servlet.centric.help.ActionMapping;
import com.jungbo.servlet.centric.help.CustError;
import com.jungbo.servlet.centric.model.CustUserService;
public class CustUserListAction implements Action {
	public ActionForward execute(
			HttpServletRequest request, 
			HttpServletResponse response,
			ActionMapping maps) {
		CustUserService manager=CustUserService.getInstance();
		List<CustUserDto> custs= manager.getCustUserList();
		ActionForward forword=new ActionForward();
		if(custs!=null ){ //고객 가져오기 성공
			request.setAttribute("custs", custs);
			forword.setUrl(maps.getKey("viewlist"));
			forword.setRedirect(false);
		}else{
			CustError errors=new CustError();
			errors.setErrorMessage("고객 추가에 실패하였습니다.");
			errors.setErrorType("요청파라메터, DB관련 ");
			request.setAttribute("errors", errors);
			forword.setUrl(maps.getKey("errors"));
			forword.setRedirect(false);
		}
		return forword;
	}//
}
