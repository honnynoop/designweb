package com.jungbo.pagecentric;

import java.util.List;
import java.util.Map;

public interface ICustUserManager {
	//public Vector<CustUserDto> getCustUserList() ; //XML에서 오버라이딩 불가 
	public List<CustUserDto> getCustUserList(Map paramMap);
	public List<CustUserDto> getUserIdList();
	public int getCount(Map paramMap);
	public CustUserDto getCustUser(String id) ;
	public int addCustUser(CustUserDto uDto);
	public int updateCustUser(CustUserDto uDto);
	public int deleteCustUser(String id);
	public boolean deleteCustUsers(String[] ids);
}//
