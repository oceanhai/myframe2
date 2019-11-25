package com.example.java_lib.executorstest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/10/27.
 * newFixedThreadPool 创建一个定长线程池，
 * 可控制线程最大并发数，超出的线程会在队列中等待。
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        /**
         *创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。示例代码如下
         * 因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。
         * 定长线程池的大小最好根据系统资源进行设置。
         */
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    try {
                        System.out.println(index);
//                        Thread.sleep(2000);//报错 java.lang.InterruptedException: sleep interrupted 不能打断
//                        wait(2000);//依然报错 java.lang.IllegalMonitorStateException
                        for(;;){//这次 如果执行 shutdownNow() 就不报错

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        /**
         * 可以关闭 ExecutorService，这将导致其拒绝新任务。提供两个方法来关闭
         * ExecutorService.shutdown() 方法在终止前允许执行以前提交的任务
         * shutdownNow() 方法阻止等待任务启动并试图停止当前正在执行的任务。
         *
         * shutdown调用后，不可以再submit新的task，已经submit的将继续执行。
         * shutdownNow试图停止当前正执行的task，并返回尚未执行的task的list
         */
//        fixedThreadPool.shutdown();
        fixedThreadPool.shutdownNow();
    }
}
