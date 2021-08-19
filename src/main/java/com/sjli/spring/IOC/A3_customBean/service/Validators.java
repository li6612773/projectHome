package com.sjli.spring.IOC.A3_customBean.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 我们通过一个Validators作为入口进行验证：
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






