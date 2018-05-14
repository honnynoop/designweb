package com.jungbo.servlet.centric.control;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jungbo.paging.ResultLists;
import com.jungbo.servlet.centric.dto.CustUserDto;
import com.jungbo.servlet.centric.help.ActionForward;
import com.jungbo.servlet.centric.help.ActionMapping;
import com.jungbo.servlet.centric.help.CustError;
import com.jungbo.servlet.centric.model.CustUserService;
public class CustUserDeleteAction implements Action {

	@Override
	public ActionForward execute(
			HttpServletRequest request, 
			HttpServletResponse response,
			ActionMapping maps) {
		CustUserService manager=CustUserService.getInstance();
		String id=request.getParameter("id");
		int count= manager.deleteCustUser(id);
		ActionForward forword=new ActionForward();
		if(count>0){
			//�� ��������
			ResultLists<CustUserDto> custs=manager.getCustUserList( 1,  1);
			//request�� ���
			request.setAttribute("custs",custs);
			forword.setUrl(maps.getKey("viewlist"));
			forword.setRedirect(false);
		}else{
			CustError errors=new CustError();
			errors.setErrorMessage("�� ������ �����߽��ϴ�.");
			errors.setErrorType("��û�Ķ����, DB���� ");
			request.setAttribute("errors", errors);
			forword.setUrl(maps.getKey("errors"));
			forword.setRedirect(false);
		}
		return forword;
	}//
}
