package com.hk.mobile.help;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
public class PagingBeanTag extends TagSupport{
	
	private static final long serialVersionUID = 1L;
	private PageBean pbean;
	public void setPbean(PageBean pbean) {
		this.pbean = pbean;
	}
	private String urls;
	
	public void setUrls(String urls) {
		this.urls = urls;
	}
	 public int doStartTag(){
		JspWriter out=pageContext.getOut();
		int startPage=pbean.getStartPage();
		int startBlock=pbean.getStartBlock();     
		int countPerBlock=pbean.getCountPerBlock();
		int totalBlock=pbean.getTotalBlock();
		int curBlockPage=pbean.getCurBlockPage();
		int curendBlock=pbean.getCurendBlock();
		int lastBlockPage=pbean.getLastBlockPage();
	    int lastBlockStartPage=pbean.getLastBlockStartPage();

	    //--버그 수정 전체개수가 0이면 버그 발생 수정완료
		int total=pbean.getTotalCount();
		if(total==0){
			curBlockPage=1;
			curendBlock=0;
		}
		try {
			out.println("<center>");
			String aaa="";
			if(startBlock-1>0 && lastBlockPage >= curendBlock && startBlock-1!=1){
				StringBuffer tt=new StringBuffer();
				tt.append("<a href='"+PageUtil.concat(urls, "startPage=%d&startBlock=%d")+"'>");
				tt.append("<img src='../image/left.gif' width='14' height='14' alt='[첫글]' />");
				tt.append("</a>");
				aaa=String.format(tt.toString(),(1), (1));
			}
			out.println(aaa);
			String yyy="";
			if(startBlock-1>0){
				StringBuffer tt=new StringBuffer();
				tt.append("<a href='"+PageUtil.concat(urls, "startPage=%d&startBlock=%d")+"'>");
				tt.append("<img src='../image/prec.gif' width='14' height='14' alt='[앞글]' />");
				tt.append("</a>");
				//debug 2010.6.7
				yyy=String.format(tt.toString(),curBlockPage-countPerBlock,(startBlock-1));
			}
			out.println(yyy);
			for(int i=curBlockPage;i<=curendBlock;i++){
				//---현재 페이지에 따른 글 들 보이기---
				StringBuffer sb=new StringBuffer();
				sb.append("<a href='"+PageUtil.concat(urls, "startPage=%d&startBlock=%d")+"'>");
				if(i==curendBlock){
					sb.append(" "+(i)+" ");
				}else{
					sb.append(" "+(i)+"| ");
				}
				sb.append("</a>");
				String sss=String.format(sb.toString(),(i),startBlock);
				out.println(sss);
			}
			String ttt="";
			if(lastBlockPage>=curendBlock+1){
				StringBuffer tt=new StringBuffer();
				tt.append("<a href='"+PageUtil.concat(urls, "startPage=%d&startBlock=%d")+"'>");
				tt.append("<img src='../image/next.gif' width='14' height='14' alt='[뒷글]'/>");
				tt.append("</a>");
				ttt=String.format(tt.toString(),(curendBlock+1),(startBlock+1));
			}
			out.println(ttt);
			String bbb="";
			if(startBlock-1>=0 && lastBlockPage > curendBlock && totalBlock!=startBlock+1){
				StringBuffer tt=new StringBuffer();
				tt.append("<a href='"+PageUtil.concat(urls, "startPage=%d&startBlock=%d")+"'>");
				tt.append("<img src='./image/last.gif' width='14' height='14' alt='[마지막글]' />");
				tt.append("</a>");
				//변경
				bbb=String.format(tt.toString(),(lastBlockStartPage),(totalBlock));
			}
			out.println(bbb);
			out.println("</center>");
		 } catch (IOException e) {
				System.out.println("TagLib에서 문제 발생");
		 }
	   return SKIP_BODY;
	}//
	
}
