package com.lzt.service;

import com.lzt.entity.WebDevelop;
import com.lzt.exception.LztException;

import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/30
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public interface WebDevelopService {


    public void saveDev(WebDevelop webDevelop) throws LztException;

    public Map<String,Object> getPageDevlop(Integer start,Integer pageSize);

    public void updateDev(String id) throws LztException;

    public List<WebDevelop> getDevList();
}
