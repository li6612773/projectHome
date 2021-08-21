package com.sjli.spring.usedb.A2_mybatis.service;

import com.sjli.spring.usedb.A1_useJDBC.service.User;
import com.sjli.spring.usedb.A2_mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Classname UserService
 * @Description TODO
 * @Date 2021/8/21 12:13
 * @Created by steven
 */

@Component
@Transactional
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User getUserById(long id){
        // 调用Mapper方法:
        User user = userMapper.getById(id);
        if (user == null) {
            throw new RuntimeException("User not found by id.");
        }
        return user;
    }
}
