package com.sjli.collection;


import java.util.HashMap;
import java.util.Map;

/**
 * @Classname Map_In_Java
 * @Description TODO
 * @Date 2021/8/21 17:17
 * @Created by steven
 */
public class Map_In_Java {
    public static void main(String[] args) {
        Student student = new Student("Xiao Ming", 99);
        Map<String, Student> map3 = new HashMap<>();
        map3.put("Xiao Ming", student); // 将"Xiao Ming"和Student实例映射并关联
        Student target = map3.get("Xiao Ming"); // 通过key查找并返回映射的Student实例
        System.out.println(target == student); // true，同一个实例
        System.out.println(target.score); // 99
        Student another = map3.get("Bob"); // 通过另一个key查找
        System.out.println(another); // 未找到返回null

        System.out.println("重复放置key：");
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("apple", 123);
        map2.put("pear", 456);
        System.out.println(map2.get("apple")); // 123
        map2.put("apple", 789); // 再次放入apple作为key，但value变为789
        System.out.println(map2.get("apple")); // 789  原来关联的value对象123就被“冲掉”了

        System.out.println("遍历map：");
        Map<String, Integer> map4 = new HashMap<>();
        map4.put("apple", 123);
        map4.put("pear", 456);
        map4.put("banana", 789);
        for (String key : map4.keySet()) {
            Integer value = map4.get(key);
            System.out.println(key + " = " + value);
        }
        for (Map.Entry<String, Integer> entry : map4.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " = " + value);
        }
    }
}
