package com.sjli.spring.usedb.A1_useJDBC.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.annotation.PostConstruct;

/**
 * @Classname AbstractDao
 * @Description TODO
 * @Date 2021/8/21 10:38
 * @Created by steven
 */
public abstract class AbstractDao  extends JdbcDaoSupport {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        super.setJdbcTemplate(jdbcTemplate);
    }
}
