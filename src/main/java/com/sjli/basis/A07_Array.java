package com.sjli.basis;

import java.util.Arrays;

public class A07_Array {
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
        /*Java的数组有几个特点：

        数组所有元素初始化为默认值，整型都是0，浮点型是0.0，布尔型是false；
        数组一旦创建后，大小就不可改变。
        要访问数组中的某一个元素，需要使用索引。数组索引从0开始，例如，5个元素的数组，索引范围是0~4。

        可以修改数组中的某一个元素，使用赋值语句，例如，ns[1] = 79;。
        */
        //可以用数组变量.length获取数组大小：
        // 5位同学的成绩:
        int[] ns1 = new int[5];
        System.out.println(ns1.length); // 5
        //数组是引用类型，在使用索引访问数组元素时，如果索引超出范围，运行时将报错：
//        System.out.println(ns1[5]); // 索引n不能超出范围
        //可以在定义数组时直接指定初始化的元素，这样就不必写出数组大小，而是由编译器自动推算数组大小。例如：
        // 5位同学的成绩:
        int[] ns22 = new int[] { 68, 79, 91, 85, 62 };
        System.out.println(ns22.length); // 编译器自动推算数组大小为5
        //还可以进一步简写为：
        int[] ns222 = { 68, 79, 91, 85, 62 };

        //数组是引用类型，并且数组大小不可变。我们观察下面的代码
        // 5位同学的成绩:
        int[] ns33;
        ns33 = new int[] { 68, 79, 91, 85, 62 };
        System.out.println(ns33.length); // 5
        ns33 = new int[] { 1, 2, 3 };
        System.out.println(ns33.length); // 3

    //字符串数组
        //如果数组元素不是基本类型，而是一个引用类型，那么，修改数组元素会有哪些不同？
        //
        //字符串是引用类型，因此我们先定义一个字符串数组：
        String[] names = {"ABC", "XYZ", "zoo"};
        //对于String[]类型的数组变量names，它实际上包含3个元素，但每个元素都指向某个字符串对象：
        /*        ┌─────────────────────────┐
            names │   ┌─────────────────────┼───────────┐
              │   │   │                     │           │
              ▼   │   │                     ▼           ▼
        ┌───┬───┬─┴─┬─┴─┬───┬───────┬───┬───────┬───┬───────┬───┐
        │   │░░░│░░░│░░░│   │ "ABC" │   │ "XYZ" │   │ "zoo" │   │
        └───┴─┬─┴───┴───┴───┴───────┴───┴───────┴───┴───────┴───┘
              │                 ▲
              └─────────────────┘
        对names[1]进行赋值，例如names[1] = "cat";，效果如下：

                  ┌─────────────────────────────────────────────────┐
            names │   ┌─────────────────────────────────┐           │
              │   │   │                                 │           │
              ▼   │   │                                 ▼           ▼
        ┌───┬───┬─┴─┬─┴─┬───┬───────┬───┬───────┬───┬───────┬───┬───────┬───┐
        │   │░░░│░░░│░░░│   │ "ABC" │   │ "XYZ" │   │ "zoo" │   │ "cat" │   │
        └───┴─┬─┴───┴───┴───┴───────┴───┴───────┴───┴───────┴───┴───────┴───┘
              │                 ▲
              └─────────────────┘
        这里注意到原来names[1]指向的字符串"XYZ"并没有改变，仅仅是将names[1]的引用从指向"XYZ"改成了指向"cat"，
        其结果是字符串"XYZ"再也无法通过names[1]访问到了。*/

        //对“指向”有了更深入的理解后，试解释如下代码：
        String[] names1 = {"ABC", "XYZ", "zoo"};
        String s1 = names1[1];
        names1[1] = "cat";
        System.out.println(s1); // s是"XYZ"还是"cat"?
        //比较两个字符串数组是否相等
        String[] a = {"ABC", "XYZ", "zoo"};
        String[] b = {"ABC", "XYZ", "zoo"};
        if(a == b) System.out.println("相等？"+a+"     "+b);else System.out.println("不相等？"+a+"     "+b);

        if(a.toString().equals(b.toString()) ) {
            System.out.println("相等？"+a.toString()+"     "+b.toString());
        }else {
            System.out.println("不相等？"+a.toString()+"     "+b.toString());
        }
        if(Arrays.equals(a,b)) {
            System.out.println("相等！！！！");
        }else {
            System.out.println("不相等！！！！");
        }
        if(compare(a,b)) {
            System.out.println("相等！！！！");
        }else {
            System.out.println("不相等！！！！");
        }


    }
    public static boolean compare(String[] a, String[] b){
        if(a.length == b.length){//先判断数组的长度是否相等
            for(int i = 0; i < a.length; i ++){//在判断内容是否相等
                if((a[i].equals(b[i]))!= true){
                    return false;
                }
            }
        }else{
            return true;
        }
        return true;
    }
}
