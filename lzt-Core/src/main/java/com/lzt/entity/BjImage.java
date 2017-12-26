package com.lzt.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/19
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Entity
@Table(name="lzt_bj_image")
public class BjImage {

    private Integer id;
    private String bjName;
    private String bjUrl;
    private Integer sort;
    private Date creteDate;
    private Date updateDate;
    private String isDelete;

    @Id
    @GeneratedValue
    @Column(name="id",unique=true,nullable=false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="bj_name")
    public String getBjName() {
        return bjName;
    }

    public void setBjName(String bjName) {
        this.bjName = bjName;
    }

    @Column(name="url")
    public String getBjUrl() {
        return bjUrl;
    }

    public void setBjUrl(String bjUrl) {
        this.bjUrl = bjUrl;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Column(name="create_date")
    public Date getCreteDate() {
        return creteDate;
    }

    public void setCreteDate(Date creteDate) {
        this.creteDate = creteDate;
    }

    @Column(name="update_date")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Column(name="is_delete")
    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
}
