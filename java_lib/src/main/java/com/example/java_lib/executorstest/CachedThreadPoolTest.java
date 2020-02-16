package com.example.java_lib.executorstest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/10/27.
 *
 * newCachedThreadPool创建一个可缓存线程池，
 * 如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 */
public class CachedThreadPoolTest {
    public static void main(String[] args) {

        /**
         * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
         * 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
         * TODO 我从哪里看出来是复用第一个任务的线程了啊 ？
         */
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;

            //TODO 在这里睡 复用第一个线程
//            try {
//                Thread.sleep(index * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    //TODO 在这里睡 新建线程操作
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(index);
                    System.out.println("cur thread name:"+Thread.currentThread().getName());
                }
            });
        }
    }
}
