package com.sjli;

public class DataType {
    public static void main(String[] args) {

/*       各种变量占内存大小：
       byte   1字节 = 8位二进制
       ┌───┐
       └───┘
       short  2字节
       ┌───┬───┐
       └───┴───┘
       int    4字节
       ┌───┬───┬───┬───┐
       └───┴───┴───┴───┘
       long   8字节
       ┌───┬───┬───┬───┬───┬───┬───┬───┐
       └───┴───┴───┴───┴───┴───┴───┴───┘
       float  4字节
       ┌───┬───┬───┬───┐
       └───┴───┴───┴───┘
       double 8字节
       ┌───┬───┬───┬───┬───┬───┬───┬───┐
       └───┴───┴───┴───┴───┴───┴───┴───┘
       char   2字节
       ┌───┬───┐
       └───┴───┘
       */

        //      整数类型：byte，short，int，long
        int n = 100 ;
        System.out.println(n);

//      浮点数类型：float，double
        float f1 = 3.14f;
        float f2 = 3.14e38f; // 科学计数法表示的3.14x10^38
        double d = 1.79e308;
        double d2 = -1.79e308;
        double d3 = 4.9e-324; // 科学计数法表示的4.9x10^-324
        System.out.println(f1+"\n"+f2+"\n"+d2);
//      字符类型：char  字符类型char表示一个字符。Java的char类型除了可表示标准的ASCII外，还可以表示一个Unicode字符：
        char a = 'A';
        char zh = '中';
        System.out.println(a);
        System.out.println(zh);
//      布尔类型：boolean
        boolean b1 = true;
        boolean b2 = false;
        boolean isGreater = 5 > 3; // 计算结果为true
        int age = 12;
        boolean isAdult = age >= 18; // 计算结果为false
        System.out.println(b1+"\n"+b2);

//      var关键字 类型的名字太长，写起来比较麻烦,这个时候，如果想省略变量类型，可以使用var关键字：
        var sb = new StringBuilder();
    }
}
