package com.lzt.service.impl;

import com.lzt.dao.TuDao;
import com.lzt.entity.Tu;
import com.lzt.entity.User;
import com.lzt.exception.LztException;
import com.lzt.service.TuService;
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
 * @Create 2018/1/22
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Service("tuService")
public class TuServiceImpl implements TuService{

    @Autowired
    private TuDao tuDao;

    @Override
    public void saveTu(Tu tu) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("CurrentUser");
        tuDao.updateStatus(tu.getTuType());
        Date date = new Date();
        tu.setTuCreate(date);
        tu.setTuUpdate(date);
        //tu.setTuCreateBy();
        tu.setTuCreateBy(user.getId());
        tu.setTuUpdateBy(user.getId());
        tu.setTuStatus("0");
        tuDao.save(tu);
    }

    @Override
    public Map<String, Object> getQtList(Integer page, Integer rows, String status,String type) {
        return tuDao.getQtList(page,rows,status,type);
    }

    @Override
    public void updateQtStatus(String id, String status,String type) throws LztException {
        tuDao.updateStatus(type);
        Integer i = tuDao.updateQtStatus(id,status);
        if(i==null ||i<1){
            throw new LztException(MessageVo.ERROR,"更新失败");
        }
    }

    @Override
    public List<Tu> getQtList() {
        return tuDao.getQtList();
    }
}
