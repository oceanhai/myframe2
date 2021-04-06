package com.wuhai.demo.lotteryticketkotlin.utils

import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

/**
 * 作者 wh
 *
 * 创建日期 2020/1/9 19:54
 *
 * 描述：日期的一些转换方法
 */
object DateUtil {
    const val TAG = "DateUtil"
    private val calendar = GregorianCalendar()

    // 提供“yyyy-mm-dd”形式的字符串到毫秒的转换
    fun getMillis(dateString: String): Long {
        val date = dateString.split("-".toRegex()).toTypedArray()
//        LogProxy.e(TAG, Arrays.toString(date))
        return getMillis(date[0], date[1], date[2])
    }

    // 根据输入的年、月、日，转换成毫秒表示的时间
    fun getMillis(year: Int, month: Int, day: Int): Long {
        val calendar = GregorianCalendar(year, month, day)
        return calendar.timeInMillis
    }

    // 根据输入的年、月、日，转换成毫秒表示的时间
    fun getMillis(yearString: String, monthString: String,
                  dayString: String): Long {
        val year = yearString.toInt()
        val month = monthString.toInt() - 1
        val day = dayString.toInt()
        return getMillis(year, month, day)
    }

    // 获得当前时间的毫秒表示
    val now: Long
        get() {
            val now = GregorianCalendar()
            return now.timeInMillis
        }

    /**
     * 根据输入的毫秒数，获得日期字符串
     *
     * TODO 坑啊 java里可以正确转 Android里不行呢貌似
     * getDateInstance() 里面要设置格式 (DateFormat.MEDIUM, Locale.CHINESE)  XXXX-XX-XX
     * 默认会返回 XXXX年XX月XX日
     *
     * TODO 只能用以前的方法 SimpleDateFormat 了  /(ㄒoㄒ)/~~
     *
     * @param millis
     * @return
     */
    fun getDate(millis: Long): String {
//        calendar.setTimeInMillis(millis);
//        return DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CHINESE).format(calendar.getTime());
        val sformat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return if (millis == 0L) {
            sformat.format(Date(System.currentTimeMillis()))
        } else sformat.format(Date(millis))
    }

    // 根据输入的毫秒数，获得年份
    fun getYear(millis: Long): Int {
        calendar.timeInMillis = millis
        return calendar[Calendar.YEAR]
    }

    // 根据输入的毫秒数，获得月份
    fun getMonth(millis: Long): Int {
        calendar.timeInMillis = millis
        return calendar[Calendar.MONTH]
    }

    // 根据输入的毫秒数，获得日期
    fun getDay(millis: Long): Int {
        calendar.timeInMillis = millis
        return calendar[Calendar.DATE]
    }

    // 根据输入的毫秒数，获得小时
    fun getHour(millis: Long): Int {
        calendar.timeInMillis = millis
        return calendar[Calendar.HOUR_OF_DAY]
    }

    // 根据输入的毫秒数，获得分钟
    fun getMinute(millis: Long): Int {
        calendar.timeInMillis = millis
        return calendar[Calendar.MINUTE]
    }

    // 根据输入的毫秒数，获得秒
    fun getSecond(millis: Long): Int {
        calendar.timeInMillis = millis
        return calendar[Calendar.SECOND]
    }

    //获取指定毫秒数的对应星期
    fun getWeek(millis: Long): String {
        calendar.timeInMillis = millis
        var week = ""
        val cweek = calendar[Calendar.DAY_OF_WEEK]
        when (cweek) {
            1 -> week = "日"
            2 -> week = "一"
            3 -> week = "二"
            4 -> week = "三"
            5 -> week = "四"
            6 -> week = "五"
            7 -> week = "六"
        }
        return week
    }

    // 获得今天日期
    val todayData: String
        get() = getDate(now)// 86400000为一天的毫秒数

    // 获得明天日期
    val tomoData: String
        get() =// 86400000为一天的毫秒数
            getDate(now + 86400000)

    // 获得后天日期
    val theDayData: String
        get() = getDate(now + 86400000 + 86400000)

    // 获得昨天日期
    val yesData: String
        get() = getDate(now - 86400000L)

    // 获得前天日期
    val beforeYesData: String
        get() = getDate(now - 86400000L - 86400000L)

    /**
     * 获取今天时间具体内容
     * @return
     */
    fun StringData(): String {
        val c = Calendar.getInstance()
        c.timeZone = TimeZone.getTimeZone("GMT+8:00")
        val mYear = c[Calendar.YEAR].toString() // 获取当前年份
        val mMonth = (c[Calendar.MONTH] + 1).toString() // 获取当前月份
        val mDay = c[Calendar.DAY_OF_MONTH].toString() // 获取当前月份的日期号码
        var mWay = c[Calendar.DAY_OF_WEEK].toString()
        if ("1" == mWay) {
            mWay = "天"
        } else if ("2" == mWay) {
            mWay = "一"
        } else if ("3" == mWay) {
            mWay = "二"
        } else if ("4" == mWay) {
            mWay = "三"
        } else if ("5" == mWay) {
            mWay = "四"
        } else if ("6" == mWay) {
            mWay = "五"
        } else if ("7" == mWay) {
            mWay = "六"
        }
        return mYear + "年" + mMonth + "月" + mDay + "日" + " 星期" + mWay
    }

    /**
     * 获取类似今天、昨天的简单名称
     * @param date 格式为 20xx-xx-xx
     * @return
     */
    fun getCustomStr(date: String): String {
        return if (getMillis(date) == getMillis(beforeYesData)) {
            "前天"
        } else if (getMillis(date) == getMillis(yesData)) {
            "昨天"
        } else if (getMillis(date) == getMillis(todayData)) {
            "今天"
        } else if (getMillis(date) == getMillis(tomoData)) {
            "明天"
        } else if (getMillis(date) == getMillis(theDayData)) {
            "后天"
        } else {
            "星期" + getWeek(getMillis(date))
        }
    }
}