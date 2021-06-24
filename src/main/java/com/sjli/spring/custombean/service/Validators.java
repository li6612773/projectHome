package com.sjli.spring.custombean.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
/*
注入List
有些时候，我们会有一系列接口相同，不同实现类的Bean。例如，注册用户时，我们要对email、password和name这3个变量进行验证。为了便于扩展，我们先定义验证接口：

public interface Validator {
    void validate(String email, String password, String name);
}
然后，分别使用3个Validator对用户参数进行验证：

@Component
public class EmailValidator implements Validator {
    public void validate(String email, String password, String name) {
        if (!email.matches("^[a-z0-9]+\\@[a-z0-9]+\\.[a-z]{2,10}$")) {
            throw new IllegalArgumentException("invalid email: " + email);
        }
    }
}

@Component
public class PasswordValidator implements Validator {
    public void validate(String email, String password, String name) {
        if (!password.matches("^.{6,20}$")) {
            throw new IllegalArgumentException("invalid password");
        }
    }
}

@Component
public class NameValidator implements Validator {
    public void validate(String email, String password, String name) {
        if (name == null || name.isBlank() || name.length() > 20) {
            throw new IllegalArgumentException("invalid name: " + name);
        }
    }
}
注意到Validators被注入了一个List<Validator>，Spring会自动把所有类型为
Validator的Bean装配为一个List注入进来，这样一来，我们每新增一个Validator类型，
就自动被Spring装配到Validators中了，非常方便。

因为Spring是通过扫描classpath获取到所有的Bean，
而List是有序的，要指定List中Bean的顺序，可以加上@Order注解：
 */



@Component
public class Validators {
    @Autowired
    List<Validator> validators;

    public void setValidators(String email,String password,String name) {
        for (var validator: this.validators){
            validator.validate(email,password,name);
        }
    }
}
