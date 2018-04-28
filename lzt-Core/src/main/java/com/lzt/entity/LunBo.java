package com.lzt.entity;

import javax.persistence.*;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/19
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Entity
@Table(name="lzt_lunbo")
public class LunBo {

    private Integer id;
    private String lbName;
    private String lbType;
    private String lbTu;
    private Integer lbAid;
    private String lbUrl;
    private String lbStatus;
    private Integer lbSort;

    @Id
    @GeneratedValue
    @Column(name="id",unique=true,nullable=false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="lb_name")
    public String getLbName() {
        return lbName;
    }

    public void setLbName(String lbName) {
        this.lbName = lbName;
    }

    @Column(name="lb_type")
    public String getLbType() {
        return lbType;
    }

    public void setLbType(String lbType) {
        this.lbType = lbType;
    }

    @Column(name="lb_tu")
    public String getLbTu() {
        return lbTu;
    }

    public void setLbTu(String lbTu) {
        this.lbTu = lbTu;
    }

    @Column(name="lb_Aid")
    public Integer getLbAid() {
        return lbAid;
    }

    public void setLbAid(Integer lbAid) {
        this.lbAid = lbAid;
    }

    @Column(name="lb_url")
    public String getLbUrl() {
        return lbUrl;
    }

    public void setLbUrl(String lbUrl) {
        this.lbUrl = lbUrl;
    }

    @Column(name="lb_status")
    public String getLbStatus() {
        return lbStatus;
    }

    public void setLbStatus(String lbStatus) {
        this.lbStatus = lbStatus;
    }

    @Column(name="lb_sort")
    public Integer getLbSort() {
        return lbSort;
    }

    public void setLbSort(Integer lbSort) {
        this.lbSort = lbSort;
    }


}
