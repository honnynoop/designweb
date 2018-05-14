package com.jungbo.ibatis;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibatis.common.resources.Resources;

import java.io.Reader;
import java.io.IOException;

public abstract class SqlDaoSuport{

	private SqlMapClient sqlMapper;
	
	public SqlDaoSuport(String path){
	    try {
	        Reader reader = Resources.getResourceAsReader(path);
	        sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
	        reader.close(); 
	      } catch (IOException e) {
	        throw new RuntimeException("Check the location of SqlMapClient." , e);
	      }
	}
	public SqlDaoSuport(){
	    this("com/jungbo/ibatis/config/SqlMapConfig.xml");
	}
	public SqlMapClient getSqlMapper() {
		return sqlMapper;
	}
}
