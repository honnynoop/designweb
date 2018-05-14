package com.jungbo.servlet.centric.control;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jungbo.servlet.centric.dto.CustUserDto;
import com.jungbo.servlet.centric.help.ActionForward;
import com.jungbo.servlet.centric.help.ActionMapping;
import com.jungbo.servlet.centric.help.CustError;
import com.jungbo.servlet.centric.model.CustUserService;
public class CustUserAddAction implements Action {
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
			CustError errors=new CustError();
			errors.setErrorMessage("�� �߰��� �����Ͽ����ϴ�.");
			errors.setErrorType("�߸��� ��û�Ķ���� ");
			request.setAttribute("errors", errors);
			forword.setUrl(maps.getKey("errors"));
			forword.setRedirect(false);
		}else{
			count=manager.addCustUser(
					new CustUserDto(id,name,address));
		}
		if(count>0){
			CustUserDto cust= manager.getCustUser(id);
			request.setAttribute("cust", cust);
			forword.setUrl(maps.getKey("detail"));
			forword.setRedirect(false);
		}else{
			CustError errors=new CustError();
			errors.setErrorMessage("�� �߰��� �����Ͽ����ϴ�.");
			errors.setErrorType("DB����, �̹� ���� �����Ͽ� �߰��� �� �����ϴ�. ");
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
