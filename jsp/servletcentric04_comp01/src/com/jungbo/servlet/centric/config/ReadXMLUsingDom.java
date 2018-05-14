package com.jungbo.servlet.centric.config;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
public class ReadXMLUsingDom {
	private HashMap<String,String> resourceMap;
	public ReadXMLUsingDom() {
		  resourceMap = new HashMap<String,String>();
	}
	public HashMap<String,String> setResourceMap(InputStream is) 
	throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		Document dom=new Document();
		dom=builder.build(is);
		Element root=dom.getRootElement();//resouces
		makeMapping(root);
	    return resourceMap;
	}
	private  void makeMapping( Element root ) {
	       List resource = root.getChildren("resource");
	       for( Iterator<Element> i=resource.iterator(); i.hasNext(); ) {
	         Element book = i.next();//resource
	         String key=book.getChildText("key");//key
	         String value=book.getChildText("value");//value
	         resourceMap.put(key, value);
	       }
	}
}//
