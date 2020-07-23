package com.wuhai.myhttp.http;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 整体的运作机制在这个类完成
 */
public class ThreedManager {

    private static final ThreedManager ourInstance = new ThreedManager();

    static ThreedManager getInstance() {
        return ourInstance;
    }

    //定以一个请求队列 阻塞 容量无限大
    private LinkedBlockingQueue<Runnable> mQueue = new LinkedBlockingQueue<>();

    //处理中心
    private ThreadPoolExecutor threadPoolExecutor;

    //添加任务
    public void addTask(Runnable runnable){
        if(runnable == null){
            return;
        }
        mQueue.add(runnable);
    }

    private ThreedManager() {
        threadPoolExecutor = new ThreadPoolExecutor(3, 10, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(4), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                //参数r出了问题丢出来的任务
                //重新放回到队列中
                mQueue.add(r);
            }
        });

        threadPoolExecutor.execute(runnable);
    }

    /**
     * 核心线程
     */
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true){
                try {
                    Runnable task = mQueue.take();//如果取不到，就停在这里  TODO 这里阻塞了也不会影响UI线程
                    threadPoolExecutor.execute(task);//取到了，线程唤醒，执行任务
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

}
