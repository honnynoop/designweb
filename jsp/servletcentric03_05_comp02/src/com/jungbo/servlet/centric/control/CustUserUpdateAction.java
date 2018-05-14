package com.jungbo.servlet.centric.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jungbo.servlet.centric.dto.CustUserDto;
import com.jungbo.servlet.centric.help.ActionForward;
import com.jungbo.servlet.centric.help.ActionMapping;
import com.jungbo.servlet.centric.help.CustError;
import com.jungbo.servlet.centric.model.CustUserService;

public class CustUserUpdateAction implements Action {

	@Override
	public ActionForward execute(
			HttpServletRequest request, 
			HttpServletResponse response,
			ActionMapping maps) {
		
		CustUserService manager=CustUserService.getInstance();
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		int count=0;
		ActionForward forword=new ActionForward();
		if(isNull(id) ||isNull(name)||isNull(address) ){
			count=0;
		}else{
			count=manager.updateCustUser(
					new CustUserDto(id,name,address));
		}
		if(count>0){//
			//update된 데이터 가져오기
			CustUserDto cust= manager.getCustUser(id);
			//request 스코프 객체에 담기
			request.setAttribute("cust", cust);
			//포워드
			forword.setUrl(maps.getKey("detail"));
			forword.setRedirect(false);
		}else{
			CustError errors=new CustError();
			errors.setErrorMessage("고객 정보 변경에 실패하였습니다.");
			errors.setErrorType("DB관련, 고객 정보 변경중에 실패하였습니다." +
					" 없는 아이디인가를 확인하십시오. ");
			request.setAttribute("errors", errors);
			forword.setUrl(maps.getKey("errors"));
			forword.setRedirect(false);
		}
		return forword;
	}//
	
	private boolean isNull(String str){
		return str==null || str.trim().equals("");
	}
}
