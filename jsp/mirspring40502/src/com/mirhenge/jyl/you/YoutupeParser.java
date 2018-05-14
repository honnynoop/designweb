package com.mirhenge.jyl.you;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import java.net.MalformedURLException;

import java.net.URL;

import java.net.URLDecoder;

import java.net.URLEncoder;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

 
@Component
public class YoutupeParser{
	 String urls = "https://www.youtube.com/results?search_query=";
	 ArrayList<String> htmls = new ArrayList<>();
	 
//	public  ArrayList<String> searchimg(String s){
//		ArrayList<String> htmls = new ArrayList<>();
//		htmls.clear();
//		int i=0;
//		BufferedReader br = null;
//		try {
//			String ss=URLEncoder.encode(s, "utf-8");
//			URL url = new URL(urls+ss);
//			br = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
//			String msg = "";
//			while ((msg = br.readLine()) != null) {// EOF				
//				if(msg.trim().contains("yt-thumb video-thumb")){
//					String dd=br.readLine();
//					if(dd.trim().contains("data-thumb")){
//						int l=dd.indexOf("data-thumb");
//						String rythms=dd.substring(l);
//						String[] musics=rythms.trim().split("\"");
//						htmls.add(musics[1]);
//					}else{
//						int l=dd.indexOf("src=");	
//						String sibal=dd.substring(l);
//						String[] fuck=sibal.trim().split("\"");
//						htmls.add(fuck[1]);
//					}
//					i++;
//				}
//			}
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e){
//			e.printStackTrace();
//		}
//		return htmls;
//	}
	public  ArrayList<String> search(String s)
	{
		htmls.clear();
		BufferedReader br = null;
		try 
		{
			String ss = URLEncoder.encode(s, "utf-8");
			System.out.println(ss);
			URL url = new URL(urls + ss);
			br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
			String msg = "";
			while ((msg = br.readLine()) != null){// EOF
				/*

				 * System.out.println("평문을 EUC-KR로 인코딩 : " + encode); String

				 * decode = URLDecoder.decode(encode, "EUC-KR");

				 * System.out.println("다시 평문으로 디코딩 : " + decode);

				 */

				if (msg.trim().contains("class=\"yt-uix-tile-link yt-ui-ellipsis yt-ui-ellipsis-2 yt-uix-sessionlink")) {
					htmls.add(msg.trim());
				}
			}
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return htmls;
	}
	//2017.4.12 /watch?v=PCaKey131NQ&dafdsfasd에서 & 제거
	public String toUrl(String msg){
		String tt="";
		if(msg.indexOf("&")==-1){//없다면
			tt=msg;
		}else{
			tt=msg.substring(0, msg.indexOf("&"));
		}
		return tt;
	}
	
	// 제목
	public ArrayList<Youtube> getTitles(String key) {
		ArrayList<Youtube> af = new ArrayList<Youtube>();
		int i = 0;
		ArrayList<String> asd = search(key);
		//ArrayList<String> img = searchimg(key);

		for(int j=0; j<asd.size(); j++){
			try{
				String[] fu = asd.get(i).split("\"");
				String url = URLDecoder.decode(fu[5], "EUC-KR");
				String title = URLDecoder.decode(fu[11], "EUC-KR");
				//String ing =img.get(i);
				Youtube f = new Youtube(title,toUrl(url),"");
				//private String title;
				//private String url;
				//private String img;
				af.add(f);
			} 
			catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			i++;
		}
		return af;

	}

    //
//	public ArrayList<Youtube> getSource(String key) {
//		ArrayList<Youtube> af = new ArrayList<Youtube>();
//		int i = 0;
//		ArrayList<String> asd = search(key);
//		ArrayList<String> img = searchimg(key);
//
//
//		for(int j=0; j<asd.size(); j++){
//			try{
//				String[] fu = asd.get(i).split("\"");
//				String ss = URLDecoder.decode(fu[5], "EUC-KR");
//				String s = URLDecoder.decode(fu[11], "EUC-KR");
//				Youtube f = new Youtube(s,ss,img.get(i));
//				af.add(f);
//
//			} 
//
//			catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//			i++;
//		}
//
//		return af;
//	}
}
