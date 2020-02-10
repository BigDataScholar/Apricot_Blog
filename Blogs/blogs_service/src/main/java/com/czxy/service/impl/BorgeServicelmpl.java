package com.czxy.service.impl;

import com.czxy.dao.BorgeMapper;
import com.czxy.dao.DiscussMapper;
import com.czxy.domain.Borge;
import com.czxy.service.BorgeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version v 1.0
 * @date 2019/9/12
 */

@Service
@Transactional   //开启事务
public class BorgeServicelmpl implements BorgeService {

    @Resource
    private BorgeMapper borgeMapper;

    @Resource
    private DiscussMapper discussMapper;

    @Override
    public List<Borge> findAllBorge() {

        List<Borge> borges = borgeMapper.selectAll();

        for (Borge borge : borges) {

            borge.setList(discussMapper.findAllDis(borge.getBorgeid()));

        }
        return borges;

    }

    @Override
    public List<Borge> findAllHot() {

        List<Borge> hotBorge = borgeMapper.findHotBorge();

        return hotBorge;
    }


}
