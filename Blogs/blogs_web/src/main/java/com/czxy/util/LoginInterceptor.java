package com.czxy.util;

import com.czxy.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: 传智新星
 * @Date: 2019/9/14 22:50
 * @Description:
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取浏览器访问的路径
        String uri = request.getRequestURI();
        System.out.println("当前访问的路径:"+uri);
        //获取域中的对象
        User user = (User) request.getSession().getAttribute("user");
        System.out.println("从域中获取到的对象:"+user);

        //判断域中对象是否存在
        if (user != null){

            return true;
        }

        if(uri.contains("login") || uri.contains("css") || uri.contains("font") || uri.contains("ico")
                ||uri.contains("img") || uri.contains("js")||uri.contains("lay")||uri.contains("register.html")||uri.contains("getLogin")){
            return true;
        }

        response.sendRedirect("login.html");
        return false;
    }
}