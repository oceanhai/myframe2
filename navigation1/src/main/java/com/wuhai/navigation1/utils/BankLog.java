package com.wuhai.navigation1.utils;

import android.util.Log;

/**
 * Created by Administrator on 2017/3/29.
 */

public class BankLog {

    public static String TAG = "pactera_bank";
    public static boolean debug = true;

    private BankLog()
    {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * log 开关
     * @param isdebug
     */
    public static void setLogEnable(boolean isdebug){
        debug = isdebug;
    }

    //设置tag
    public static void setLog(String tag){
        TAG = tag;
    }

    public static void v(String tag, String msg) {
        if(debug){
            Log.v(tag,msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if(debug){
            Log.v(tag,msg,tr);
        }
    }

    public static void d(String msg) {
        if(debug){
            Log.d(TAG,msg);
        }
    }

    public static void d(String tag, String msg) {
        if(debug){
            Log.d(tag,msg);
        }
    }


    public static void d(String tag, String msg, Throwable tr) {
        if(debug){
            Log.d(tag,msg,tr);
        }
    }

    public static void i(String msg) {
        if(debug){
            Log.i(TAG,msg);
        }
    }

    public static void i(String tag, String msg) {
        if(debug){
            Log.i(tag,msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if(debug){
            Log.i(tag,msg,tr);
        }
    }

    public static void w(String tag, String msg) {
        if(debug){
            Log.w(tag,msg);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if(debug){
            Log.w(tag,msg,tr);
        }
    }

    public static void w(String tag, Throwable tr) {
        if(debug){
            Log.w(tag,tr);
        }
    }

    public static void e(String msg) {
        if (debug) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if(debug){
            Log.e(tag,msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if(debug){
            Log.e(tag,msg,tr);
        }
    }
    public static String getStackTraceString(Throwable tr) {

        if(debug){
            return Log.getStackTraceString(tr);
        }else{
            return "";
        }
    }
}
