package com.lzt.dto;


public class ArticleDto {
	
	private String title;
	private String content;
	private Integer userId;
	private Integer typeId;
	
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	@Override
	public String toString() {
		return "ArticleDto [title=" + title + ", content=" + content + ", userId=" + userId + ", typeId=" + typeId
				+ "]";
	}
	
	

}
