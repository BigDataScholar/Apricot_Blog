package com.czxy.controller;

import com.czxy.domain.Email;
import com.czxy.domain.User;
import com.czxy.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: 传智新星
 * @Date: 2019/9/13 20:13
 * @Description:
 */
@RestController
public class EmailServlet {

    @Resource
    private EmailService emailService;


    /**
     * 展示不同状态下的邮件
     * @param request    浏览器请求对象
     * @param sendemailstatus    邮件的状态   0:收件箱  1:草稿箱   2:发件箱  3:垃圾箱
     * @return
     */
    @GetMapping("findSomeEmails")
    public ResponseEntity<List<Email>> showEmails(HttpServletRequest request,String sendemailstatus){

        //从域中获取到当前登录的用户
        User user = (User) request.getSession().getAttribute("user");

        //根据不同的状态调用工程的方法
        try {
            List<Email> allEmail = emailService.findAllEmail(user.getUserid(), Integer.parseInt(sendemailstatus),user.getUseremail());

            System.out.println("邮件集合:"+allEmail);
            return new ResponseEntity<>(allEmail, HttpStatus.OK);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    /**
     * 获取不同邮件的个数
     * @param request
     * @return
     */
    @GetMapping("findAllCounts")
    public ResponseEntity<List<Integer>> findAllCounts(HttpServletRequest request){

        //从域中获取到user对象
        User user = (User) request.getSession().getAttribute("user");

        //调用工程的方法获取结果的值
        try {
            List<Integer> allEmailsCuont = emailService.findAllEmailsCuont(user.getUserid(), user.getUseremail());
            return new ResponseEntity<>(allEmailsCuont,HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    /**
     * 发送邮件
     * @param request    浏览器请求对象
     * @param receiveusermail   接收到的收件人邮箱
     * @param receiveemailcontent   邮件的具体内容
     * @return
     */
    @GetMapping("sendEmails")
    public ResponseEntity<Void> sendEmail(HttpServletRequest request,String receiveusermail,String receiveemailcontent,String choice){

        System.out.println("当前的选择是:"+choice);

        //从域中获取到当前的对象
        User user = (User) request.getSession().getAttribute("user");

        try {
            //调用工程的方法进行把邮件的信息存入数据库
            emailService.addEmails(receiveusermail,user,receiveemailcontent,choice);

            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }


    /**
     * 删除邮件
     * @param arr    含有需要删除邮件id的数组
     * @return
     */
    @PostMapping("deleteEmails")
    public ResponseEntity<Void> deleteEmails(@RequestParam(value="par") String []arr,HttpServletRequest request){

        System.out.println("需要删除的邮件id数组:"+ Arrays.toString(arr));

        User user = (User) request.getSession().getAttribute("user");


        //调用工程的方法完成删除邮件的操作
        try {
            emailService.deleteEmails(arr,user);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}