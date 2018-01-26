package com.lzt.dao;

import com.lzt.entity.WebContent;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/22
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public interface WebDao extends AllDao<WebContent> {

    public Integer updateContent(WebContent webContent);

}
