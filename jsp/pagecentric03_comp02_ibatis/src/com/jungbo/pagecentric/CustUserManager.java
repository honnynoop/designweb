package com.jungbo.pagecentric;
import java.sql.SQLException;
import java.util.List;
import com.jungbo.ibatis.SqlDaoSuport;
//SqlDaoSuport를 상속받아서 iBATIS를 쉽게 사용하도록 한다.
public class CustUserManager 
       extends SqlDaoSuport implements ICustUserManager {
	public CustUserManager(String pass) {
		super(pass);
	}//
	public CustUserManager() {
		super();
	}//
	@SuppressWarnings("unchecked")
	public List<CustUserDto> getCustUserList(){
		List<CustUserDto> result=null;
		try {
			result=
		 (List<CustUserDto>)getSqlMapClient().queryForList("getCustUserList");
		} catch (Exception e) {
			System.out.println(e);
		}		 
		return result;
	}//
	public CustUserDto getCustUser(String id) {
		CustUserDto result=null;
		try {
			result=(CustUserDto)this.getSqlMapClient().queryForObject("getCustUser",id);
		} catch (Exception e) {
			System.out.println(e);
		}		 
		return result;
	}//
	public int addCustUser(CustUserDto uDto){
		int answer=1;
		try {
			this.getSqlMapClient().insert("addCustUser", uDto);
		} catch (Exception e) {
			System.out.println(e);
			answer=-1;
		}
		return answer;
	}//
	public int updateCustUser(CustUserDto uDto){
		int count=1;
		try {
			this.getSqlMapClient().update("updateCustUser", uDto);
		} catch (SQLException e) {
			System.out.println(e);
			count=-1;
		}
		return count;
	}//
	public int deleteCustUser(String id){
		int count=1;
		try{
			this.getSqlMapClient().delete("deleteCustUser",id);
		}catch (Exception e) {
			count=-1;
			System.out.println(e);
		}
		return count;
	}//
	public boolean deleteCustUsers(String[] ids){
		boolean isb=true;
		try{
			this.getSqlMapClient().startTransaction();
			this.getSqlMapClient().startBatch();
			for (int i = 0; i < ids.length; i++) {
				this.getSqlMapClient().delete("deleteCustUser",ids[i]);
			}					
			this.getSqlMapClient().executeBatch();
			this.getSqlMapClient().commitTransaction();
		}catch (Exception e) {
			isb=false;
			System.out.println(e);
		}
		finally{
			try {
				this.getSqlMapClient().endTransaction();
			} catch (SQLException e) {
			}
		}
		return isb;
	}//
}//
