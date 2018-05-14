package com.jungbo.paging;

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
	    System.out.println("-----------------------");
		System.out.println("startPage :"+startPage);
		System.out.println("startBlock :"+startBlock);
		System.out.println("countPerBlock :"+countPerBlock);
		System.out.println("totalBlock :"+totalBlock);
		System.out.println("curBlockPage :"+curBlockPage);
		System.out.println("curendBlock :"+curendBlock);
		System.out.println("lastBlockPage :"+lastBlockPage);
		System.out.println("lastBlockStartPage :"+lastBlockStartPage);
		try {
			out.println("<center>");
			String aaa="";
			if(startBlock-1>0 && lastBlockPage >= curendBlock ){
				StringBuffer tt=new StringBuffer();
				tt.append("<a href='"+PageUtil.concat(urls, "startPage=%d&startBlock=%d")+"'>");
				tt.append("<img src='./image/left.gif' width='14' height='14' alt='[ù��]' />");
				tt.append("</a>");
				aaa=String.format(tt.toString(),(1), (1));
			}
			out.println(aaa);
			String yyy="";
			if(startBlock-1>0){
				StringBuffer tt=new StringBuffer();
				tt.append("<a href='"+PageUtil.concat(urls, "startPage=%d&startBlock=%d")+"'>");
				tt.append("<img src='./image/prec.gif' width='14' height='14' alt='[�ձ�]' />");
				tt.append("</a>");
				yyy=String.format(tt.toString(),lastBlockStartPage,(startBlock-1));
			}
			out.println(yyy);
			for(int i=curBlockPage;i<=curendBlock;i++){
				//---���� �������� ���� �� �� ���̱�---
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
				tt.append("<img src='./image/next.gif' width='14' height='14' alt='[�ޱ�]'/>");
				tt.append("</a>");
				ttt=String.format(tt.toString(),(curendBlock+1),(startBlock+1));
			}
			out.println(ttt);
			String bbb="";
			if(startBlock-1>=0 && lastBlockPage > curendBlock){
				StringBuffer tt=new StringBuffer();
				tt.append("<a href='"+PageUtil.concat(urls, "startPage=%d&startBlock=%d")+"'>");
				tt.append("<img src='./image/last.gif' width='14' height='14' alt='[��������]' />");
				tt.append("</a>");
				bbb=String.format(tt.toString(),((totalBlock-1)*countPerBlock+1),(totalBlock));
			}
			out.println(bbb);
			out.println("</center>");
		 } catch (IOException e) {
				System.out.println("TagLib���� ���� �߻�");
		 }
	   return SKIP_BODY;
	}//
	
}
