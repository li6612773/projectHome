package com.sjli.spring.AOP.service;

import com.sjli.spring.AOP.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MailService {
    @Autowired(required = false)
    private ZoneId zoneId = ZoneId.systemDefault();
    @PostConstruct
    public void init(){
        System.out.println("Init mail service with zoneId= " + this.zoneId);
    }

    @PreDestroy
    public void shutdown(){
        System.out.println("Shutdown mail service");
    }
    /*
    Spring容器会对上述Bean做如下初始化流程：

调用构造方法创建MailService实例；
根据@Autowired进行注入；
调用标记有@PostConstruct的init()方法进行初始化。
而销毁时，容器会首先调用标记有@PreDestroy的shutdown()方法。

Spring只根据Annotation查找无参数方法，对方法名不作要求。
     */


    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public String getTime() {
        return ZonedDateTime.now(this.zoneId).format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    public void sendLoginMail(User user) {
        System.err.println(String.format("Hi, %s! You are logged in at %s", user.getName(), getTime()));
    }

    public void sendRegistrationMail(User user) {
        System.err.println(String.format("Welcome, %s!", user.getName()));

    }
}




