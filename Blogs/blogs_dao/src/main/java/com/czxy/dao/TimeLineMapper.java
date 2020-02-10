package com.czxy.dao;

import com.czxy.domain.TimeLine;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @version v 1.0
 * @date 2019/9/14
 */

@org.apache.ibatis.annotations.Mapper
public interface TimeLineMapper extends Mapper<TimeLine> {


    @Select("select * from i_timeline where userIid =#{uid}")
    public List<TimeLine> findByUid(@Param("uid") Integer uid);

}
