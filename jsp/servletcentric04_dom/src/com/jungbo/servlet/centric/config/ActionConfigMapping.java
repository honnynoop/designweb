package com.jungbo.servlet.centric.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletContext;
import com.jungbo.servlet.centric.help.ActionMapping;

public class ActionConfigMapping {
	private HashMap<String,String> resourceMap;
	private javax.servlet.ServletContext context;
	public ActionConfigMapping(ServletContext context) {
		this.context=context;
		resourceMap = new HashMap<String,String>();
	}
	
	public void setMapping(String path){
		InputStream instream = context.getResourceAsStream(path);
		//ReadXML rxml = new ReadXML();
		ReadXMLUsingDom rxml=new ReadXMLUsingDom();
		try {
			resourceMap = rxml.setResourceMap(instream);
			
		} catch (IOException e) {
			
		} catch (Exception e) {
			
		} 
	}
	public String get(String key){
		if(resourceMap.containsKey(key)){
			return resourceMap.get(key);
		}else{
			return "";
		}
	}
	public String maps(){
		StringBuffer sb=new StringBuffer();
		Iterator<String> keys=resourceMap.keySet().iterator();
		sb.append("<table border='1'>");
		while(keys.hasNext()){
			String key=keys.next();
			String url=get(key);
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
		sb.append("<a href='./index.jsp'>index.jsp</a>");
		return sb.toString();
	}
	public ActionMapping getActionMapping(String contextPath){
		ActionMapping maps=new ActionMapping(contextPath);
		for(String key : resourceMap.keySet()){
			maps.set(key.trim(), get(key.trim()));
		}
		return maps;
	}
}//
