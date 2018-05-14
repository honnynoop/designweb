package com.jungbo.paging;

public class PageUtil {
	public static String concat(String urls,String str){
		String s="";
		if(urls.indexOf("?")>0){
			s=urls.concat("&"+str);
		}else{
			s=urls.concat("?"+str);
		}
		return s;
	}//
	
	public static String notNull(String s){
		if(s==null){return "";}
		else{return s.trim();}
	}//
	public static int toInt(String s){
		int temp=0;
		if(!notNull(s).equals("")){
			try{
				temp=Integer.parseInt(s);
			}catch(Exception ee){}
		}
		return temp;
	}
	public static boolean isNull(String str){
		return str==null || str.trim().equals("");
	}
}
