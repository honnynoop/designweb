package com.jungbo.pagecentric;
import java.util.List;
public class CustUserService {
	//�̱��� �������� ����
	private static CustUserService custUserService;
	private ICustUserManager manager;
	private CustUserService() {
		manager=new CustUserManager();
	}
	public static CustUserService getInstance(){
		if(custUserService==null){
			//dao ����
			custUserService=new CustUserService();
		}
		return custUserService;
	}
	//------------������� �̱��� ����
	public List<CustUserDto> getCustUserList(){
		return manager.getCustUserList();
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
