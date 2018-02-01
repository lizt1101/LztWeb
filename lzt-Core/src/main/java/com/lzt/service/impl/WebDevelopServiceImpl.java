package com.lzt.service.impl;

import com.lzt.dao.WebDevelopDao;
import com.lzt.entity.WebDevelop;
import com.lzt.exception.LztException;
import com.lzt.service.WebDevelopService;
import com.lzt.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
@Service("webDevelopService")
public class WebDevelopServiceImpl implements WebDevelopService{

    @Autowired
    private WebDevelopDao webDevelopDao;

    @Override
    public void saveDev(WebDevelop webDevelop) throws LztException {
        if(webDevelop.getId()!=null){
           Integer i = webDevelopDao.updateDev(webDevelop);
           if(i<1 || i==null){
               throw new LztException(MessageVo.ERROR,"发表失败!");
           }
        }else{
            Date date = new Date();
            webDevelop.setWebCreateDate(date);
            webDevelop.setWebUpdateDate(date);
            webDevelop.setWebStatus("0");
            WebDevelop webDevelop1 = webDevelopDao.save(webDevelop);
            if(webDevelop1==null){
                throw new LztException(MessageVo.ERROR,"发表失败!");
            }
        }

    }

    @Override
    public Map<String, Object> getPageDevlop(Integer start, Integer pageSize) {
        return webDevelopDao.getPageDevlop(start,pageSize);
    }

    @Override
    public void updateDev(String id) throws LztException {
        Integer i = webDevelopDao.deleteDev(Integer.parseInt(id));
        if(i<1 || i==null){
            throw new LztException(MessageVo.ERROR,"删除失败!");
        }
    }

    @Override
    public List<WebDevelop> getDevList() {
        return webDevelopDao.getDevList();
    }
}









