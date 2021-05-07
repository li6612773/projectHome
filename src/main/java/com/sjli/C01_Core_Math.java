package com.sjli;

public class C01_Core_Math {
    public static void main(String[] args) {
    //Math
        //顾名思义，Math类就是用来进行数学计算的，它提供了大量的静态方法来便于我们实现数学计算：
        //
        //求绝对值：
        Math.abs(-100); // 100
        Math.abs(-7.8); // 7.8
        //取最大或最小值：
        Math.max(100, 99); // 100
        Math.min(1.2, 2.3); // 1.2
        //计算xy次方：
        Math.pow(2, 10); // 2的10次方=1024
        //计算√x：
        Math.sqrt(2); // 1.414...
        //计算ex次方
        Math.exp(2); // 7.389...
        //计算以e为底的对数：
        Math.log(4); // 1.386...
        //计算以10为底的对数：
        Math.log10(100); // 2
        //三角函数：
        Math.sin(3.14); // 0.00159...
        Math.cos(3.14); // -0.9999...
        Math.tan(3.14); // -0.0015...
        Math.asin(1.0); // 1.57079...
        Math.acos(1.0); // 0.0
        //Math还提供了几个数学常量：
        double pi = Math.PI; // 3.14159...
        double e = Math.E; // 2.7182818...
        Math.sin(Math.PI / 6); // sin(π/6) = 0.5
        //生成一个随机数x，x的范围是0 <= x < 1：
        Math.random(); // 0.53907... 每次都不一样
        for (int i = 0; i < 10; i++) {
            System.out.println(Math.random());
        }
        //如果我们要生成一个区间在[MIN, MAX)的随机数，
        // 可以借助Math.random()实现，计算如下：
        double x = Math.random(); // x的范围是[0,1)
        double min = 10;
        double max = 50;
        double y = x * (max - min) + min; // y的范围是[10,50)
        long n = (long) y; // n的范围是[10,50)的整数
        System.out.println(y);
        System.out.println(n);
        //有些童鞋可能注意到Java标准库还提供了一个StrictMath，
        // 它提供了和Math几乎一模一样的方法。这两个类的区别在于，
        // 由于浮点数计算存在误差，不同的平台（例如x86和ARM）
        // 计算的结果可能不一致（指误差不同），因此，StrictMath
        // 保证所有平台计算结果都是完全相同的，而Math会尽量针对平台优化计算速度，
        // 所以，绝大多数情况下，使用Math就足够了。
    }
}
