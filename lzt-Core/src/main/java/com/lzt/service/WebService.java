package com.lzt.service;

import com.lzt.entity.WebContent;
import com.lzt.exception.LztException;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/9
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public interface WebService {

    public WebContent getWebConttent(Integer id);

    public void updateContent(WebContent webContent) throws LztException;

}
