package com.example.java_lib.designpattern.singletonpattern;

/**
 * 双重检查锁定
 */
public class DoubleCheckedLocking { // 1

//    private static DoubleCheckedLocking instance; // 2
    private static volatile DoubleCheckedLocking instance;//volatile 修饰 被volatile关键字修饰的变量是被禁止重排序的

    public static DoubleCheckedLocking getInstance() { // 3
        if (instance == null) { // 4:第一次检查
            synchronized (DoubleCheckedLocking.class) { // 5:加锁
                if (instance == null) // 6:第二次检查
                    instance = new DoubleCheckedLocking(); // 7:问题的根源出在这里
            } // 8
        } // 9
        return instance; // 10
    } // 11
}