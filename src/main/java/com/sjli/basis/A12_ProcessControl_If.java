package com.sjli.basis;

public class A12_ProcessControl_If {
    public static void main(String[] args) {

    //if判断
        //if语句的基本语法是：
        /*
            if (条件) {
                // 条件满足时执行
            }
        */
        int n = 70;
        if (n >= 60) {
            System.out.println("及格了");
            System.out.println("恭喜你");
        }
        System.out.println("END");

        //当if语句块只有一行语句时，可以省略花括号{}：
        if (n >= 60)
            System.out.println("及格了");
        System.out.println("END");

        //带else
        if (n >= 90) {
            System.out.println("优秀");
        } else if (n >= 60) {
            System.out.println("及格了");
        } else {
            System.out.println("挂科了");
        }
        System.out.println("END");

        //在串联使用多个if时，要特别注意判断顺序。观察下面的代码：
        n = 100;
        if (n >= 60) {
            System.out.println("及格了");
        } else if (n >= 90) {
            System.out.println("优秀");
        } else {
            System.out.println("挂科了");
        }
        //执行发现，n = 100时，满足条件n >= 90，但输出的不是"优秀"，而是"及格了"，
        // 原因是if语句从上到下执行时，先判断n >= 60成功后，后续else不再执行，
        // 因此，if (n >= 90)没有机会执行了。

        //浮点数判断时不靠谱
        //前面讲过了浮点数在计算机中常常无法精确表示，并且计算可能出现误差，因此，判断浮点数相等用==判断不靠谱：
        double x = 1 - 9.0 / 10;
        if (x == 0.1) {
            System.out.println("x is 0.1");
        } else {
            System.out.println("x is NOT 0.1");
        }
        //正确的方法是利用差值小于某个临界值来判断：
        x = 1 - 9.0 / 10;
        if (Math.abs(x - 0.1) < 0.00001) {
            System.out.println("x is 0.1");
        } else {
            System.out.println("x is NOT 0.1");
        }

        //判断引用类型相等
       /* 在Java中，判断值类型的变量是否相等，可以使用==运算符。
        但是，判断引用类型的变量是否相等，==表示“引用是否相等”，或者说，是否指向同一个对象。
        例如，下面的两个String类型，它们的内容是相同的，但是，分别指向不同的对象，用==判断，结果为false：*/
        String s1 = "hello";
        String s2 = "HELLO".toLowerCase();
        System.out.println(s1);
        System.out.println(s2);
        if (s1 == s2) {
            System.out.println("s1 == s2");
        } else {
            System.out.println("s1 != s2");
        }

        //要判断引用类型的变量内容是否相等，必须使用equals()方法：
        System.out.println(s1);
        System.out.println(s2);
        if (s1.equals(s2)) {
            System.out.println("s1 equals s2");
        } else {
            System.out.println("s1 not equals s2");
        }
        //注意：执行语句s1.equals(s2)时，如果变量s1为null，会报NullPointerException：
        /*
            s1 = null;
            if (s1.equals("hello")) {
                System.out.println("hello");
            }
        */
        //要避免NullPointerException错误，可以利用短路运算符&&：
        s1 = null;
        if (s1 != null && s1.equals("hello")) {
            System.out.println("hello");
        }
        //还可以把一定不是null的对象"hello"放到前面：例如：if ("hello".equals(s)) { ... }。
    }
}
