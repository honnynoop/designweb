package com.jungbo.servlet.centric.model;
import java.util.Vector;
import com.jungbo.servlet.centric.dto.CustUserDto;
public interface ICustUserManager {
	public Vector<CustUserDto> getCustUserList() ;
	public CustUserDto getCustUser(String id) ;
	public int addCustUser(CustUserDto dto);
	public int updateCustUser(CustUserDto dto);
	public int deleteCustUser(String id);
	public boolean deleteCustUsers(String[] ids);
}//
