package com.jungbo.servlet.centric.model;
import java.util.List;
import com.jungbo.servlet.centric.dto.CustUserDto;
public interface ICustUserManager {
	public List<CustUserDto> getCustUserList();
	public CustUserDto getCustUser(String id) ;
	public int addCustUser(CustUserDto uDto);
	public int updateCustUser(CustUserDto uDto);
	public int deleteCustUser(String id);
	public boolean deleteCustUsers(String[] ids);
}//
