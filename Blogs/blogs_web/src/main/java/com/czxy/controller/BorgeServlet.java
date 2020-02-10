package com.czxy.controller;

import com.czxy.domain.Borge;
import com.czxy.domain.Borgekey;
import com.czxy.domain.User;
import com.czxy.service.BorgeService;
import com.czxy.service.BorgekeyService;
import com.czxy.service.DiscussService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: 传智新星
 * @Date: 2019/9/12 19:31
 * @Description:
 */
@Controller
public class BorgeServlet {

    @Resource
    private BorgeService borgeService;

    @Resource
    private BorgekeyService borgekeyService;

    @Resource
    private DiscussService discussService;

    /**
     * 获取到所有的博客
     * @return
     */
    @GetMapping("findAllBorge")
    public ResponseEntity<List<Borge>> findAllBorge(){


        try {
            List<Borge> allBorge = borgeService.findAllBorge();
            System.out.println("集合:"+allBorge);
            return new ResponseEntity<>(allBorge, HttpStatus.OK);


        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    /**
     * 获取所有的博客标签
     */
@GetMapping("findAllBorkey")
    public ResponseEntity<List<Borgekey>> findAllBorkey(){

        try {
            List<Borgekey> all = borgekeyService.findAll();
            return new ResponseEntity<>(all,HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    /**
     * 获取热门博客(浏览量前四位)
     * @return
     */
    @GetMapping("findHot")
    public ResponseEntity<List<Borge>> findAll(){

        try {
            List<Borge> allHot = borgeService.findAllHot();

            return new ResponseEntity<>(allHot,HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }


    /**
     * 在博客详情页面添加评论
     * @param request
     * @param todiscussid
     * @param content
     * @return
     */
    @GetMapping("addDiscuss")
    public ResponseEntity<Void> addDiscuss(HttpServletRequest request, String todiscussid, String content){


        //从session域中获取当前登录的对象
        User user = (User) request.getSession().getAttribute("user");

        try {
            discussService.addDiscuss(user.getUserid(),content,Integer.parseInt(todiscussid));

            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }

}