package com.example.java_lib.designpattern.singletonpattern;

/**
 * 参考文章
 *
 * https://blog.csdn.net/z694644032/article/details/100109014
 * SingletonObject1-7系列都是上述文章的例子
 *
 * 饿汉模式
 */
public class SingletonObject1 {
    private static final SingletonObject1 ourInstance = new SingletonObject1();

    public static SingletonObject1 getInstance() {
        return ourInstance;
    }

    private SingletonObject1() {
    }
}
