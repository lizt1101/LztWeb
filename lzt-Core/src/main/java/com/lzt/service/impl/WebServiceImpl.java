package com.lzt.service.impl;

import com.lzt.dao.WebDao;
import com.lzt.entity.WebContent;
import com.lzt.exception.LztException;
import com.lzt.service.WebService;
import com.lzt.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/9
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Service("webService")
public class WebServiceImpl implements WebService {

    @Autowired
    private WebDao webDao;

    @Override
    public WebContent getWebConttent(Integer id) {
        return webDao.get(id);
    }

    @Override
    public void updateContent(WebContent webContent) throws LztException {
        Integer i = webDao.updateContent(webContent);
        if(i<1){
            throw new LztException(MessageVo.PASSWORD_ERROR,"修改本站介绍失败!");
        }
    }
}
