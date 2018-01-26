package com.lzt.service.impl;

import com.lzt.dao.LeaveMessageDao;
import com.lzt.entity.LeaveMessage;
import com.lzt.exception.LztException;
import com.lzt.service.LeaveMessageService;
import com.lzt.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/6
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Service("leaveMessageService")
public class LeaveMessageServiceImpl implements LeaveMessageService {


    @Autowired
    private LeaveMessageDao leaveMessageDao;

    @Override
    public void save(LeaveMessage leaveMessage) throws LztException {
        Integer i = leaveMessageDao.getNowCount(leaveMessage);
        if(i>2){
            throw new LztException(MessageVo.PASSWORD_ERROR,"一天只能留言三条");
        }
        leaveMessage.setCreateDate(new Date());
        leaveMessage.setIsShow("0");
        leaveMessage.setIsDelete("0");
        leaveMessage.setUpdateDate(new Date());
        leaveMessageDao.save(leaveMessage);
    }

    @Override
    public Map<String, Object> getLeaveMessageList(Integer start, Integer pageSize,String type) {
        return leaveMessageDao.getLeaveMessageList(start,pageSize,type);
    }

    @Override
    public void deleteMessage(String ids) throws LztException {
        String[] idss = ids.split(",");
        List<Integer> integerList = new ArrayList<Integer>();
        for (String s:idss) {
            integerList.add(Integer.parseInt(s));
        }
        Integer i = leaveMessageDao.deleteLeaveMessage(integerList);
        if(i<1 || i==null){
            throw new LztException(MessageVo.PASSWORD_ERROR,"删除留言成功!");
        }
    }

    @Override
    public void updateMessage(String id, String isShow) throws LztException {
        Integer i = leaveMessageDao.updateLeaveMessage(Integer.parseInt(id),isShow);
        if(i<1 || i==null){
            throw new LztException(MessageVo.PASSWORD_ERROR,"更新失败!");
        }
    }

    @Override
    public Map<String, Object> getLeaveMessageShowList(Integer start, Integer pageSize) {
        return leaveMessageDao.getLeaveMessageList(start,pageSize,"1");
    }
}
