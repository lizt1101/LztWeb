package com.lzt.dao;

import com.lzt.entity.Url;

import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/25
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public interface UrlDao extends AllDao<Url>{

    public Map<String, Object> getPageUrlList(Integer start, Integer pageSize);

    public Integer deleteUrlById(Integer id);

    public List<Url> getUrlList();


}
