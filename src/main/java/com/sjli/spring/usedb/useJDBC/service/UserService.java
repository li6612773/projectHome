package com.sjli.spring.usedb.useJDBC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @Classname UserService
 * @Description TODO
 * @Date 2021/8/11 17:42
 * @Created by steven
 */


@Component
//表示在抛出RuntimeException或IOException时，事务将回滚。
@Transactional(rollbackFor = {RuntimeException.class, IOException.class})
public class UserService {

    @Autowired
    BonusService bonusService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public User register(String email,String password,String name){
        // 插入用户记录:
//        User user = jdbcTemplate.execute("insert into");
        // 增加100积分:

        User user = null;
        bonusService.addBonus(user.id, 100);
        return null;
    }


}
