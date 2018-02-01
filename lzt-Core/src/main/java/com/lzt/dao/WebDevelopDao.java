package com.lzt.dao;

import com.lzt.entity.WebDevelop;

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
public interface WebDevelopDao extends AllDao<WebDevelop>{

    public Map<String, Object> getPageDevlop(Integer start, Integer pageSize);

    public Integer deleteDev(Integer id);

    public Integer updateDev(WebDevelop webDevelop);

    public List<WebDevelop> getDevList();
}
