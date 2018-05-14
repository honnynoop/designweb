package com.jungbo.servlet.centric.control;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jungbo.servlet.centric.dto.CustUserDto;
import com.jungbo.servlet.centric.help.ActionForward;
import com.jungbo.servlet.centric.help.ActionMapping;
import com.jungbo.servlet.centric.help.CustError;
import com.jungbo.servlet.centric.model.CustUserService;
public class CustUserMultiDeleteActioin implements Action {
	@Override
	public ActionForward execute(
			HttpServletRequest request, 
			HttpServletResponse response,
			ActionMapping maps) {
		CustUserService manager=CustUserService.getInstance();
		String[] ids=request.getParameterValues("delck");
		ActionForward forword=new ActionForward();
		if(ids==null ||ids.length==0){
			CustError errors=new CustError();
			errors.setErrorMessage("���� �� ������ �����߽��ϴ�.");
			errors.setErrorType("��û�Ķ����, DB���� ");
			request.setAttribute("errors", errors);
			forword.setUrl(maps.getKey("errors"));
			forword.setRedirect(false);
		}else{
			boolean isS=manager.deleteCustUsers(ids);
			if(isS){
				List<CustUserDto> custs= manager.getCustUserList();
				request.setAttribute("custs", custs);
				forword.setUrl(maps.getKey("viewlist"));
				forword.setRedirect(false);
			}else{
				CustError errors=new CustError();
				errors.setErrorMessage("�� ������ �����߽��ϴ�.");
				errors.setErrorType("��û�Ķ����, �Ѱ� �̻��� �ݵ�� �����Ͻʽÿ�. ");
				request.setAttribute("errors", errors);
				forword.setUrl(maps.getKey("errors"));
				forword.setRedirect(false);
			}
		}
		return forword;
	}//
	
	

}
