package com.jungbo.servlet.centric.config;
import com.jungbo.servlet.centric.control.CustErrorAction;
public class ActionClassReflection {
	public static Object createObject(String className) {
	    Object object = null;
		try {
			Class makeClass = Class.forName(className);
			object = makeClass.newInstance();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (InstantiationException e) {
			System.out.println(e);
		} catch (IllegalAccessException e) {
			System.out.println(e);
		}
		if(object==null){
			object=new CustErrorAction();
		}
	      return object;
	   }
}//
