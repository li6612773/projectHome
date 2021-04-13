package com.sjli;

public class A14_ProcessControl_WhileAndFor {
    public static void main(String[] args) {
    //while循环
        //while循环来累加1到100，可以这么写：
        int sum = 0; // 累加的和，初始化为0
        int n = 1;
        while (n <= 100) { // 循环条件是n <= 100
            sum = sum + n; // 把n累加到sum中
            n ++; // n自身加1
        }
        System.out.println(sum); // 5050
    //do while循环
        //while循环是先判断循环条件，再执行循环。而另一种do while循环则是先执行循环，
        // 再判断条件，条件满足时继续循环，条件不满足时退出。
        //把对1到100的求和用do while循环改写一下：
        sum = 0;
        n = 1;
        do {
            sum = sum + n;
            n ++;
        } while (n <= 100);
        System.out.println(sum);
    //for循环
        //for循环的功能非常强大，它使用计数器实现循环。for循环会先初始化计数器，
        // 然后，在每次循环前检测循环条件，在每次循环后更新计数器。计数器变量通常命名为i
        sum = 0;
        for (int i=1; i<=100; i++) {
            sum = sum + i;
        }
        System.out.println(sum);

        //如果我们要对一个整型数组的所有元素求和，可以用for循环实现：
        int[] ns = { 1, 4, 9, 16, 25 };
        sum = 0;
        for (int i=0; i<ns.length; i++) {
            System.out.println("i = " + i + ", ns[i] = " + ns[i]);
            sum = sum + ns[i];
        }
        System.out.println("sum = " + sum);
    //灵活使用for循环
        //for循环还可以缺少初始化语句、循环条件和每次循环更新语句，例如：
        /*
        // 不设置结束条件:
                for (int i=0; ; i++) {
            ...
                }
        // 不设置结束条件和更新语句:
                for (int i=0; ;) {
            ...
                }
        // 什么都不设置:
                for (;;) {
            ...
                }
        */
        //通常不推荐这样写，但是，某些情况下，是可以省略for循环的某些语句的。
    //for each循环
        //Java还提供了另一种for each循环，它可以更简单地遍历数组：
        int[] ns1 = { 1, 4, 9, 16, 25 };
        for (int n1 : ns1) {
            System.out.println(n1);
        }
    }
}
