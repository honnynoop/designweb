package com.jungbo.paging;
public class PageBean {
	//	------------�� ��Ͽ��� �߻�
	int startPage=1;     //���� ������
	int curPage=1;       //���� ������
	int curendPage=0;    //������������ ��
	int countPerPage=1;  //�������� �� ��
	int totalCount=0;    //��ü �ۼ�
	int totalPage=0;     //��ü �������� = ��ü �ۼ�/��ϴ� ������+??
	//	------------���� ����̶��
	int startBlock=1;     //���� ���
	int countPerBlock=2;
	int totalBlock=0;
	int curBlockPage=0;       //���� ���
	int curendBlock=0;
	int lastBlockPage=0;
	int lastBlockStartPage=0;
	
	public int getLastBlockStartPage() {
		//2010.6.4 ������ ���� ����
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
        //�߸��� �䱸�� �´ٸ�
		if(startPage<1 || startPage>totalPage ){
			startBlock=1;
			startPage=1;
		}
		if(startBlock<1 || startBlock>totalBlock ){
			startBlock=1;
			startPage=1;
		}//-------��ü �������� ������ ��� �������� �ٽ� �����Ѵ�.
		curPage=this.getCurPage();
		curendPage=this.getCurendPage();
		curBlockPage=this.getCurBlockPage();
		lastBlockPage=this.getLastBlockPage();
		curendBlock=this.getCurendBlock();
		//2010.6.7 �߰�
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