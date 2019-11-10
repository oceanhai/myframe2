package com.example.java_lib.m2016;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {

        method01();
    }

    //酷划
    private static void method01() {
        Thread t1 = new  Thread(new T1());
        Thread t3 = new  Thread(new T3());
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(t1);
        es.execute(t3);
        es.shutdown();
    }
}

class T3 implements Runnable {
    @Override
    public void run() {
        Walk walk = new Walk();
//        Walk walk = Walk.walk;
        walk.walk();
    }
}

class T1 implements Runnable{
    @Override
    public void run() {
        Walk walk = new Walk();
//        Walk walk = Walk.walk;
        // 这里我依然用的new
        walk.run();
    }
}