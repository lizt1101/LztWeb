package com.lzt.entity;

import javax.persistence.*;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/9
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Entity
@Table(name="lzt_common")
public class WebContent {
    private Integer id;
    private String webContent;

    @Id
    @GeneratedValue
    @Column(name="id",unique=true,nullable=false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="web_content")
    public String getWebContent() {
        return webContent;
    }

    public void setWebContent(String webContent) {
        this.webContent = webContent;
    }
}
