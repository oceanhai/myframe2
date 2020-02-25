package com.example.java_lib.designpattern.singletonpattern;

/**
 * 懒汉式 枷锁
 */
public class SingletonObject3 {
    private static SingletonObject3 instance;

    private SingletonObject3(){

    }

    public synchronized static SingletonObject3 getInstance(){
        /**
         * 添加class类锁，影响了性能，加锁之后将代码进行了串行化，
         * 我们的代码块绝大部分是读操作，在读操作的情况下，代码线程是安全的
         *
         */

        if (instance == null)
            instance = new SingletonObject3();
        return instance;
    }
}

