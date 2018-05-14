package com.jungbo.pagecentric;

import java.util.List;
import java.util.Map;

import com.jungbo.paging.PageBean;
import com.jungbo.paging.ResultLists;

public class CustUserService {
	//�̱��� �������� ����
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
	public ResultLists<CustUserDto> getCustUserList(Map paramMap){
		//vector�� ������ ��ü�� �����ϴ� ��ü
		ResultLists<CustUserDto> results=new ResultLists<CustUserDto>();
		//DAO
		//DAO ���� 1
		//������ ��� ��ü
		PageBean pbean=new PageBean();
		pbean.setCountPerBlock(3);
		pbean.setCountPerPage(5);
		pbean.setStartBlock((Integer)paramMap.get("endpage"));
		pbean.setStartPage((Integer)paramMap.get("startpage"));
		paramMap.put("startpage", pbean.getCurPage());
		paramMap.put("endpage", pbean.getCurendPage());
		int totalCount=manager.getCount(paramMap);
		System.out.println("=========cnt:"+totalCount);
		pbean.setTotalCount(totalCount);
		//DAO ���� 2
		List<CustUserDto> custs=manager.getCustUserList(paramMap);
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
	
	public List<CustUserDto> getUserIdList(){
		return manager.getUserIdList();
	}
	
}
