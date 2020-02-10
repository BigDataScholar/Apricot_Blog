package com.czxy.controller;

import com.czxy.domain.User;
import com.czxy.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @Auther: 传智新星
 * @Date: 2019/9/10 15:47
 * @Description:
 */
@RestController
public class UserServlet  {


    @Resource
    private UserService userService;


    /**
     * 登录的方法
     * @param user   需要登录的对象
     * @param remember   复选框的状态
     * @param response   浏览器响应对象
     * @return     是否登录成功的结果
     */
    @GetMapping("getLogin")
    public ResponseEntity<Boolean> getLogin(User user, String remember, HttpServletResponse response, HttpServletRequest request){

        User user1 = null;
        try {
            user1 = userService.checkUser(user);
            //调用工程的方法,返回的结果不为null则代表登录成功
            if (user1!=null) {

                //获取当前的ip地址
                InetAddress ip = InetAddress.getLocalHost();
                String s = ip.getHostAddress().toString();

                user1.setUserip(s);
                //登录成功之后把当前用户存放至session域中
                request.getSession().setAttribute("user",user1);

                //登录成功之后还需再判断用户是否点击了记住密码
                //先创建好几个cookie,保存用户的一系列信息
                Cookie cookie1 = new Cookie("username", user1.getUsername());
                Cookie cookie2 = new Cookie("userpassword", user1.getUserpassword());
                Cookie cookie3 = new Cookie("remember", "ok");

                if ("true".equals(remember)){

                    //点击了记住密码,给每个保存信息的cookie设置一周的有效期
                    cookie1.setMaxAge(7*24*60*60);
                    cookie2.setMaxAge(7*24*60*60);
                    cookie3.setMaxAge(7*24*60*60);

                }else {

                    //未点击记住密码就把保存信息的cookie干掉
                    cookie1.setMaxAge(0);
                    cookie2.setMaxAge(0);
                    cookie3.setMaxAge(0);

                }

                //给每个cookie设置作用范围
                cookie1.setPath("/");
                cookie2.setPath("/");
                cookie3.setPath("/");

                //把cookie的结果响应给浏览器
                response.addCookie(cookie1);
                response.addCookie(cookie2);
                response.addCookie(cookie3);



                return new ResponseEntity<>(true,HttpStatus.OK);

            }else{
            //登录失败

                return new ResponseEntity<>(false,HttpStatus.OK);

            }

        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @GetMapping("checkUsername")
    public ResponseEntity<Boolean> checkUsername(String username){

        try {
            boolean flag = userService.checkUsername(username);

            if (flag){
                //用户名未出现重复
                return new ResponseEntity<>(true,HttpStatus.OK);

            }else {
                //用户名出现重复
                return new ResponseEntity<>(false,HttpStatus.OK);

            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    /**
     * 注册的方法
     * @param user
     * @return
     */
    @PostMapping("getRegister/{cid}")
    public ResponseEntity<Void> getRegister(User user, String[] jineng, String[] hobby, @PathVariable String cid, HttpServletRequest request) throws UnknownHostException {

        System.out.println("需要注册的用户:"+user);
        System.out.println("技能:"+jineng);
        System.out.println("爱好:"+hobby);

        //新建一个字符串变量来保存用户的技能和爱好信息
        String jinengs ="";

        for (int i = 0; i < jineng.length; i++) {

            if (i==jineng.length-1){

                jinengs += jineng[i];

            }else{

                jinengs += jineng[i]+",";
            }

        }

        String hobbys = "";
        for (int i = 0; i < hobby.length; i++) {

            if (i==hobby.length-1){
                hobbys+=hobby[i];

            }else{
                hobbys += hobby[i]+",";

            }


        }

        user.setUserskills(jinengs);
        user.setUserhobby(hobbys);


        //调用工具类的方法,获取到当前登录的ip
        //获取当前的ip地址
        InetAddress ip = InetAddress.getLocalHost();
        String s = ip.getHostAddress().toString();

        //设置用户当前的ip
        user.setUserip(s);

        //设置用户的账号类型id
        user.setUsertypeid(Integer.parseInt(cid));

        //调用工程的方法进行注册
        try {
            userService.getRegister(user);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }

    /**
     * 获取到已经登录的用户
     * @param request
     * @return
     */
    @GetMapping("getUser")
    public ResponseEntity<User> getUser(HttpServletRequest request){


        //从session域中获取登录的对象
        try {

            User user = (User) request.getSession().getAttribute("user");
            return new ResponseEntity<>(user,HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    /***
     * 用户注销
     * @param request
     * @return
     */
    @GetMapping("logout")
    public ResponseEntity<Void> Logout(HttpServletRequest request){

        //从session域中删除user
        try {
            request.getSession().removeAttribute("user");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);


        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * 更新用户的信息
     * @return
     */
    @PostMapping("updateUser")
    public ResponseEntity<Void> updateUser(User user, MultipartFile files,HttpServletRequest request) throws IOException {


        try {

            //定义一个变量用来保存文件名
            String filename = files.getOriginalFilename();

            System.out.println("文件的信息:"+filename);

            System.out.println("更新的对象"+user);

            //把文件下载进本地项目
            files.transferTo(new File("G:\\idea arc\\summer\\Blogs\\blogs_web\\src\\main\\resources\\static\\assets\\img\\it\\"+filename));

            //从域中获取到user
            User user1 = (User) request.getSession().getAttribute("user");

            String userimg = "\\assets\\img\\it\\"+filename;

            Date date = new Date();
            userService.updateInfo(user1.getUserid(),user.getUserflname(),user.getUseremail(),userimg,user.getUserpassword(),user.getUsernickname(),date);

            User user2 = userService.checkUser(user1);


            //把更新完成后的对象存放至域中
            request.getSession().setAttribute("user",user2);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }


    /**
     *  锁屏器解锁时的密码校验
     * @param password
     * @param request
     * @return
     */
    @GetMapping("checkPassword")
    public ResponseEntity<Boolean> checkPassword(String pas,HttpServletRequest request){

        //从域中获取到当前登录的对象
        User user = null;
        try {
            user = (User) request.getSession().getAttribute("user");

            if (user.getUserpassword().equals(pas)){
                return new ResponseEntity<>(true,HttpStatus.OK);

            }else {
                return new ResponseEntity<>(false,HttpStatus.OK);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


}