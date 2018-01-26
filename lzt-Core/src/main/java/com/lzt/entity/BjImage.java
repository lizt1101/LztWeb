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
    private Date createDate;
    private Date updateDate;
    private String isDelete;
    private String isPu;

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
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    @Column(name="is_pu")
    public String getIsPu() {
        return isPu;
    }

    public void setIsPu(String isPu) {
        this.isPu = isPu;
    }

    @Override
    public String toString() {
        return "BjImage{" +
                "id=" + id +
                ", bjName='" + bjName + '\'' +
                ", bjUrl='" + bjUrl + '\'' +
                ", sort=" + sort +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", isDelete='" + isDelete + '\'' +
                ", isPu='" + isPu + '\'' +
                '}';
    }
}
