package com.example.java_lib.designpattern.singletonpattern;


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
