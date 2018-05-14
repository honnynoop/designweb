package com.jungbo.servlet.centric.help;

import java.util.HashMap;
import java.util.Iterator;

public class ActionMapping{

	private HashMap<String, String> actionMap=
		new HashMap<String, String>(5,5);
	private String contextPath;
	
	public ActionMapping(String contextPath) {
		actionMap.clear();//내용 모두제거
		this.contextPath=contextPath;
	}
	public ActionMapping() {
		this(".");
	}
	
	public void set(String key, String url){
		if(! actionMap.containsKey(key)){
			actionMap.put(key, contextPath+url);
		}
	}//
	public String getKey(String key){
		if(actionMap.containsKey(key)){
			return actionMap.get(key);
		}else{
			return contextPath;
		}
	}//
	private String maps(){
		StringBuffer sb=new StringBuffer();
		Iterator<String> keys=actionMap.keySet().iterator();
		sb.append("<table border='1'>");
		while(keys.hasNext()){
			String key=keys.next();
			String url=actionMap.get(key);
			sb.append("<tr bgcolor='#0000ff'>");
			sb.append("<td bgcolor='#00ffff'>");
			sb.append(key);
			sb.append("</td>");
			sb.append("<td bgcolor='#aaccff'>");
			sb.append(url);
			sb.append("</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		//sb.append("<a href='./custusercontrol.jsp?command=list'>목록보기</a>");
		//sb.append("<a href='./index.jsp'>index.jsp</a>");
		return sb.toString();
	}
	@Override
	public String toString() {
		return maps();
	}
	public int size(){
		return actionMap.size();
	}
}//
