package com.sjli.collection;

import java.util.Objects;

/**
 * @Classname Person
 * @Description TODO
 * @Date 2021/8/21 17:00
 * @Created by steven
 */
public class Person {
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (o instanceof Person) {
            Person p = (Person) o;
            return Objects.equals(this.name, p.name) && this.age == p.age;
        }
        return false;
    }
}
