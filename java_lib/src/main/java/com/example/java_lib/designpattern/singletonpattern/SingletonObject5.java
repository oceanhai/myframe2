package com.example.java_lib.designpattern.singletonpattern;

/**
 * 双重检查锁模式 + volatile
 *
 * 解决重排序问题
 *
 */
public class SingletonObject5 {
    // 添加volatile关键字
    private static volatile SingletonObject5 instance;

    private SingletonObject5(){

    }

    public static SingletonObject5 getInstance(){

        if (instance == null)
            synchronized (SingletonObject5.class){
                if (instance == null){
                    instance = new SingletonObject5();
                }
            }

        return instance;
    }
}

