package com.wuhai.lotteryticket.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wuhai on 2017/02/10 17:12.
 * 描述：
 */

public class DateUtils {

    private static final String TAG = "DateUtils";

    /**
     * 根据format，把当前毫秒值转换成想要的日期形式
     *
     * @param format
     * @param longTime
     * @return
     */
    public static String getDateString(String format, long longTime) {
        SimpleDateFormat sformat = null;
        if (format == null || "".equals(format)) {
            sformat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        } else {
            sformat = new SimpleDateFormat(format, Locale.getDefault());
        }
        if (longTime == 0) {
            return sformat.format(new java.sql.Date(System.currentTimeMillis()));
        }
        return sformat.format(new java.sql.Date(longTime));
    }


    /**
     * 根据format，把当前毫秒值转换成想要的日期形式
     *
     * @return
     */
    public static String getDateAllString() {
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sformat.format(new Date().getTime());
    }


    /**
     * 根据format，把当前毫秒值转换成想要的日期形式
     *
     * @return
     */
    public static String getDateYMD(String time) {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = (Date) sdf1.parse(time);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
            return sdf2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";

    }


    /**
     * 根据format，把当前毫秒值转换成想要的日期形式
     *
     * @return
     */
    public static String getDateYMDHm() {
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        return sformat.format(new Date().getTime());
    }

    /**
     * 根据format，把当前毫秒值转换成想要的日期形式
     *
     * @return
     */
    public static String getDateAllMsecString() {
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS" );
        return sformat.format(new Date().getTime());
    }

    //返回现在 的时间戳
    public static long getNow() {
        return new Date().getTime();
    }


    /**
     * 返回比现在多多少分钟的 的时间戳
     *
     * @return
     */
    public static String getSyncDate(int minute) {
        long time = new Date().getTime();
        long lastTime = time + (1000 * 60 * minute);
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Log.e(TAG,"log" + sformat.format(lastTime));
        return String.valueOf(lastTime);
    }
}
