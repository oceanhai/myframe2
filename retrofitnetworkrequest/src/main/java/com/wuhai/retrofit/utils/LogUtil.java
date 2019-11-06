package com.wuhai.retrofit.utils;

import android.util.Log;

/**
 * Created by wuhai on 2017/1/6
 * 描述: 日志工具类
 */
public class LogUtil {

    private LogUtil()
    {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static String TAG = "lct_log";

    //设置tag
    public static void setLog(String tag){
        TAG=tag+"_"+TAG;
    }

    public static void init(boolean isdebug){
        isDebug =isdebug;
    }

    public static boolean isDebug = true;

    public static void i(String msg) {
        if (isDebug){
            Log.i(TAG, msg);
        }
    }

    public static void i(int msg) {
        if (isDebug){
            Log.i(TAG, msg + "");
        }
    }

    public static void d(String msg) {
        if (isDebug){
            Log.d(TAG, msg);
        }
    }

    public static void d(int msg) {
        if (isDebug){
            Log.d(TAG, msg + "");
        }
    }

    public static void v(String msg) {
        if (isDebug){
            Log.v(TAG, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug){
            Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (isDebug){
            Log.v(tag, msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug){
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug){
            Log.d(tag, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug){
            Log.e(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug){
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (isDebug){
            Log.e(tag, msg, tr);
        }
    }
}
