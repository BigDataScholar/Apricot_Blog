package com.czxy.service.impl;

import com.czxy.dao.TimeLineMapper;
import com.czxy.domain.TimeLine;
import com.czxy.service.TimeLineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: 传智新星
 * @Date: 2019/9/14 20:02
 * @Description:
 */
@Service
@Transactional   //开启事务
public class TimeLineServicelmpl implements TimeLineService {

    @Resource
    private TimeLineMapper timeLineMapper;


    @Override
    public List<TimeLine> findAll(Integer uid) {

        List<TimeLine> byUid = timeLineMapper.findByUid(uid);

        return byUid;
    }

    @Override
    public void deleteTimeLine(Integer tid) {

        timeLineMapper.deleteByPrimaryKey(tid);

    }
}