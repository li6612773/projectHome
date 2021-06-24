package com.sjli.spring.customBean.service;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

/*
使用FactoryBean
我们在设计模式的工厂方法中讲到，很多时候，可以通过工厂模式创建对象。Spring也提供了工厂模式，
允许定义一个工厂，然后由工厂创建真正的Bean。

用工厂模式创建Bean需要实现FactoryBean接口。我们观察下面的代码：

@Component
public class ZoneIdFactoryBean implements FactoryBean<ZoneId> {

    String zone = "Z";

    @Override
    public ZoneId getObject() throws Exception {
        return ZoneId.of(zone);
    }

    @Override
    public Class<?> getObjectType() {
        return ZoneId.class;
    }
}
当一个Bean实现了FactoryBean接口后，Spring会先实例化这个工厂，然后调用getObject()创建真正的Bean。
getObjectType()可以指定创建的Bean的类型，因为指定类型不一定与实际类型一致，可以是接口或抽象类。

因此，如果定义了一个FactoryBean，要注意Spring创建的Bean实际上是这个FactoryBean的getObject()方法
返回的Bean。为了和普通Bean区分，我们通常都以XxxFactoryBean命名。
 */

@Component
public class ZoneIdFactoryBean implements FactoryBean<ZoneId> {
    String zone = "z";

    @Override
    public ZoneId getObject() throws Exception {
        return ZoneId.of(zone);
    }

    //Class<?> 表示这个Class可以放任意的类
    public Class<?> getObjectType() {
        return ZoneId.class;
    }
}
