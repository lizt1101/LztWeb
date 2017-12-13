package com.lzt.solrEntity;


import org.apache.solr.client.solrj.beans.Field;

public class ArticleField {
	
	@Field("id")
	private Integer id;
	@Field("my_title")
	private String title;
	@Field("my_content")
	private String content;
	@Field("create_time")         
	private String createTime;
	
	public ArticleField() {
	}
	
	public ArticleField(Integer id, String title, String content, String createTime) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	

}
