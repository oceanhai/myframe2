package com.example.java_lib.m2019.fenghuangfm;

import org.junit.Test;

/**
 * ※ @test注意
 * 我们运行单元测试的时候，得到的结果却是：
 * junit测试主线程结束
 * junit测试子线程开始
 * 并未打印“junit测试子线程结束”，
 * 这是由于主线退出后，子线程也立即退出
 *
 * 解决办法
 * 在junit测试主线程执行的时候让主线sleep一段时间：
 * 就可以看到子线程结束的语句
 * 这是由于在缺省的情况下，虚拟机等待所有的线程结束程序才结束。
 * 守护线程例外，会随程序主动结束。
 */
public class VolatileTest {

    private volatile static int num = 0;//manyThread ③多线程下 num++ 出现重复情况
    private volatile int num2 = 0;//多线程下 num2++  出现重复
    private static int num3 = 0;//manyThread3 多线程下 num3++ 出现重复情况
    private int num4 = 0;//manyThread4 多线程下 num4++ 出现重复


    public static void main(String[] args){
//        manyThread();
        manyThread3();
//        method03();
    }

    /**
     * manyThread ③多线程下 num++ 出现重复情况
     */
    public static void manyThread(){
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
    public void manyThread2(){

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
                        num2++;
                        System.out.println("num2="+num2);//方便我把结果copy到excel里查证
                    }

                    System.out.println("junit 测试子线程结束:"+Thread.currentThread().getName());
                }
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("junit 测试主线程结束");

    }

    /**
     *manyThread3 多线程下 num3++ 出现重复情况
     */
    public static void manyThread3(){
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
                        num3++;
                        System.out.println(num3);//方便我把结果copy到excel里查证
                    }
                }
            }).start();
        }

    }

    /**
     *
     */
    @Test
    public void manyThread4(){
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
                        num4++;
                        System.out.println("num4="+num4);//方便我把结果copy到excel里查证
                    }
                    System.out.println("junit 测试子线程结束:"+Thread.currentThread().getName());
                }
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("junit 测试主线程结束");
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