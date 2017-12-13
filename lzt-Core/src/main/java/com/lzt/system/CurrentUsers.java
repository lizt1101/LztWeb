package com.lzt.system;

import com.lzt.entity.User;
import org.apache.shiro.SecurityUtils;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/11/28
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public class CurrentUsers {

    public static final User SESSIONUSER = (User)SecurityUtils.getSubject().getSession().getAttribute("CurrentUser");


}
