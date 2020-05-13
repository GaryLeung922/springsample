package com.xiaojiaqi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: liangjiaqi
 * @Date: 2020/3/26 12:50 PM
 */
@Entity
@Data
@Table(name = "spitter")
public class Spitter {
    @Id
    @GeneratedValue()
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

}
