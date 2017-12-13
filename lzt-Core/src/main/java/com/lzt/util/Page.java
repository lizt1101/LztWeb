package com.lzt.util;

public class Page {

	private Long pageNum;

	private Long allCount;

	private Long totalPage;

	private Long pageSize;
	
	public Page(Long pageNum, Long pageSize, Long allCount){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.allCount = allCount;
        this.totalPage = (allCount!=0)?((allCount+pageSize-1)/pageSize):1;
    }

	public Long getPageNum() {
		return pageNum;
	}

	public void setPageNum(Long pageNum) {
		this.pageNum = pageNum;
	}

	public Long getAllCount() {
		return allCount;
	}

	public void setAllCount(Long allCount) {
		this.allCount = allCount;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
	

}
