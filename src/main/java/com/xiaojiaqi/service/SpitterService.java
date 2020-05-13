package com.xiaojiaqi.service;

import com.xiaojiaqi.dao.SpitterDao;
import com.xiaojiaqi.entity.Spitter;
import com.xiaojiaqi.repository.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @Author: liangjiaqi
 * @Date: 2020/3/26 1:11 PM
 */
@Service
public class SpitterService {
    @Autowired
    private SpitterDao spitterDao;

    @Autowired
    private SpitterService spitterService;

    @Transactional()
    public void insert() throws Throwable {
        try {
            spitterDao.insert();
            System.out.println("插入完成。");
            throw new Exception();
        }catch (Exception e){
            throw new Throwable();
        }

    }

//    public void insert2(){
//
//        spitterService.insert();
//    }
}
