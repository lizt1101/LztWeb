package com.lzt.service.impl;

import com.lzt.dao.TypeDao;
import com.lzt.entity.Type;
import com.lzt.entity.User;
import com.lzt.exception.LztException;
import com.lzt.service.TypeService;
import com.lzt.vo.MessageVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/8
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Service("typeService")
public class TypeServiceImpl implements TypeService{

    @Autowired
    private TypeDao typeDao;

    @Override
    public List<Type> getTypeList() {
        return typeDao.getTypeList();
    }

    @Override
    public Map<String,Object> getPageTypeList(Integer page, Integer rows) {
        return typeDao.getPageTypeList(page,rows);
    }

    @Override
    public void saveOrUpdate(Type type) throws LztException {
        Date date = new Date();
        if(type.getId() == null){
            type.setCreateDate(date);
            type.setIsDelete("0");
        }
        type.setUpdateDate(date);
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("CurrentUser");
        type.setCreateBy(user.getId());
        Integer i = typeDao.saveOrUpdate(type);
        if(i==null || i<1){
           throw new LztException(MessageVo.ERROR,"保存失败");
        }
    }

    @Override
    public void deleteType(Integer id) throws LztException {
        Integer i = typeDao.deletetype(id);
        if(i<1){
            throw new LztException(MessageVo.ERROR,"删除失败");
        }
    }

    @Override
    public Type getType(Integer id) {
        return typeDao.getType(id);
    }
}
