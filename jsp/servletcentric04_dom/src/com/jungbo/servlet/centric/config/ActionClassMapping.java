package com.jungbo.servlet.centric.config;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.ServletContext;
import com.jungbo.servlet.centric.control.Action;

public class ActionClassMapping {

	private HashMap<String,Action> resourceMap;
	private HashMap<String,String> actionMap;
	private javax.servlet.ServletContext context;
	public ActionClassMapping(ServletContext context) {
		this.context=context;
		resourceMap = new HashMap<String,Action>();
	}
	
	public void setMapping(String path){
		InputStream instream = context.getResourceAsStream(path);
		//ReadXML rxml = new ReadXML();
		ReadXMLUsingDom rxml=new ReadXMLUsingDom();
		try {
			actionMap = rxml.setResourceMap(instream);
			makeActionClass(actionMap);
		} catch (IOException e) {
			
		} catch (Exception e) {
			
		} 
	}
	public Action get(String key){
		if(resourceMap.containsKey(key)){
			return resourceMap.get(key);
		}else{
			return null;
		}
	}
	public String maps(){
		StringBuffer sb=new StringBuffer();
		Iterator<String> keys=actionMap.keySet().iterator();
		sb.append("<table border='1'>");
		while(keys.hasNext()){
			String key=keys.next();
			String classes=actionMap.get(key);
			sb.append("<tr bgcolor='#0000ff'>");
			sb.append("<td bgcolor='#00ffff'>");
			sb.append(key);
			sb.append("</td>");
			sb.append("<td bgcolor='#aaccff'>");
			sb.append(classes);
			sb.append("</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		sb.append("<a href='./index.jsp'>index.jsp</a>");
		return sb.toString();
	}
	private void makeActionClass(HashMap<String,String> actMap){
		for(String key : actMap.keySet()){
			resourceMap.put(key.trim(), getAct(actMap.get(key)));
		}
	}
	private Action getAct(String className){
		return (Action)ActionClassReflection.createObject(className.trim());
	}
}
