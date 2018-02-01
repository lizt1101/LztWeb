package com.lzt.vo;

import com.lzt.entity.WebDevelop;

import java.util.List;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/31
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public class WebDevelopListVo {

    private String year;

    private List<WebDevelop> webDevelopList;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<WebDevelop> getWebDevelopList() {
        return webDevelopList;
    }

    public void setWebDevelopList(List<WebDevelop> webDevelopList) {
        this.webDevelopList = webDevelopList;
    }
}
