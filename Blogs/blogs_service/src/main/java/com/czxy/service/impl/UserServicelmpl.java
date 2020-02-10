package com.czxy.service.impl;

import com.czxy.dao.TimeLineMapper;
import com.czxy.dao.UserMapper;
import com.czxy.domain.User;
import com.czxy.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Auther: 传智新星
 * @Date: 2019/9/10 15:36
 * @Description:
 */
@Service
@Transactional
public class UserServicelmpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private TimeLineMapper timeLineMapper;

    @Override
    public User checkUser(User user) {

        List<User> userList = userMapper.selectAll();

        for (User user1 : userList) {

            if (user1.getUsername().equals(user.getUsername())&&user1.getUserpassword().equals(user.getUserpassword())){

                return user1;

            }
        }

        //若没有与登录对象用户名密码相同的则直接返回null
        return null;

    }

    @Override
    public boolean checkUsername(String username) {

        List<User> userList = userMapper.selectAll();

        //定义一个变量,保存最后的判断结果
        boolean flag =true;
        for (User user : userList) {

            if (user.getUsername().equals(username)){
                flag = false;
                break;
            }
        }

        //返回最后的判断结果
        return flag;

    }

    @Override
    public void getRegister(User user) {

        //给用户注册的时间赋值
        user.setUserregister(new Date());

        user.setUserap(new Date());
        user.setUserlp(new Date());

        userMapper.insert(user);



    }

    @Override
    public void updateInfo(Integer uid, String userflname, String email, String img, String password,String nicklname, Date time) {


        userMapper.updates(userflname,email,nicklname,password,img,uid,time);

    }

}