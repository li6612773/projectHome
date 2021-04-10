package com.sjli;

public class Array {
    public static void main(String[] args) {
    //数组类型
        //如果我们有一组类型相同的变量，例如，5位同学的成绩，可以这么写：
        // 5位同学的成绩:
        int n1 = 68;
        int n2 = 79;
        int n3 = 91;
        int n4 = 85;
        int n5 = 62;
        //但其实没有必要定义5个int变量。可以使用数组来表示“一组”int类型。代码如下：
        // 5位同学的成绩:
        int[] ns = new int[5];
        ns[0] = 68;
        ns[1] = 79;
        ns[2] = 91;
        ns[3] = 85;
        ns[4] = 62;
    }
}
