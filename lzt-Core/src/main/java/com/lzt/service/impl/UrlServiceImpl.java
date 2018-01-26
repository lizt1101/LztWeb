package com.lzt.service.impl;

import com.lzt.dao.UrlDao;
import com.lzt.entity.Url;
import com.lzt.exception.LztException;
import com.lzt.service.UrlService;
import com.lzt.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("urlService")
public class UrlServiceImpl implements UrlService{

    @Autowired
    private UrlDao urlDao;

    @Override
    public Map<String, Object> getPageUrlList(Integer start, Integer pageSize) {
        return urlDao.getPageUrlList(start,pageSize);
    }


    @Override
    public void deleteUrl(Integer id) throws LztException {
        Integer i = urlDao.deleteUrlById(id);
        if(i<1 || i==null){
            throw new LztException(MessageVo.ERROR,"删除链接失败!");
        }
    }

    @Override
    public void saveUrl(Url url) {
        urlDao.save(url);
    }

    @Override
    public List<Url> getUrlList() {
        return urlDao.getUrlList();
    }
}
