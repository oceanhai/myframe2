package com.example.java_lib.designpattern.singletonpattern;

/**
 * 双重检查锁模式
 *
 * 双重检查锁模式是一种非常好的单例实现模式，解决了单例、性能、线程安全问题，上面的双重检测锁模式看上去完美无缺，
 * 其实是存在问题，
 * 在多线程的情况下，可能会出现空指针问题，出现问题的原因是JVM在实例化对象的时候会进行优化和指令重排序操作。
 */
public class SingletonObject4 {
    private static SingletonObject4 instance;

    private SingletonObject4(){

    }

    public static SingletonObject4 getInstance(){

        // 第一次判断，如果这里为空，不进入抢锁阶段，直接返回实例
        if (instance == null)
            synchronized (SingletonObject4.class){
                // 抢到锁之后再次判断是否为空
                if (instance == null){
                    instance = new SingletonObject4();
                }
            }

        return instance;
    }
}

