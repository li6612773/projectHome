package com.sjli.collection;

/**
 * @Classname Student
 * @Description TODO
 * @Date 2021/8/21 17:19
 * @Created by steven
 */

public class Student extends Person {
    public int score;
    // 不要重复name和age字段/方法,
    // 只需要定义新增score字段/方法:

    public Student(String name, int age,int score) {
        //super
        //super关键字表示父类（超类）。子类引用父类的字段时，可以用super.fieldName
        //实际上，这里使用super.name，或者this.name，或者name，效果都是一样的。编译器会自动定位到父类的name字段.
        super(name,age);
        this.score = score;

    }

    public Student(String name, int score) {
        super(name,0);
        this.score = score;
    }

    public int getScore() {
        return  this.score ;
    }
    public void setScore(int score) {
        this.score = score;
    }
}
