package com.xiaojiaqi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Author: liangjiaqi
 * @Date: 2020/3/29 1:26 PM
 */

@Repository
public class SpitterDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(){
        String sql = "INSERT INTO spitter (username,password,fullname,email) VALUES \n" +
                "(?,?,?,?)\n" +
                ";";

        jdbcTemplate.update(sql,"leung","362203","liangjiaqi","3124354675uhgfds@");
        System.out.println("ewarsfdgc");

    }
}
