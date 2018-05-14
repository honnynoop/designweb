package com.jungbo.servlet.centric.model;
import java.util.Vector;
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
	public Vector<CustUserDto> getCustUserList() {
		return manager.getCustUserList();
	}//
	public CustUserDto getCustUser(String id) {
		return manager.getCustUser(id);
	}
	public int addCustUser(CustUserDto dto){
		return manager.addCustUser(dto);
	}
	public int updateCustUser(CustUserDto dto){
		return manager.updateCustUser(dto);
	}
	public int deleteCustUser(String id){
		return manager.deleteCustUser(id);
	}
	public boolean deleteCustUsers(String[] ids){
		return manager.deleteCustUsers(ids);
	}
}