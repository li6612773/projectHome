package com.sjli.collection;

import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueue_In_Java {
    public static void main(String[] args) {
        Queue<String> q = new PriorityQueue<>();
        // 添加3个元素到队列:
        q.offer("apple");
        q.offer("pear");
        q.offer("banana");
        System.out.println(q.poll()); // apple
        System.out.println(q.poll()); // banana
        System.out.println(q.poll()); // pear
        System.out.println(q.poll()); // null,因为队列为空
        //优先队列：PriorityQueue。
        //
        //PriorityQueue和Queue的区别在于，它的出队顺序与元素的优先级有关，对PriorityQueue调用remove()或poll()方法，返回的总是优先级最高的元素。
        //
        //要使用PriorityQueue，我们就必须给每个元素定义“优先级”。我们以实际代码为例，先看看PriorityQueue的行为：
        Queue<User> q2 = new PriorityQueue<>(new UserComparator());
        // 添加3个元素到队列:
        q2.offer(new User("Bob", "A1"));
        q2.offer(new User("Alice", "A2"));
        q2.offer(new User("Boss", "V1"));
        System.out.println(q2.poll()); // Boss/V1
        System.out.println(q2.poll()); // Bob/A1
        System.out.println(q2.poll()); // Alice/A2
        System.out.println(q2.poll()); // null,因为队列为空
    }
}
