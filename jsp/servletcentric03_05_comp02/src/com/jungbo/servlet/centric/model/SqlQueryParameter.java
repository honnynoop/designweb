package com.jungbo.servlet.centric.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
/**
 * PreparedStatement의 ?(퀘스쳔마크)에 원하는 값을 정상적으로 
 * 넣었는지 확인하기 위한 쿼리 문자열 
 * 보통 PreparedStatement의 Wrapper 클래스를 만들어 사용한다.
 * 이 클래스는 기본 적으로 많이 사용하는 것만 만들었다.
 *@author hyonny
 *@since 2009. 10. 24
 */
public class SqlQueryParameter {
	private ArrayList<String> queryQuestionValue;
	private ArrayList<String> queryBatch;
    private String sql;
    protected PreparedStatement psmt;
	
    
	public SqlQueryParameter(PreparedStatement psmt, String sql) {
		  queryQuestionValue = new ArrayList<String>();
		  queryBatch = new ArrayList<String>();
		  this.psmt=psmt;
		  this.sql=sql;
	}
	/**
	 * <pre>
	 * ?를 찾아서 원하는 값으로 변화시킨다.
	 * String sql="INSERT INTO CUSTUSER VALUES(?,?,?)";일 때
	 * INSERT INTO CUSTUSER VALUES(
	 * ,
	 * ,
	 * )로 분해
	 * ------------------------------------------------------
	 * INSERT INTO CUSTUSER VALUES( 
	 * 4>1 -> value=get(1) 'id'
	 * INSERT INTO CUSTUSER VALUES( 'id'
	 * ,
	 * 4>2 -> value=get(2) 'name'
	 * INSERT INTO CUSTUSER VALUES( 'id','name'
	 * ,
	 * 4>3 -> value=get(3) 'address'
	 * INSERT INTO CUSTUSER VALUES( 'id','name','address'
	 * )
	 * -------------------------------------------------------
	 * </pre>
	 * @return ?대신 입력된 데이터로 변환한 쿼리문장
	 */
	public   String getQueryString() {
        StringBuffer sb = new StringBuffer();
        int questionMarks = 0;
        
        StringTokenizer stringToken = new StringTokenizer(sql + " ", "?");
        while (stringToken.hasMoreTokens()) {
            String qToken = stringToken.nextToken();
            sb.append(qToken);
            try {
                Object value=null;
                if (queryQuestionValue.size() > 1 + questionMarks) {
                    value = queryQuestionValue.get(1 + questionMarks++);
                }
                else {
                    if (stringToken.hasMoreTokens()) {
                        value = null;
                    }
                    else {
                        value = "";
                    }
                }
                sb.append("" + value);
            }
            catch (Exception e) {
               
            }
        }
        return sb.toString().trim();
    }
	/**
	 * <pre>
	 * SqlQueryParameter qstmt=new SqlQueryParameter(psmt, sql);
	 * qstmt.setString(1, id.trim());  //1>=0  이므로 0번째 null 1 번째 'id'
	 * qstmt.setString(2,name.trim()); //2>=2 이므로 2번째 null 그리고 2번째 'name' set 으로 대입
	 * qstmt.setString(3,address.trim()); //3>=3 이므로 3번째 null 그리고 3번째 'address' set 으로 대입
	 * 즉 (null, 'id', 'name', 'address')를 넣는다.
	 * </pre>
	 * @param index setXXX(index, 내용)형식으로 JDBC의 스테이트 문장의 ?의 위치
	 * @param obj 입력할 내용
	 */
	 private void setQuestionMark(int index, Object obj) {
	        String str="";
	        if (obj instanceof String || obj instanceof Date) {
	        	str = "'" + obj + "'"; //DB에서 문자일 때는  's001'처럼 ' '을 붙인다.
	        }
	        else {
	            if (obj == null) {
	            	str = "   ";
	            }
	            else {
	            	str = obj.toString();
	            }
	        }
	        
	        while (index >= queryQuestionValue.size()) {
	            queryQuestionValue.add(null);
	        }
	        queryQuestionValue.set(index, str);
	    }
	 public   String getBatchString() {
		 StringBuffer sb=new StringBuffer();
		 for (String ss : queryBatch) {
			sb.append(ss+"\n");
		}
		 return sb.toString();
	 }//
	 public void setString(int index, String strs) throws SQLException{
		 psmt.setString(index, strs);
		 setQuestionMark(index,strs);
	 }
	 public void setInt(int index, int strs) throws SQLException{
		 psmt.setInt(index, strs);
		 setQuestionMark(index,strs);
	 }
	 public void setDate(int index, java.sql.Date d) throws SQLException{
		 psmt.setDate(index, d);
		 setQuestionMark(index,d);
	 }
	 public void setDouble(int index, double d) throws SQLException{
		 psmt.setDouble(index, d);
		 setQuestionMark(index,d);
	 }
	 public void addBatch() throws SQLException{
		 psmt.addBatch();
		 queryBatch.add(getQueryString());
		 queryQuestionValue.clear();
	 }
	 public ResultSet executeQuery() throws SQLException{
		 return psmt.executeQuery();
	 }//
	 
	 public int executeUpdate() throws SQLException{
		 return psmt.executeUpdate();
	 }//
	 public int[] executeBatch() throws SQLException{
		 return psmt.executeBatch();
	 }//
}//
