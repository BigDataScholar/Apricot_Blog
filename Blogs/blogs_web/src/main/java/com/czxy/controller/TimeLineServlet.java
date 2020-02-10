package com.czxy.controller;

import com.czxy.domain.TimeLine;
import com.czxy.domain.User;
import com.czxy.service.TimeLineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: 传智新星
 * @Date: 2019/9/14 20:06
 * @Description:
 */
@RestController
public class TimeLineServlet {

    @Resource
    private TimeLineService timeLineService;


    /**
     * 获取当前用户的所有时间线数据
     * @param request
     * @return
     */
    @GetMapping("findTimeLine")
    public ResponseEntity<List<TimeLine>> findByUid(HttpServletRequest request){


        //从session域中获取user
        User user = (User) request.getSession().getAttribute("user");

        try {
            List<TimeLine> all = timeLineService.findAll(user.getUserid());

            return new ResponseEntity<>(all, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("deleteTimeLine/{timelineid}")
    public ResponseEntity<Void> deletTimeLine(@PathVariable("timelineid") Integer timelineid){



        //调用工程的方法,删除指定id的时间线
        try {
            timeLineService.deleteTimeLine(timelineid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }
}