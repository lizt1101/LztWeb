package com.lzt.service;

import com.lzt.entity.Url;
import com.lzt.exception.LztException;

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
public interface UrlService {

    public Map<String,Object> getPageUrlList(Integer start,Integer pageSize);

    public void deleteUrl(Integer id) throws LztException;

    public void saveUrl(Url url);

    public List<Url> getUrlList();
}
