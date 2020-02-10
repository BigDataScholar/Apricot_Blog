package com.czxy.dao;

import com.czxy.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;

/**
 * @Auther: 传智新星
 * @Date: 2019/9/10 15:36
 * @Description:
 */
@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {



    @Update("update l_user set userflname=#{userflname},useremail=#{email},usernickname=#{nicklname},userpassword=#{password},userphoto=#{img},userlp=#{userlp} where userid=#{id} ")
    public void updates(@Param("userflname") String userflname, @Param("email") String email, @Param("nicklname") String nicklname, @Param("password") String password, @Param("img") String img, @Param("id") Integer id,@Param("userlp") Date time);


    /**
     * 根据评论查询用户实体类
     */
    @Select("select * from l_user where userid =#{id}")
    public User findUserByDisid(@Param("id") Integer id);

}