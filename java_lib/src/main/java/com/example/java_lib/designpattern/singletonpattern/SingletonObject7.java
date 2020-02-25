package com.example.java_lib.designpattern.singletonpattern;

/**
 * 枚举类实现单例模式
 * 可以跟 SingleTon4 进行比较 ，SingleTon4这个之前捎货帮网络请求框架中用到的枚举单例
 *
 * 枚举类实现单例模式是 effective java 作者极力推荐的单例实现模式，因为枚举类型是线程安全的，并且只会装载一次，
 * 设计者充分的利用了枚举的这个特性来实现单例模式，枚举的写法非常简单，而且枚举类型是所用单例实现中唯一一种不会被破坏的单例实现模式。
 */
public class SingletonObject7 {


    private SingletonObject7(){

    }

    /**
     * 枚举类型是线程安全的，并且只会装载一次
     */
    private enum Singleton{
        INSTANCE;

        private final SingletonObject7 instance;

        Singleton(){
            instance = new SingletonObject7();
        }

        private SingletonObject7 getInstance(){
            return instance;
        }
    }

    public static SingletonObject7 getInstance(){

        return Singleton.INSTANCE.getInstance();
    }
}
