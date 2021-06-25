package com.sjli.spring.IOC.customBean.service;

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


可选注入
默认情况下，当我们标记了一个@Autowired后，Spring如果没有找到对应类型的Bean，它会抛出NoSuchBeanDefinitionException异常。

可以给@Autowired增加一个required = false的参数：

@Component
public class MailService {
    @Autowired(required = false)
    ZoneId zoneId = ZoneId.systemDefault();
    ...
}

创建第三方Bean
如果一个Bean不在我们自己的package管理之内，例如ZoneId，如何创建它？

答案是我们自己在@Configuration类中编写一个Java方法创建并返回它，注意给方法标记一个@Bean注解：

@Configuration
@ComponentScan
public class AppConfig {
    // 创建一个Bean:
    @Bean
    ZoneId createZoneId() {
        return ZoneId.of("Z");
    }
}
Spring对标记为@Bean的方法只调用一次，因此返回的Bean仍然是单例。
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






