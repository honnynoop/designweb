package com.jungbo.servlet.centric.model;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jungbo.ibatis.SqlDaoSuport;
import com.jungbo.servlet.centric.dto.CustUserDto;
//다오서포트를 상속받아서 IBatis를 쉽게 사용하도록 한다.
public class CustUserManager extends SqlDaoSuport implements ICustUserManager {

	public CustUserManager(String pass) {
		super(pass);
	}//
	public CustUserManager() {
		super();
	}//
	
	@SuppressWarnings("unchecked")
	public List<CustUserDto> getCustUserList(int startpage, int endpage){
		Map<String, Integer> params = new HashMap<String, Integer>();
		System.out.println("--------getCustUserList------------");
		System.out.println("startpage= "+startpage+"\t endpage="+endpage);
		System.out.println("------------------------------");
		params.put("startpage", startpage);
		params.put("endpage", endpage);
		List<CustUserDto> result=null;
		try {
			result=(List<CustUserDto>)this.getSqlMapClient().queryForList("getCustUserList",params);
		} catch (Exception e) {

			System.out.println(e);
		}		 
		return result;
	}//
	public int getCount() {
		int answer=-1;
		System.out.println("--------getCount------------");
		System.out.println("--------------------");
		try {
			answer = (Integer)this.getSqlMapClient().queryForObject("getCount");
		} catch (Exception e) {
			System.out.println(e);
		}
		return answer; 
	}//
	public CustUserDto getCustUser(String id) {
		System.out.println("--------getCustUser------------");
		System.out.println("id= "+id);
		System.out.println("-------------------------------");
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
			System.out.println("--------addCustUser------------");
			System.out.println("id= "+uDto.getId());
			System.out.println("name= "+uDto.getName());
			System.out.println("address= "+uDto.getAddress());
			System.out.println("-------------------------------");
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
