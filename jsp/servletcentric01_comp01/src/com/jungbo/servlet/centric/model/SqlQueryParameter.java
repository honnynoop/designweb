package com.jungbo.servlet.centric.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
/**
 * PreparedStatement�� ?(�����Ÿ�ũ)�� ���ϴ� ���� ���������� 
 * �־����� Ȯ���ϱ� ���� ���� ���ڿ� 
 * ���� PreparedStatement�� Wrapper Ŭ������ ����� ����Ѵ�.
 * �� Ŭ������ �⺻ ������ ���� ����ϴ� �͸� �������.
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
	 * ?�� ã�Ƽ� ���ϴ� ������ ��ȭ��Ų��.
	 * String sql="INSERT INTO CUSTUSER VALUES(?,?,?)";�� ��
	 * INSERT INTO CUSTUSER VALUES(
	 * ,
	 * ,
	 * )�� ����
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
	 * @return ?��� �Էµ� �����ͷ� ��ȯ�� ��������
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
	 * qstmt.setString(1, id.trim());  //1>=0  �̹Ƿ� 0��° null 1 ��° 'id'
	 * qstmt.setString(2,name.trim()); //2>=2 �̹Ƿ� 2��° null �׸��� 2��° 'name' set ���� ����
	 * qstmt.setString(3,address.trim()); //3>=3 �̹Ƿ� 3��° null �׸��� 3��° 'address' set ���� ����
	 * �� (null, 'id', 'name', 'address')�� �ִ´�.
	 * </pre>
	 * @param index setXXX(index, ����)�������� JDBC�� ������Ʈ ������ ?�� ��ġ
	 * @param obj �Է��� ����
	 */
	 private void setQuestionMark(int index, Object obj) {
	        String str="";
	        if (obj instanceof String || obj instanceof Date) {
	        	str = "'" + obj + "'"; //DB���� ������ ����  's001'ó�� ' '�� ���δ�.
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
