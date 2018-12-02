package com.dangde.domain.model;

import java.util.List;

/**
 * 
 * 记住用户分页查询 对应页码的对象数据，对象总记录数，当前页码，页码条，总页数，每页显示的条数
 *
 */
public class PageBean {

	private List list;
	private long totalrecord;
	private int pagesize;
	private int totalpage;
	private int currentpage;
	private int[] pagebar;
	
	
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public long getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public void setTotalpage() {
		//100   5   20
		//101   5   21
		//99    5   20
		
		if(this.totalrecord%this.pagesize==0){
			this.totalpage = (int) (this.totalrecord/this.pagesize);
		}else{
			this.totalpage = (int) (this.totalrecord/this.pagesize+1);
		}
		
		
		
	}
	public int getTotalpage() {return totalpage;}
	
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	
	public void setPagebar() {
		int startpage;
		int endpage;
		int pagebar[] = null;
		if(this.totalpage<=10){
			pagebar = new int[this.totalpage];
			startpage = 1;
			endpage = this.totalpage;
		}else{
			pagebar = new int[10];
			startpage = this.currentpage - 4;
			endpage = this.currentpage + 5;
			
			//总页数=30      3    -1
			//总页数=30      29   34   21   30
			if(startpage<1){
				startpage = 1;
				endpage = 10;
			}
			
			if(endpage>this.totalpage){
				endpage = this.totalpage;
				startpage = this.totalpage - 9;
			}
		}
		
		int index = 0;
		for(int i=startpage;i<=endpage;i++){
			pagebar[index++] = i;
		}
		
		this.pagebar = pagebar;
		
	}
	
	public int[] getPagebar() {return pagebar;}
	
}
