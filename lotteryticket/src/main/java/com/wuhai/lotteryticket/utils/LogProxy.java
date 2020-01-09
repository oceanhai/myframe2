package com.wuhai.lotteryticket.utils;

import android.util.Log;

import com.wuhai.lotteryticket.config.network.APIConstants;


/**
 * 打印log
 */
public class LogProxy {

    private static int LOG_UNSUPPORT_VALUE = -1;


    private LogProxy(){}

    public static int v(String tag, String msg) {
        if(APIConstants.DEBUG){
            return Log.v(tag,msg);
        }else{
            return LOG_UNSUPPORT_VALUE;
        }
    }

    public static int v(String tag, String msg, Throwable tr) {
        if(APIConstants.DEBUG){
            return Log.v(tag,msg,tr);
        }else{
            return LOG_UNSUPPORT_VALUE;
        }
    }

    public static int d(String tag, String msg) {
        if(APIConstants.DEBUG){
            return Log.d(tag,msg);
        }else{
            return LOG_UNSUPPORT_VALUE;
        }
    }


    public static int d(String tag, String msg, Throwable tr) {
        if(APIConstants.DEBUG){
            return Log.d(tag,msg,tr);
        }else{
            return LOG_UNSUPPORT_VALUE;
        }
    }

    public static int i(String tag, String msg) {
        if(APIConstants.DEBUG){
            return Log.i(tag,msg);
        }else{
            return LOG_UNSUPPORT_VALUE;
        }
    }

    public static int i(String tag, String msg, Throwable tr) {
        if(APIConstants.DEBUG){
            return Log.i(tag,msg,tr);
        }else{
            return LOG_UNSUPPORT_VALUE;
        }
    }

    public static int w(String tag, String msg) {
        if(APIConstants.DEBUG){
            return Log.w(tag,msg);
        }else{
            return LOG_UNSUPPORT_VALUE;
        }
    }

    public static int w(String tag, String msg, Throwable tr) {
        if(APIConstants.DEBUG){
            return Log.w(tag,msg,tr);
        }else{
            return LOG_UNSUPPORT_VALUE;
        }
    }

    public static int w(String tag, Throwable tr) {
        if(APIConstants.DEBUG){
            return Log.w(tag,tr);
        }else{
            return LOG_UNSUPPORT_VALUE;
        }
    }

    public static int e(String tag, String msg) {
        if(APIConstants.DEBUG){
            return Log.e(tag,msg);
        }else{
            return LOG_UNSUPPORT_VALUE;
        }
    }

    public static int e(String tag, String msg, Throwable tr) {
        if(APIConstants.DEBUG){
            return Log.e(tag,msg,tr);
        }else{
            return LOG_UNSUPPORT_VALUE;
        }
    }
    public static String getStackTraceString(Throwable tr) {

        if(APIConstants.DEBUG){
            return Log.getStackTraceString(tr);
        }else{
            return "";
        }
    }
}