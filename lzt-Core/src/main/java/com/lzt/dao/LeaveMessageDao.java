package com.lzt.dao;

import com.lzt.entity.LeaveMessage;

import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/22
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public interface LeaveMessageDao extends AllDao<LeaveMessage> {


    public Integer getNowCount(LeaveMessage leaveMessage);

    public Map<String,Object> getLeaveMessageList(Integer start, Integer pageSize,String type);

    public Integer deleteLeaveMessage(List<Integer> integerList);

    public Integer updateLeaveMessage(Integer id,String isShow);

}
