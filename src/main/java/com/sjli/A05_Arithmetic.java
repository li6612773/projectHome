package com.sjli;

public class A05_Arithmetic {
    public static void main(String[] args) {
        // 四则运算
        int i = (100 + 200) * (99 - 88); // 3300
        int n = 7 * (5 + (i - 9)); // 23072
        System.out.println(i);
        System.out.println(n);
        //除法
        int x = 12345 / 67; // 184
        System.out.println(x);
        //求余
        int y = 12345 % 67;
        System.out.println(y);
        //自增自减
        int n2 = 100;
        n2 += 100; // 3409, 相当于 n = n + 100;
        n2 -= 100; // 3309, 相当于 n = n - 100;
        //移位 可以对整数进行移位运算。对整数7左移1位将得到整数14，左移两位将得到整数28：
        int n3 = 7;       // 00000000 00000000 00000000 00000111 = 7
        int a = n3 << 1;  // 00000000 00000000 00000000 00001110 = 14
        int b = n3 << 2;  // 00000000 00000000 00000000 00011100 = 28
        int c = n3 << 28; // 01110000 00000000 00000000 00000000 = 1879048192
        int d = n3 << 29; // 11100000 00000000 00000000 00000000 = -536870912
        //如果对一个负数进行右移，最高位的1不动，结果仍然是一个负数：
        int n4 = -536870912;
        int a2 = n4 >> 1;  // 11110000 00000000 00000000 00000000 = -268435456
        int b2 = n4 >> 2;  // 11111000 00000000 00000000 00000000 = -134217728
        int c2 = n4 >> 28; // 11111111 11111111 11111111 11111110 = -2
        int d2 = n4 >> 29; // 11111111 11111111 11111111 11111111 = -1
        //还有一种无符号的右移运算，使用>>>，它的特点是不管符号位，右移后高位总是补0，因此，对一个负数进行>>>右移，它会变成正数，原因是最高位的1变成了0：
        int n5 = -536870912;
        int a3 = n5 >>> 1;  // 01110000 00000000 00000000 00000000 = 1879048192
        int b3 = n5 >>> 2;  // 00111000 00000000 00000000 00000000 = 939524096
        int c3 = n5 >>> 29; // 00000000 00000000 00000000 00000111 = 7
        int d3 = n5 >>> 31; // 00000000 00000000 00000000 00000001 = 1
        //对byte和short类型进行移位时，会首先转换为int再进行位移。仔细观察可发现，左移实际上就是不断地×2，右移实际上就是不断地÷2

        //位运算 位运算是按位进行与、或、非和异或的运算。与运算的规则是，必须两个数同时为1，结果才为1：
        n = 0 & 0; // 0
        n = 0 & 1; // 0
        n = 1 & 0; // 0
        n = 1 & 1; // 1
        //或运算的规则是，只要任意一个为1，结果就为1：
        n = 0 | 0; // 0
        n = 0 | 1; // 1
        n = 1 | 0; // 1
        n = 1 | 1; // 1
        //非运算的规则是，0和1互换：
        n = ~0; // 1
        n = ~1; // 0
        //异或运算的规则是，如果两个数不同，结果为1，否则为0：
        n = 0 ^ 0; // 0
        n = 0 ^ 1; // 1
        n = 1 ^ 0; // 1
        n = 1 ^ 1; // 0
        //对两个整数进行位运算，实际上就是按位对齐，然后依次对每一位进行运算。例如：
        int i2 = 167776589; // 00001010 00000000 00010001 01001101
        int n6 = 167776512; // 00001010 00000000 00010001 00000000
        System.out.println(i & n); // 167776512

        /*运算优先级
        在Java的计算表达式中，运算优先级从高到低依次是：
        ()
        ! ~ ++ --
         * / %
        + -
        << >> >>>
        &
        |
        += -= *= /=
        */

        //类型自动提升与强制转型  在运算过程中，如果参与运算的两个数类型不一致，那么计算结果为较大类型的整型。例如，short和int计算，结果总是int，原因是short首先自动被转型为int：
        short s = 1234;
        int i7 = 123456;
        int x7 = s + i7; // s自动转型为int
        //short y7 = s + i7; // 编译错误!
        //也可以将结果强制转型，即将大范围的整数转型为小范围的整数。强制转型使用(类型)，例如，将int强制转型为short：
        int i8 = 12345;
        short s8 = (short) i8; // 12345
        //要注意，超出范围的强制转型会得到错误的结果，原因是转型时，int的两个高位字节直接被扔掉，仅保留了低位的两个字节：
        int i9 = 1234567;
        short s9 = (short) i9; // -10617
        System.out.println(s9);
        int i10 = 12345678;
        short s10 = (short) i10; // 24910
        System.out.println(s10);
    }
}