package com.sjli.basis;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

public class C01_Core_Random {
    public static void main(String[] args) {
    //Random
        //Random用来创建伪随机数。所谓伪随机数，是指只要给定一个初始的种子，产生的随机数序列是完全一样的。
        //
        //要生成一个随机数，可以使用nextInt()、nextLong()、nextFloat()、nextDouble()：
        Random r = new Random();
        r.nextInt(); // 2071575453,每次都不一样
        r.nextInt(10); // 5,生成一个[0,10)之间的int
        r.nextLong(); // 8811649292570369305,每次都不一样
        r.nextFloat(); // 0.54335...生成一个[0,1)之间的float
        r.nextDouble(); // 0.3716...生成一个[0,1)之间的double
        //有童鞋问，每次运行程序，生成的随机数都是不同的，没看出伪随机数的特性来。
        //
        //这是因为我们创建Random实例时，如果不给定种子，就使用系统当前时间戳作为种子，
        // 因此每次运行时，种子不同，得到的伪随机数序列就不同。
        //
        //如果我们在创建Random实例时指定一个种子，就会得到完全确定的随机数序列：
        Random r2 = new Random(12345);
        for (int i = 0; i < 10; i++) {
            System.out.println(r2.nextInt(100));
        }
        // 51, 80, 41, 28, 55...
        //前面我们使用的Math.random()实际上内部调用了Random类，所以它也是伪随机数，只是我们无法指定种子。
    //SecureRandom
        //有伪随机数，就有真随机数。实际上真正的真随机数只能通过量子力学原理来获取，
        // 而我们想要的是一个不可预测的安全的随机数，SecureRandom就是用来创建安全的随机数的：
        SecureRandom sr = new SecureRandom();
        System.out.println(sr.nextInt(100));
        // SecureRandom无法指定种子，它使用RNG（random number generator）算法。
        // JDK的SecureRandom实际上有多种不同的底层实现，有的使用安全随机种子加上伪随机数算法来产生安全的随机数，
        // 有的使用真正的随机数生成器。实际使用的时候，可以优先获取高强度的安全随机数生成器，
        // 如果没有提供，再使用普通等级的安全随机数生成器：
        SecureRandom sr2 = null;
        try {
            sr2 = SecureRandom.getInstanceStrong(); // 获取高强度安全随机数生成器
        } catch (NoSuchAlgorithmException e) {
            sr2 = new SecureRandom(); // 获取普通的安全随机数生成器
        }
        byte[] buffer = new byte[16];
        sr2.nextBytes(buffer); // 用安全随机数填充buffer
        System.out.println(Arrays.toString(buffer));
        //SecureRandom的安全性是通过操作系统提供的安全的随机种子来生成随机数。
        // 这个种子是通过CPU的热噪声、读写磁盘的字节、网络流量等各种随机事件产生的“熵”。
        //
        //在密码学中，安全的随机数非常重要。如果使用不安全的伪随机数，所有加密体系都将被攻破。
        // 因此，时刻牢记必须使用SecureRandom来产生安全的随机数。
        //需要使用安全随机数的时候，必须使用SecureRandom，绝不能使用Random！

    }




}
