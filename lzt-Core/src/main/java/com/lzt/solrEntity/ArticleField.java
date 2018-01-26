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
	@Field("update_time")
	private String updateTime;
	@Field("sign")
	private String sign;
	
	public ArticleField() {
	}


	public ArticleField(Integer id, String title, String content, String createTime,String updateTime,String sign) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.sign = sign;
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
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
