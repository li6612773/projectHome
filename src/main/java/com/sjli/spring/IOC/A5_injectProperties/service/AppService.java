package com.sjli.spring.IOC.A5_injectProperties.service;

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

    public String getLogo() {
        return logo;
    }

    private String logo;

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
