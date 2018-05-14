package com.jungbo.servlet.centric.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jungbo.paging.ResultLists;
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
		//CustUserManager manager=new CustUserManager();
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
				int startPage=1;
				int startBlock=1;
				//�� ��������
				ResultLists<CustUserDto> custs=manager.getCustUserList( startPage,  startBlock);
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
