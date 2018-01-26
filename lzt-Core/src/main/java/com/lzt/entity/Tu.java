package com.lzt.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/22
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Entity
@Table(name="lzt_tu")
public class Tu {

    private Integer id;
    private String tuName;
    private String tuType;
    private String tuUrl;
    private String tuStatus;
    private Date tuCreate;
    private Date tuUpdate;
    private Integer tuUpdateBy;
    private Integer tuCreateBy;

    @Id
    @GeneratedValue
    @Column(name="id",unique=true,nullable=false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="tu_name")
    public String getTuName() {
        return tuName;
    }

    public void setTuName(String tuName) {
        this.tuName = tuName;
    }

    @Column(name="tu_type")
    public String getTuType() {
        return tuType;
    }

    public void setTuType(String tuType) {
        this.tuType = tuType;
    }

    @Column(name="tu_url")
    public String getTuUrl() {
        return tuUrl;
    }

    public void setTuUrl(String tuUrl) {
        this.tuUrl = tuUrl;
    }

    @Column(name="tu_status")
    public String getTuStatus() {
        return tuStatus;
    }

    public void setTuStatus(String tuStatus) {
        this.tuStatus = tuStatus;
    }

    @Column(name="tu_create")
    public Date getTuCreate() {
        return tuCreate;
    }

    public void setTuCreate(Date tuCreate) {
        this.tuCreate = tuCreate;
    }

    @Column(name="tu_update")
    public Date getTuUpdate() {
        return tuUpdate;
    }

    public void setTuUpdate(Date tuUpdate) {
        this.tuUpdate = tuUpdate;
    }

    @Column(name="tu_updateBy")
    public Integer getTuUpdateBy() {
        return tuUpdateBy;
    }

    public void setTuUpdateBy(Integer tuUpdateBy) {
        this.tuUpdateBy = tuUpdateBy;
    }

    @Column(name="tu_createBy")
    public Integer getTuCreateBy() {
        return tuCreateBy;
    }

    public void setTuCreateBy(Integer tuCreateBy) {
        this.tuCreateBy = tuCreateBy;
    }
}
