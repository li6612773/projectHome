package com.sjli.unitTesting;

/**
 * @Classname Factorial
 * @Description TODO
 * @Date 2021/9/13 14:21
 * @Created by steven
 */
public class Factorial {
    public static long fact(long n) {
        long r = 1;
        for (long i = 1; i <= n; i++) {
            r = r * i;
        }
        return r;
    }
}
