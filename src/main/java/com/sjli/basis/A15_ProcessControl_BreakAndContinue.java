package com.sjli.basis;

public class A15_ProcessControl_BreakAndContinue {
    public static void main(String[] args) {
    //break和continue
        //无论是while循环还是for循环，有两个特别的语句可以使用，就是break语句和continue语句。
    //break
        //在循环过程中，可以使用break语句跳出当前循环。我们来看一个例子：
        int sum = 0;
        for (int i=1; ; i++) {
            sum = sum + i;
            if (i == 100) {
                break;
            }
        }
        System.out.println(sum);
        //break语句通常都是配合if语句使用。要特别注意，break语句总是跳出自己所在的那一层循环。例如：
        for (int i=1; i<=10; i++) {
            System.out.println("i = " + i);
            for (int j=1; j<=10; j++) {
                System.out.println("j = " + j);
                if (j >= i) {
                    break;
                }
            }
            // break跳到这里
            System.out.println("breaked");
        }
        //上面的代码是两个for循环嵌套。因为break语句位于内层的for循环，因此，它会跳出内层for循环，但不会跳出外层for循环。

    //continue
        //break会跳出当前循环，也就是整个循环都不会执行了。而continue则是提前结束本次循环，直接继续执行下次循环。我们看一个例子：
        sum = 0;
        for (int i=1; i<=10; i++) {
            System.out.println("begin i = " + i);
            if (i % 2 == 0) {
                continue; // continue语句会结束本次循环
            }
            sum = sum + i;
            System.out.println("end i = " + i);
        }
        System.out.println(sum); // 25

    }
}
