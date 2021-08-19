package com.sjli.spring.usedb.useJDBC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Classname UserService
 * @Description TODO
 * @Date 2021/8/11 17:42
 * @Created by steven
 */
public class UserService {
    @Autowired
    JdbcTemplate jdbcTemplate;
}
