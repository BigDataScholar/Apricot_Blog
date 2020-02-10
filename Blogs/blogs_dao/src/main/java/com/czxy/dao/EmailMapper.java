package com.czxy.dao;

import com.czxy.domain.Email;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Auther: 传智新星
 * @Date: 2019/9/13 20:16
 * @Description:
 */
@org.apache.ibatis.annotations.Mapper
public interface EmailMapper extends Mapper<Email> {


    //获取指定uid用户的收件邮箱集合
    @Select("select * from l_emailreceive where receiveuserid != #{id} and receiveemailstatus = 0 and receiveusermail = #{email}")
    @Results({

            @Result(property = "user",one = @One(select = "com.czxy.dao.UserMapper.findUserByDisid"),column = "receiveuserid")
    })
    public List<Email> findReceiveEmail(@Param("id") Integer uid,@Param("email") String email);


    //获取指定uid收件箱的个数
    @Select("select count(*) from l_emailreceive where receiveuserid != #{id} and receiveemailstatus = 0 and receiveusermail = #{email}")
    public Integer findReceiveCount(@Param("id") Integer uid,@Param("email") String email);


    //获取指定uid用户的草稿箱
    @Select("select * from l_emailreceive where receiveuserid = #{id} and receiveemailstatus = 1 and receiveusermail = #{email}" )
    @Results({

            @Result(property = "user",one = @One(select = "com.czxy.dao.UserMapper.findUserByDisid"),column = "receiveuserid")
    })
    public List<Email> findDraftEmail(@Param("id") Integer id,@Param("email") String email);


    //获取指定草稿箱的个数
    @Select("select count(*) from l_emailreceive where receiveuserid = #{id} and receiveemailstatus = 1 and receiveusermail = #{email}" )
    public Integer findDrafCount(@Param("id") Integer uid,@Param("email") String email);


    //获取指定uid用户的发件箱
    @Select("select * from l_emailreceive where receiveuserid = #{id} and receiveemailstatus = 2 and receiveusermail = #{email} " )
    @Results({

            @Result(property = "user",one = @One(select = "com.czxy.dao.UserMapper.findUserByDisid"),column = "receiveuserid")
    })
    public List<Email> findSendEmail(@Param("id") Integer id,@Param("email") String email);


    //获取指定uid发件箱的个数
    @Select("select count(*) from l_emailreceive where receiveuserid = #{id} and receiveemailstatus = 2 and receiveusermail = #{email} " )
    public Integer findSendCount(@Param("id") Integer id,@Param("email") String email);



    //获取指定uid用户的垃圾邮箱
    @Select("select * from l_emailreceive where receiveemailstatus = 3 and receiveusermail = #{email}" )
    @Results({

            @Result(property = "user",one = @One(select = "com.czxy.dao.UserMapper.findUserByDisid"),column = "receiveuserid")
    })
    public List<Email> findDelectEmail(@Param("id") Integer id,@Param("email") String email);


    //获取指定uid垃圾邮箱的个数
    @Select("select count(*) from l_emailreceive where receiveemailstatus = 3 and receiveusermail = #{email}")
    public Integer findDelectCount(@Param("id") Integer id,@Param("email") String email);


}