package com.czxy.service.impl;

import com.czxy.dao.EmailMapper;
import com.czxy.dao.TimeLineMapper;
import com.czxy.domain.Email;
import com.czxy.domain.TimeLine;
import com.czxy.domain.User;
import com.czxy.service.EmailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: 传智新星
 * @Date: 2019/9/13 20:17
 * @Description:
 */
@Service
@Transactional   // 开启事务
public class EmailServicelmpl implements EmailService {

    @Resource
    private EmailMapper emailMapper;

    @Resource
    private TimeLineMapper timeLineMapper;

    @Override
    public List<Email> findAllEmail(Integer uid, Integer statusCode,String email) {

        List<Email> list = new ArrayList<>();
        System.out.println("statusCode:"+statusCode);

        //根据不同的状态码我们调用不同的展示方法
        if (statusCode==0){
            // 收件箱
            list = emailMapper.findReceiveEmail(uid,email);
        }else if (statusCode==1){
            // 草稿箱
            list = emailMapper.findDraftEmail(uid,email);
        }else if (statusCode==2){
            // 发件箱
            list = emailMapper.findSendEmail(uid,email);
        }else {
            // 垃圾箱
            list = emailMapper.findDelectEmail(uid,email);
        }

        return list;
    }


    //获取不同的邮件的个数,装进一个集合返回
    @Override
    public List<Integer> findAllEmailsCuont(Integer uid,String email) {

        //新建一个集合,用来储存不同邮件的个数
        List<Integer> list = new ArrayList<>();

        //收件箱的个数
        Integer receiveCount = emailMapper.findReceiveCount(uid, email);

        //草稿箱的个数
        Integer drafCount = emailMapper.findDrafCount(uid, email);

        //发件箱的个数
        Integer sendCount = emailMapper.findSendCount(uid, email);

        //垃圾箱的个数
        Integer delectCount = emailMapper.findDelectCount(uid, email);

        //把获取到的结果存放在集合中,再将结果返回
        list.add(receiveCount);
        list.add(drafCount);
        list.add(sendCount);
        list.add(delectCount);

        return list;
    }

    //添加邮件
    @Override
    public void addEmails(String email, User user,String content,String choice) {

           //choice为1 代表发送邮件，choice为2 代表保存至草稿箱
        if (choice.equals("1")){

            //新建邮件对象---收件人
            Email email1 = new Email();
            email1.setReceiveemailcontent(content);
            email1.setReceiveemaildate(new Date());
            email1.setReceiveemailstatus(0);
            email1.setReceiveuserid(user.getUserid());
            email1.setReceiveusermail(email);

            //调用工程的方法将其添加进数据库
            emailMapper.insert(email1);

            //再新建一个邮件对象--发件人
            Email email2 = new Email();
            email2.setReceiveemailcontent(content);
            email2.setReceiveemaildate(new Date());
            email2.setReceiveemailstatus(2);
            email2.setReceiveuserid(user.getUserid());
            email2.setReceiveusermail(user.getUseremail());
            emailMapper.insert(email2);

            //往时间线中添加一条数据--把邮件保存至草稿箱
            TimeLine timeLine = new TimeLine();

            timeLine.setInfo("You send an email!!");
            timeLine.setUserIid(user.getUserid());
            timeLine.setTimeline(new Date());
            //添加至数据库
            timeLineMapper.insert(timeLine);


        }else{

            //新建邮件对象
            Email emails = new Email();

            emails.setReceiveemailcontent(content);
            emails.setReceiveemaildate(new Date());
            emails.setReceiveemailstatus(1);
            emails.setReceiveuserid(user.getUserid());
            emails.setReceiveusermail(user.getUseremail());
            emailMapper.insert(emails);


            //往时间线中添加一条数据--把邮件保存至草稿箱
            TimeLine timeLine = new TimeLine();

            timeLine.setInfo("You saved a letter in the draft box!");
            timeLine.setUserIid(user.getUserid());
            timeLine.setTimeline(new Date());
            //添加至数据库
            timeLineMapper.insert(timeLine);

        }

    }

    //删除邮件
    @Override
    public void deleteEmails(String[] arr,User user) {

        for (String s : arr) {

            //假删除【把需要删除的邮件存放至垃圾箱】

            //先根据id获取到需要删除的邮件对象的实体类
            Email email = emailMapper.selectByPrimaryKey(Integer.parseInt(s));
            System.out.println("删除的email对象:"+email);
            email.setReceiveemailstatus(3);
            //把旧记录删除
            emailMapper.deleteByPrimaryKey(Integer.parseInt(s));
            //把新纪录添至数据库
            emailMapper.insert(email);
        }

        //往时间线中添加一条数据--删除邮件
        TimeLine timeLine = new TimeLine();
        timeLine.setInfo("You deleted "+arr.length+" emails!");
        timeLine.setUserIid(user.getUserid());
        timeLine.setTimeline(new Date());
        //添加至数据库
        timeLineMapper.insert(timeLine);


        System.out.println("删除成功!");
    }
}