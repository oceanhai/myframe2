package com.wuhai.demo.lotteryticketkotlin.utils

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import com.wuhai.demo.lotteryticketkotlin.utils.DateUtils.dateAllMsecString
import com.wuhai.demo.lotteryticketkotlin.utils.DateUtils.getDateString
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import java.util.regex.Pattern

/**
 *
 * 字符串工具类
 *
 */
object StringUtil {
    /**
     * 格式化数据，保留指定的位数
     *
     * @param flt
     * @param count
     * 小数点后位数
     * @return
     */
    fun formatFloatStr(flt: String, count: Int): String {
        if (TextUtils.isEmpty(flt)) {
            if (count <= 0) {
                return "0"
            }
            val sb = StringBuffer("0.")
            for (i in 0 until count) {
                sb.append("0")
            }
            return sb.toString()
        }
        val sb = StringBuffer("0.00000")
        val df = DecimalFormat(sb.toString()) // " "内写格式的模式
        // 如保留2位就用"0.00"即可
        val format = df.format(flt.toFloat().toDouble())
        return format.substring(0, format.indexOf(".") + count + 1)
    }

    /**
     * 格式化钱币，每三位加一逗号
     *
     * @param money
     * @return
     */
    fun formatMoney(money: String?, count: Int): String {
        // setMaximumFractionDigits(int) 设置数值的小数部分允许的最大位数。
        // setMaximumIntegerDigits(int) 设置数值的整数部分允许的最大位数。
        // setMinimumFractionDigits(int) 设置数值的小数部分允许的最小位数。
        // setMinimumIntegerDigits(int) 设置数值的整数部分允许的最小位数.
        val format = NumberFormat.getInstance()
        format.minimumFractionDigits = count
        format.maximumFractionDigits = count
        format.maximumIntegerDigits = 20
        format.minimumIntegerDigits = 1
        return format.format(BigDecimal(money))
        // return format.format(Float.parseFloat(money));
    }

    fun addColorAndSize(textView: TextView, start: Int, end: Int, color: Int, fontSize: Int) {
        val text = textView.text.toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(text) || start > end) {
            return
        }
        val builder = SpannableStringBuilder(text)
        if (color != 0) {
            builder.setSpan(ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        if (fontSize > 0) {
            builder.setSpan(AbsoluteSizeSpan(fontSize, true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        textView.text = builder
    }

    /**
     * 单位
     * @param money
     * @param unit
     * @return
     */
    fun formatMoneyUnit(money: Int, unit: Int): String {
        var result = ""
        if (unit == 10000) {
            result = (money / unit).toString() + "W"
        }
        return result
    }

    /**
     * double 类型 截取小数点后位数
     * @param format
     * @param num
     * @return
     */
    fun getDecimalFormat(format: String?, num: Double): String {
        var df: DecimalFormat? = null
        df = if (TextUtils.isEmpty(format)) {
            DecimalFormat("######0.00")
        } else {
            DecimalFormat(format)
        }
        df.roundingMode = RoundingMode.HALF_UP
        return df.format(num)
    }

    /**
     * 处理后的电话号码
     * @param phone
     * @return    eg:177****9269
     */
    fun getPhoneStr(phone: String): String? {
        if (TextUtils.isEmpty(phone)) {
            return null
        }
        return if (phone.length != 11) {
            null
        } else phone.substring(0, 3) + "****" + phone.substring(8)
    }

    /**
     * 返回长度为【strLength】的随机数
     * 最大16位
     */
    fun getFixLenthString(strLength: Int): String {
        val rm = Random()

        // 获得随机数
        val pross = (1 + rm.nextDouble()) * Math.pow(10.0, strLength.toDouble())

        // 将获得的获得随机数转化为字符串
        val fixLenthString = pross.toString()

        // 返回固定的长度的随机数
        return fixLenthString.substring(2, strLength + 2)
    }

    /**
     * 获取uuid
     *
     */
    val uUid: String
        get() {
            val uuid = UUID.randomUUID()
            return uuid.toString().replace("-", "").toLowerCase()
        }

    /**
     *
     * @return
     */
    fun get17RandomString(): String {
        return dateAllMsecString.replace("-", "").replace(" ", "").replace(":".toRegex(), "")
    }

    /**
     * 获得一个随机 id
     * 订单id、退货单id、订货单id
     * @return
     */
    fun get17RandomNo(): String {
        return getDateString("yyMMddHHmm",
                System.currentTimeMillis()) + getFixLenthString(7)
    }

    /**
     * 判断 str 是否为数字
     * @param str
     * @return
     */
    fun isNumeric1(str: String): Boolean {
        for (i in 0 until str.length) {
            if (!Character.isDigit(str[i])) {
                return false
            }
        }
        return true
    }

    /**
     * 判断 str 是否为数字
     * @param str
     * @return
     */
    fun isNumeric2(str: String?): Boolean {
        val pattern = Pattern.compile("[0-9]*")
        val isNum = pattern.matcher(str)
        return if (!isNum.matches()) {
            false
        } else true
    }

    /**
     * x位，前面不足自动补零
     * @param num       位数
     * @param str       入参
     * @return
     */
    fun addZeroBefore(num: Int, str: String?): String {
        return String.format("%0" + num + "d", Integer.valueOf(str))
    }

    /**
     * x位，前面不足自动补零
     * @param num       位数
     * @param str       入参
     * @return
     */
    fun addZeroBefore2(num: Int, str: Int): String {
        return String.format("%0" + num + "d", str)
    }

    /**
     * 判断是否是会员卡
     * 99、00开头的6位、11位数字
     * @param str
     * @return
     */
    fun isMemberCard(str: String?): Boolean {
        val pattern = Pattern.compile("^(00|99)(\\d{4}|\\d{9})$")
        val isNum = pattern.matcher(str)
        return if (!isNum.matches()) {
            false
        } else true
    }

    /**
     * 判断是否是 订单no
     * 17位数字
     * @param str
     * @return
     */
    fun isOrderNo(str: String): Boolean {
        return if (str.length != 17) {
            false
        } else isNumeric2(str)
    }
}