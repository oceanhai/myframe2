package com.example.java_lib.designpattern.singletonpattern;

/**
 * 枚举形式 单例
 */
public enum  SingleTon4 {
    INSTANCE;

    private final A instance;

    SingleTon4() {
        instance = new A();
        instance.setNum(666);
    }

    public A getInstance(){
        return instance;
    }
}
