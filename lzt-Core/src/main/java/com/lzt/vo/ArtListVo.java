package com.lzt.vo;

import java.sql.Timestamp;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/29
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public class ArtListVo {

    private Integer id;
    private String title;
    private String content;
    private String userName;
    private Timestamp createTime;
    private String imgUrl;
    private Integer readCount;
    private Integer pingCount;
    private String nickName;

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getPingCount() {
        return pingCount;
    }

    public void setPingCount(Integer pingCount) {
        this.pingCount = pingCount;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
