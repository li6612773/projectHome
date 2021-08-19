package com.sjli.spring.IOC.A4_useResource.service;


/*
在Java程序中，我们经常会读取配置文件、资源文件等。使用Spring容器时，我们也可以把“文件”注入进来，
方便程序读取。

例如，AppService需要读取logo.txt这个文件，通常情况下，我们需要写很多繁琐的代码，
主要是为了定位文件，打开InputStream。

Spring提供了一个org.springframework.core.io.Resource
（注意不是javax.annotation.Resource），它可以像String、int一样使用@Value注入：
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component
public class AppService {

    @Value("classpath:/logo.txt")
    private Resource resource;

    private String logo;

    public String getLogo() {
        return logo;
    }



    @PostConstruct
    public void init() throws IOException {
        try(var reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))){
            this.logo = reader.lines().collect(Collectors.joining("\n"));
        }
    }
    /*
    注入Resource最常用的方式是通过classpath，即类似classpath:/logo.txt表示在classpath中搜索logo.txt文件，然后，我们直接调用Resource.getInputStream()就可以获取到输入流，避免了自己搜索文件的代码。

也可以直接指定文件的路径，例如：

@Value("file:/path/to/logo.txt")
private Resource resource;
但使用classpath是最简单的方式。上述工程结构如下：

spring-ioc-resource
├── pom.xml
└── src
    └── main
        ├── java
        │   └── com
        │       └── itranswarp
        │           └── learnjava
        │               ├── AppConfig.java
        │               └── AppService.java
        └── resources
            └── logo.txt
使用Maven的标准目录结构，所有资源文件放入src/main/resources即可。
     */
}
