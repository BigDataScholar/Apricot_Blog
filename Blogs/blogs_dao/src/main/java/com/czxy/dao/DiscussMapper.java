package com.czxy.dao;

import com.czxy.domain.Discuss;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @version v 1.0
 * @date 2019/9/13
 */


@org.apache.ibatis.annotations.Mapper
public interface DiscussMapper extends Mapper<Discuss> {


    @Select("select * from l_discuss where todiscussid = #{id}")
    @Results({

            @Result(property = "user",one = @One(select = "com.czxy.dao.UserMapper.findUserByDisid"),column = "userid")
    })
    public List<Discuss> findAllDis(@Param("id") Integer id);

}
