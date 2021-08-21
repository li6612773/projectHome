package com.sjli.spring.usedb.A2_mybatis.mapper;


import com.sjli.spring.usedb.A1_useJDBC.service.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname UserMapper
 * @Description TODO
 * @Date 2021/8/21 11:06
 * @Created by steven
 */
public interface UserMapper {

    @Select("SELECT * FROM users WHERE id = #{id}")
    User getById(@Param("id") long id);

    @Select("SELECT * FROM users LIMIT #{offset}, #{maxResults}")
    List<User> getAll(@Param("offset") int offset, @Param("maxResults") int maxResults);

    @Insert("INSERT INTO users (email, password, name, createdAt) VALUES (#{user.email}, " +
            "#{user.password}, #{user.name}, #{user.createdAt})")
    void insert(@Param("user") User user);

    //如果users表的id是自增主键，那么，我们在SQL中不传入id，但希望获取插入后的主键，需要再加一个@Options注解：
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO users (email, password, name, createdAt) VALUES (#{user.email}, " +
            "#{user.password}, #{user.name}, #{user.createdAt})")
    void insertWithId(@Param("user") User user);

    @Update("UPDATE users SET name = #{user.name}, createdAt = #{user.createdAt} WHERE id = #{user.id}")
    void update(@Param("user") User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteById(@Param("id") long id);
}
