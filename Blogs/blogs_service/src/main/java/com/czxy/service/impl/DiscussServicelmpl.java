package com.czxy.service.impl;

import com.czxy.dao.DiscussMapper;
import com.czxy.domain.Discuss;
import com.czxy.service.DiscussService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Auther: 传智新星
 * @Date: 2019/9/13 09:41
 * @Description:
 */
@Service
@Transactional
public class DiscussServicelmpl implements DiscussService {

    @Resource
    private DiscussMapper discussMapper;


    @Override
    public void addDiscuss(Integer uid, String dismsg, Integer todiscussid) {

        //新建一个评论对象discuss
        Discuss discuss = new Discuss();
        discuss.setDiscussmsg(dismsg);
        discuss.setDiscusstime(new Date());
        discuss.setTodiscussid(todiscussid);
        discuss.setUpuserid(0);
        discuss.setDiscusslevel(1);
        discuss.setUserid(uid);

        //调用工程的方法,添加对象
        discussMapper.insert(discuss);

    }
}