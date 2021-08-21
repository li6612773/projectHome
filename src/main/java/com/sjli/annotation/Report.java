package com.sjli.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname Report
 * @Description TODO
 * @Date 2021/8/21 15:09
 * @Created by steven
 */

//Java语言使用@interface语法来定义注解（Annotation）
//注解的参数类似无参数方法，可以用default设定一个默认值（强烈推荐）。最常用的参数应当命名为value。

//定义注解@Report可用在方法上，我们必须添加一个@Target(ElementType.METHOD)：
@Target({
        ElementType.METHOD,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface Report {
    int type() default 0;
    String level() default "info";
    String value() default "";
}
