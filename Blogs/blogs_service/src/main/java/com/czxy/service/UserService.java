package com.czxy.service;

import com.czxy.domain.User;

import java.util.Date;

/**
 * @Auther: 传智新星
 * @Date: 2019/9/10 15:35
 * @Description:
 */
public interface UserService {


    public User checkUser(User user);


    public boolean checkUsername(String username);

    public void getRegister(User user);

    public void updateInfo(Integer uid, String userflname, String email, String img, String password, String nicklname, Date time);


}