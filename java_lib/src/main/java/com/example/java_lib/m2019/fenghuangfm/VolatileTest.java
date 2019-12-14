package com.example.java_lib.m2019.fenghuangfm;

import org.junit.Test;

public class VolatileTest {

    private volatile static int num = 0;//③多线程下 num++ 没出现重复
//    private volatile int num = 0;//③多线程下 num++ 没出现重复
//    private static int num = 0;//②多线程下 num++ 没出现重复
//    private int num = 0;//①多线程下 num++ 出现重复

    public static void main(String[] args){
        manyThread();
//        method03();
    }

    public static void manyThread(){
//    @Test
//    public void manyThread(){

        for (int x=0;x<3;x++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int y=0;y<80;y++){
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        num++;
                        System.out.println(num);//方便我把结果copy到excel里查证
                    }
                }
            }).start();
        }

    }

    @Test
    public void method01(){
        int a= 10;
        int b= 10;
        System.out.println(a++);
        System.out.println(++b);
    }

    /**
     * 应该是由于 final的原因，所以非volatile修饰也能停
     */
    @Test
    public void method02(){
        final StoppableTask thread = new StoppableTask();
        thread.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                thread.tellMeToStop();
            }
        }).start();
    }

    /**
     *
     */
    public static void method03(){
        final StoppableTask thread = new StoppableTask();
        thread.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.tellMeToStop();
    }

}

class StoppableTask extends Thread{

//    private volatile boolean pleaseStop;
    private boolean pleaseStop;

    @Override
    public void run() {
        while (!pleaseStop) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("StoppableTask do something");
        }
    }


    public void tellMeToStop() {
        pleaseStop = true;
        System.out.println("StoppableTask 停");
    }
}