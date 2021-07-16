package com.example.java_lib.designpattern.proxypattern.dynamicproxy;


/**
 * 被代理的实际类
 */
public class Student implements Person {
    private String name;
    public Student(String name) {
        this.name = name;
    }

    @Override
    public void giveMoney() {
        try {
            //假设数钱花了一秒时间
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "上交班费50元");
    }

    @Override
    public void handInHomework() {
        System.out.println(name + "上交了作业");
    }
}