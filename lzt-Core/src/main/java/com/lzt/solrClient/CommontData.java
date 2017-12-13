package com.lzt.solrClient;

import java.util.List;
import java.util.Map;

import com.lzt.util.Page;

public class CommontData<T> {
	
	private Page page;
	private List<T> dataList;
	private Map<String,Map<String,List<String>>> highlightings;
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	public Map<String, Map<String, List<String>>> getHighlightings() {
		return highlightings;
	}
	public void setHighlightings(Map<String, Map<String, List<String>>> highlightings) {
		this.highlightings = highlightings;
	}
	
	

}
