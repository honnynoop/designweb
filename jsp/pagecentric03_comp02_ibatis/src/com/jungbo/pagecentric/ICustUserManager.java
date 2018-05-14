package com.jungbo.pagecentric;
import java.util.List;
public interface ICustUserManager {
	public List<CustUserDto> getCustUserList();
	public CustUserDto getCustUser(String id) ;
	public int addCustUser(CustUserDto uDto);
	public int updateCustUser(CustUserDto uDto);
	public int deleteCustUser(String id);
	public boolean deleteCustUsers(String[] ids);
}//
