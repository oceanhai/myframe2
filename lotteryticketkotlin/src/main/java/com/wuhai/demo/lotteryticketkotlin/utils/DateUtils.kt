package com.wuhai.demo.lotteryticketkotlin.utils

import android.util.Log
import java.sql.Date
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by wuhai on 2017/02/10 17:12.
 * 描述：
 */
object DateUtils {
    private const val TAG = "DateUtils"

    /**
     * 根据format，把当前毫秒值转换成想要的日期形式
     *
     * @param format
     * @param longTime
     * @return
     */
    fun getDateString(format: String?, longTime: Long): String {
        var sformat: SimpleDateFormat? = null
        sformat = if (format == null || "" == format) {
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        } else {
            SimpleDateFormat(format, Locale.getDefault())
        }
        return if (longTime == 0L) {
            sformat.format(Date(System.currentTimeMillis()))
        } else sformat.format(Date(longTime))
    }

    /**
     * 根据format，把当前毫秒值转换成想要的日期形式
     *
     * @return
     */
    val dateAllString: String
        get() {
            val sformat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            return sformat.format(Date().time)
        }

    /**
     * 根据format，把当前毫秒值转换成想要的日期形式
     *
     * @return
     */
    fun getDateYMD(time: String?): String {
        val sdf1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date: java.util.Date
        try {
            date = sdf1.parse(time) as java.util.Date
            val sdf2 = SimpleDateFormat("yyyy年MM月dd日")
            return sdf2.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * 根据format，把当前毫秒值转换成想要的日期形式
     *
     * @return
     */
    val dateYMDHm: String
        get() {
            val sformat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            return sformat.format(Date().time)
        }

    /**
     * 根据format，把当前毫秒值转换成想要的日期形式
     *
     * @return
     */
    val dateAllMsecString: String
        get() {
            val sformat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
            return sformat.format(Date().time)
        }

    //返回现在 的时间戳
    val now: Long
        get() = Date().time

    /**
     * 返回比现在多多少分钟的 的时间戳
     *
     * @return
     */
    fun getSyncDate(minute: Int): String {
        val time = Date().time
        val lastTime = time + 1000 * 60 * minute
        val sformat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        Log.e(TAG, "log" + sformat.format(lastTime))
        return lastTime.toString()
    }
}