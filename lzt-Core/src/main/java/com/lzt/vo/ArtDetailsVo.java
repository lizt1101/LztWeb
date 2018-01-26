package com.lzt.vo;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/11
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public class ArtDetailsVo {

    private Integer id;
    private String title;
    private Integer reads;
    private List<String> keys;
    private String content;
    private Timestamp createTime;
    private String typeName;
    private Integer pings;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public Integer getReads() {
        return reads;
    }

    public void setReads(Integer reads) {
        this.reads = reads;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getPings() {
        return pings;
    }

    public void setPings(Integer pings) {
        this.pings = pings;
    }
}
