package com.lzt.vo;

import java.sql.Timestamp;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/5
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public class ArtVo {

    private Integer id;
    private String title;
    private String content;
    private String userName;
    private String typeName;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String updateBy;
    private String bContent;

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getBcontent() {
        return bContent;
    }

    public void setBcontent(String bContent) {
        this.bContent = bContent;
    }
}
