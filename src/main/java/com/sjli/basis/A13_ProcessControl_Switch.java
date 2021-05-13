package com.sjli.basis;

public class A13_ProcessControl_Switch {
    public static void main(String[] args) {
        //switch多重选择
        /*除了if语句外，还有一种条件判断，是根据某个表达式的结果，分别去执行不同的分支。

        例如，在游戏中，让用户选择选项：

        单人模式
                多人模式
        退出游戏
        这时，switch语句就派上用场了。

        switch语句根据switch (表达式)计算的结果，跳转到匹配的case结果，然后继续执行后续语句，直到遇到break结束执行。*/
        int option = 1;
        switch (option) {
            case 1:
                System.out.println("Selected 1");
                break;
            case 2:
                System.out.println("Selected 2");
                break;
            case 3:
                System.out.println("Selected 3");
                break;
        }

        //如果option的值没有匹配到任何case，例如option = 99，那么，switch语句不会执行任何语句。
        // 这时，可以给switch语句加一个default，当没有匹配到任何case时，执行default：
        option = 99;
        switch (option) {
            case 1:
                System.out.println("Selected 1");
                break;
            case 2:
                System.out.println("Selected 2");
                break;
            case 3:
                System.out.println("Selected 3");
                break;
            default:
                System.out.println("Not selected");
                break;
        }
        //如果把switch语句翻译成if语句，那么上述的代码相当于：
        if (option == 1) {
            System.out.println("Selected 1");
        } else if (option == 2) {
            System.out.println("Selected 2");
        } else if (option == 3) {
            System.out.println("Selected 3");
        } else {
            System.out.println("Not selected");
        }
        //对于多个==判断的情况，使用switch结构更加清晰。
        //
        //同时注意，上述“翻译”只有在switch语句中对每个case正确编写了break语句才能对应得上。
        //
        //使用switch时，注意case语句并没有花括号{}，而且，case语句具有“穿透性”，漏写break将导致意想不到的结果：
        System.out.println("----------------------------");
        option = 2;
        switch (option) {
            case 1:
                System.out.println("Selected 1");
            case 2:
                System.out.println("Selected 2");
            case 3:
                System.out.println("Selected 3");
            default:
                System.out.println("Not selected");
        }
        //如果有几个case语句执行的是同一组语句块，可以这么写：
        option = 2;
        switch (option) {
            case 1:
                System.out.println("Selected 1");
                break;
            case 2:
            case 3:
                System.out.println("Selected 2, 3");
                break;
            default:
                System.out.println("Not selected");
                break;
        }
        //switch语句还可以匹配字符串。字符串匹配时，是比较“内容相等”。例如：
        String fruit = "apple";
        switch (fruit) {
            case "apple":
                System.out.println("Selected apple");
                break;
            case "pear":
                System.out.println("Selected pear");
                break;
            case "mango":
                System.out.println("Selected mango");
                break;
            default:
                System.out.println("No fruit selected");
                break;
        }
        //switch语句还可以使用枚举类型，枚举类型我们在后面讲解。

    //switch表达式
        //使用switch时，如果遗漏了break，就会造成严重的逻辑错误，而且不易在源代码中发现错误。
        // 从Java 12开始，switch语句升级为更简洁的表达式语法，使用类似模式匹配（Pattern Matching）的方法，
        // 保证只有一种路径会被执行，并且不需要break语句：
        fruit = "apple";
        switch (fruit) {
            case "apple" -> System.out.println("Selected apple");
            case "pear" -> System.out.println("Selected pear");
            case "mango" -> {
                System.out.println("Selected mango");
                System.out.println("Good choice!");
            }
            default -> System.out.println("No fruit selected");
        }
        //注意新语法使用->，如果有多条语句，需要用{}括起来。不要写break语句，因为新语法只会执行匹配的语句，没有穿透效应。

        //很多时候，我们还可能用switch语句给某个变量赋值。例如：
        int opt;
        switch (fruit) {
            case "apple":
                opt = 1;
                break;
            case "pear":
            case "mango":
                opt = 2;
                break;
            default:
                opt = 0;
                break;
        }
        //使用新的switch语法，不但不需要break，还可以直接返回值。把上面的代码改写如下：
        fruit = "apple";
        int opt1 = switch (fruit) {
            case "apple" -> 1;
            case "pear", "mango" -> 2;
            default -> 0;
        }; // 注意赋值语句要以;结束
        System.out.println("opt = " + opt1);
    //yield
        //大多数时候，在switch表达式内部，我们会返回简单的值。
        //
        //但是，如果需要复杂的语句，我们也可以写很多语句，放到{...}里，然后，用yield返回一个值作为switch语句的返回值：
        fruit = "orange";
        int opt2 = switch (fruit) {
            case "apple" -> 1;
            case "pear", "mango" -> 2;
            default -> {
                int code = fruit.hashCode();
                yield code; // switch语句返回值
            }
        };
        System.out.println("opt = " + opt2);



    }
}
