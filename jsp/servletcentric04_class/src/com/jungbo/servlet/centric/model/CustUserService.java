package com.jungbo.servlet.centric.model;

import java.util.List;
import java.util.Vector;

import com.jungbo.paging.PageBean;
import com.jungbo.paging.ResultLists;
import com.jungbo.servlet.centric.dto.CustUserDto;

public class CustUserService {
	
	private static CustUserService custUserService;
	private ICustUserManager manager;
	private CustUserService() {
		manager=new CustUserManager();
	}
	public static CustUserService getInstance(){
		if(custUserService==null){
			custUserService=new CustUserService();
		}
		return custUserService;
	}
	//���� �ٿ��� �̿��ϰų� �޼��带 �����Ͽ� ���ϴ°�ü�� ����� ���� ��ü-> ���� ��ü
	public ResultLists<CustUserDto> getCustUserList(int startPage, int startBlock){
		//vector�� ������ ��ü�� �����ϴ� ��ü
		ResultLists<CustUserDto> results=new ResultLists<CustUserDto>();
		//DAO
		//DAO ���� 1
		int totalCount=manager.getCount();
		//������ ��� ��ü
		PageBean pbean=new PageBean();
		pbean.setCountPerBlock(3);
		pbean.setCountPerPage(2);
		pbean.setStartBlock(startBlock);
		pbean.setStartPage(startPage);
		pbean.setTotalCount(totalCount);
		//DAO ���� 2
		List<CustUserDto>  custs=manager.getCustUserList(
				pbean.getCurPage(),pbean.getCurendPage());
		results.setResults(custs);
		results.setPageBean(pbean);
		return results;
	}//
	
	public CustUserDto getCustUser(String id) {
		return manager.getCustUser(id);
	}
	public int addCustUser(CustUserDto uDto){
		return manager.addCustUser(uDto);
	}
	public int updateCustUser(CustUserDto uDto){
		return manager.updateCustUser(uDto);
	}
	public int deleteCustUser(String id){
		return manager.deleteCustUser(id);
	}
	public boolean deleteCustUsers(String[] ids){
		return manager.deleteCustUsers(ids);
	}
	
}
