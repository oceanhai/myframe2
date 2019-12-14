package com.example.java_lib.m2019.fenghuangfm;

/**
 *https://blog.csdn.net/QQB67G8COM/article/details/89635238
 * 上述文章是屎吗  坑人玩意，不用volatile 也能停下来 什么原因？！
 * 这个就是垃圾 不用看
 */
public class RunThreadDemo extends Thread {
    private volatile boolean isRunning = true;  //(1)
//    private boolean isRunning = true;			//(2)
    private void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

    public void run(){
        System.out.println("enter run method..");
        while(isRunning == true){
            System.out.println("do something");
        }
        System.out.println("Thread stop!");
    }

    public static void main(String[] args) throws InterruptedException {
        RunThreadDemo rtd = new RunThreadDemo();
        rtd.start();
        Thread.sleep(1000);						//(3)
        rtd.setRunning(false);					//(4)
        System.out.println("isRunning status:"+rtd.isRunning);
    }
}

