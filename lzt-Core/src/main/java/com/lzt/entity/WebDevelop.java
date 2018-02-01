package com.lzt.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/30
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Entity
@Table(name="lzt_web_develop")
public class WebDevelop {

    private Integer id;
    private String webTitle;
    private String webContent;
    private Date webCreateDate;
    private Date webUpdateDate;
    private String webStatus;


    @Id
    @GeneratedValue
    @Column(name="id",unique=true,nullable=false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="web_title")
    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    @Column(name="web_content")
    public String getWebContent() {
        return webContent;
    }

    public void setWebContent(String webContent) {
        this.webContent = webContent;
    }

    @Column(name="web_create_date")
    public Date getWebCreateDate() {
        return webCreateDate;
    }

    public void setWebCreateDate(Date webCreateDate) {
        this.webCreateDate = webCreateDate;
    }

    @Column(name="web_update_date")
    public Date getWebUpdateDate() {
        return webUpdateDate;
    }

    public void setWebUpdateDate(Date webUpdateDate) {
        this.webUpdateDate = webUpdateDate;
    }

    @Column(name="web_status")
    public String getWebStatus() {
        return webStatus;
    }

    public void setWebStatus(String webStatus) {
        this.webStatus = webStatus;
    }
}
