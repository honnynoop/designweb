package com.jungbo.paging;
public class PageBean {
	//	------------한 블록에서 발생
	int startPage=1;     //시작 페이지
	int curPage=1;       //현재 페이지
	int curendPage=0;    //현재페이지의 끝
	int countPerPage=1;  //페이지당 글 수
	int totalCount=0;    //전체 글수
	int totalPage=0;     //전체 페이지수 = 전체 글수/블록당 페이지+??
	//	------------여러 블록이라면
	int startBlock=1;     //시작 블록
	int countPerBlock=2;
	int totalBlock=0;
	int curBlockPage=0;       //현재 블록
	int curendBlock=0;
	int lastBlockPage=0;
	int lastBlockStartPage=0;
	
	public int getLastBlockStartPage() {
		//2010.6.4 마지막 오류 수정
		return lastBlockStartPage=(totalBlock-1)*countPerBlock+1;
	}
	public void setLastBlockStartPage(int lastBlockStartPage) {
		this.lastBlockStartPage = lastBlockStartPage;
	}
	public int getCountPerBlock() {
		return countPerBlock;
	}
	public void setCountPerBlock(int countPerBlock) {
		this.countPerBlock = countPerBlock;
	}
	public int getCountPerPage() {
		return countPerPage;
	}
	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}
	public int getCurBlockPage() {
		return curBlockPage=totalBlock==1?1:(startBlock-1)*countPerBlock+1;
	}
	public int getCurendBlock() {
		return curendBlock=(totalBlock!=(int)Math.ceil(curBlockPage*1.0/countPerBlock))?(startBlock*countPerBlock):lastBlockPage ;
	}
	public int getCurendPage() {
		return curendPage=(totalPage!=startPage)?(startPage)*countPerPage:
			(totalCount%countPerPage==0?(startPage)*countPerPage:(curPage-1)+totalCount%countPerPage);
	}
	public int getCurPage() {
		return curPage=(totalPage==1?1:(startPage-1)*countPerPage+1);
	}
	public int getLastBlockPage() {
		return lastBlockPage=(int)Math.ceil(totalCount*1.0/countPerPage);
	}
	public int getStartBlock() {
		return startBlock;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getTotalBlock() {
		return totalBlock=(int)Math.ceil(totalCount*1.0/(countPerPage*countPerBlock));
	}
	public int getTotalCount() {
		return totalCount;
	}
	public int getTotalPage() {
		return totalPage=(int)Math.ceil(totalCount*1.0/countPerPage);
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		totalPage= this.getTotalPage();
		totalBlock=this.getTotalBlock();
        //잘못된 요구가 온다면
		if(startPage<1 || startPage>totalPage ){
			startBlock=1;
			startPage=1;
		}
		if(startBlock<1 || startBlock>totalBlock ){
			startBlock=1;
			startPage=1;
		}//-------전체 페이지가 들어오면 모든 페이지를 다시 세팅한다.
		curPage=this.getCurPage();
		curendPage=this.getCurendPage();
		curBlockPage=this.getCurBlockPage();
		lastBlockPage=this.getLastBlockPage();
		curendBlock=this.getCurendBlock();
		//2010.6.7 추가
		lastBlockStartPage=getLastBlockStartPage();
	}
	public void setStartBlock(int startBlock) {
		if(startBlock<=0){
			startBlock=1;
		}else{
			this.startBlock = startBlock;
		}
	}
	public void setStartPage(int startPage) {
		if(startPage<=0){
			startPage=1;
		}else{
			this.startPage = startPage;
		}
	}
}