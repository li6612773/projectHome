package com.sjli.collection;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Set接口并不保证有序，而SortedSet接口则保证元素是有序的：
 *
 * HashSet是无序的，因为它实现了Set接口，并没有实现SortedSet接口；
 * TreeSet是有序的，因为它实现了SortedSet接口。
 * 用一张图表示：
 *
 *        ┌───┐
 *        │Set│
 *        └───┘
 *          ▲
 *     ┌────┴─────┐
 *     │          │
 * ┌───────┐ ┌─────────┐
 * │HashSet│ │SortedSet│
 * └───────┘ └─────────┘
 *                ▲
 *                │
 *           ┌─────────┐
 *           │ TreeSet │
 *           └─────────┘
 */

public class SortedSet_In_Java {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("apple");
        set.add("banana");
        set.add("pear");
        set.add("orange");
        for (String s : set) {
            System.out.println(s);
        }

        //注意输出的顺序既不是添加的顺序，也不是String排序的顺序，在不同版本的JDK中，这个顺序也可能是不同的。
        //
        //把HashSet换成TreeSet，在遍历TreeSet时，输出就是有序的，这个顺序是元素的排序顺序：
        Set<String> set2 = new TreeSet<>();
        set2.add("apple");
        set2.add("banana");
        set2.add("pear");
        set2.add("orange");
        for (String s : set2) {
            System.out.println(s);
        }
        //使用TreeSet和使用TreeMap的要求一样，添加的元素必须正确实现Comparable接口，
        // 如果没有实现Comparable接口，那么创建TreeSet时必须传入一个Comparator对象。
    }
}
