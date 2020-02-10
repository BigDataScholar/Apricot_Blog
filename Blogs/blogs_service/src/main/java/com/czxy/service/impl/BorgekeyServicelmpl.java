package com.czxy.service.impl;

import com.czxy.dao.BorgekeyMapper;
import com.czxy.domain.Borgekey;
import com.czxy.service.BorgekeyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: 传智新星
 * @Date: 2019/9/12 21:11
 * @Description:
 */
@Service
@Transactional   // 开启事务
public class BorgekeyServicelmpl implements BorgekeyService {

      @Resource
    private BorgekeyMapper borgekeyMapper;



     //获取所有的博客
    @Override
    public List<Borgekey> findAll() {

        List<Borgekey> borgekeys = borgekeyMapper.selectAll();


        return borgekeys;
    }
}