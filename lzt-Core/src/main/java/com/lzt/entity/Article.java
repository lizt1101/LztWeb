package com.lzt.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lzt_article")
public class Article {
	
	private Integer Aid;
	private String title;
	private String content;
	private Integer userId;
	private Integer status;
	private Timestamp createTime;
	private Timestamp updateTime;
	private Integer typeId;
	private String sign;
	private String contentText;

	@Id
	@GeneratedValue
	@Column(name="id",unique=true,nullable=false)
	public Integer getAid() {
		return Aid;
	}
	public void setAid(Integer aid) {
		Aid = aid;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getTypeId() {return typeId;}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}

	@Column(name="content_text")
	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	@Override
	public String toString() {
		return "Article{" +
				"Aid=" + Aid +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", userId=" + userId +
				", status=" + status +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", typeId=" + typeId +
				", sign='" + sign + '\'' +
				", contentText='" + contentText + '\'' +
				'}';
	}
}
