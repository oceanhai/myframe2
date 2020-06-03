package com.wuhai.myframe2.utils;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 作者 wh
 *
 * 创建日期 2020/2/13 12:37
 *
 * 描述：线程池
 */

public class ThreadPoolUtils {

    private static ThreadPoolUtils instance;

    private ExecutorService cachedThreadPool;

    public static ThreadPoolUtils getInstance() {
        if (instance == null) {
            instance = new ThreadPoolUtils();
        }
        return instance;
    }

    public ExecutorService getCachedThreadPool() {
        if (cachedThreadPool == null) {
            cachedThreadPool = Executors.newCachedThreadPool();
        }
        return cachedThreadPool;
    }

}
