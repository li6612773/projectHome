package com.sjli.xml_json;

/**
 * @Classname UseJSON
 * @Description TODO
 * @Date 2021/8/26 19:05
 * @Created by steven
 */

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * JSON是JavaScript Object Notation的缩写，
 * 它去除了所有JavaScript执行代码，只保留JavaScript的对象格式。一个典型的JSON如下：
 */
public class JSON_Jackson {
    public static void main(String[] args) throws IOException {
        InputStream input = JSON_Jackson.class.getResourceAsStream("/book.json");
        ObjectMapper mapper = new ObjectMapper();
// 反序列化时忽略不存在的JavaBean属性:
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Book book = mapper.readValue(input, Book.class);
    }
}
