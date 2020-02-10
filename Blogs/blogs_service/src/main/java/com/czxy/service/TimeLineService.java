package com.czxy.service;

import com.czxy.domain.TimeLine;

import java.util.List;

/**
 * @version v 1.0
 * @date 2019/9/14
 */
public interface TimeLineService {



    public List<TimeLine> findAll(Integer uid);


    public void deleteTimeLine(Integer tid);
}
