package com.lzt.entity;

import javax.persistence.*;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/25
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Entity
@Table(name="lzt_url")
public class Url {

    private Integer id;
    private String urlName;
    private String url;

    @Id
    @GeneratedValue
    @Column(name="id",unique=true,nullable=false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="name")
    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
