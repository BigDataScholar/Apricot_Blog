package com.czxy.service;

import com.czxy.domain.Borge;

import java.util.List;

/**
 * @version v 1.0
 * @date 2019/9/12
 */
public interface BorgeService {


    public List<Borge> findAllBorge();


    public List<Borge> findAllHot();
}
