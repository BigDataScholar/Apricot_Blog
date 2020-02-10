package com.czxy.service;

import com.czxy.domain.Email;
import com.czxy.domain.User;

import java.util.List;

/**
 * @version v 1.0
 * @date 2019/9/13
 */
public interface EmailService {



    public List<Email> findAllEmail(Integer uid,Integer statusCode,String email);


    public List<Integer> findAllEmailsCuont(Integer uid,String email);



    public void addEmails(String email, User user,String content,String choice);

    public void deleteEmails(String []arr,User user);
}
