package com.sjli.spring.usedb.A1_useJDBC.DAO;

import com.sjli.spring.usedb.A1_useJDBC.service.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 * @Classname UserDao
 * @Description TODO
 * @Date 2021/8/21 10:36
 * @Created by steven
 */
public class UserDao extends AbstractDao {
    public User getByID(long id){
        return getJdbcTemplate().queryForObject(
                "select * from users where id = ?",
                new BeanPropertyRowMapper<>(User.class),
                id
        );
    }
}
