package com.lzt.solrEntity;

import org.apache.solr.client.solrj.beans.Field;

public class ArticleResult {
	@Field("id")
	private String id;
	@Field
	private String my_title;
	@Field
	private String my_content;     
	@Field
	private String create_time;
	@Field
	private String update_time;
	@Field
	private String sign;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMy_title() {
		return my_title;
	}
	public void setMy_title(String my_title) {
		this.my_title = my_title;
	}
	public String getMy_content() {
		return my_content;
	}
	public void setMy_content(String my_content) {
		this.my_content = my_content;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
