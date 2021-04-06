package com.wuhai.demo.lotteryticketkotlin.utils

import com.wuhai.demo.lotteryticketkotlin.utils.MatcherUtils.isAllNumber
import java.math.BigDecimal

object MonetaryUnitUtil {
    /**
     * <pre>
     * 数字格式化显示
     * 小于万默认显示 大于万以1.7万方式显示最大是9999.9万
     * 大于亿以1.1亿方式显示最大没有限制都是亿单位
    </pre> *
     * @param num
     * 格式化的数字
     * @param kBool
     * 是否格式化千,为true,并且num大于999就显示999+,小于等于999就正常显示
     * @return
     */
    fun formatNum(num: String, kBool: Boolean?): String {
        var kBool = kBool
        val sb = StringBuffer()
        if (!isAllNumber(num)) return "0"
        if (kBool == null) kBool = false
        val b0 = BigDecimal("1000")
        val b1 = BigDecimal("10000")
        val b2 = BigDecimal("100000000")
        val b3 = BigDecimal(num)
        var formatNumStr = ""
        var nuit = ""

        // 以千为单位处理
        if (kBool) {
            return if (b3.compareTo(b0) == 0 || b3.compareTo(b0) == 1) {
                "999+"
            } else num
        }

        // 以万为单位处理
        if (b3.compareTo(b1) == -1) {
            sb.append(b3.toString())
        } else if (b3.compareTo(b1) == 0 && b3.compareTo(b1) == 1
                || b3.compareTo(b2) == -1) {
            formatNumStr = b3.divide(b1).toString()
            nuit = "万"
        } else if (b3.compareTo(b2) == 0 || b3.compareTo(b2) == 1) {
            formatNumStr = b3.divide(b2).toString()
            nuit = "亿"
        }
        if ("" != formatNumStr) {
            var i = formatNumStr.indexOf(".")
            if (i == -1) {
                sb.append(formatNumStr).append(nuit)
            } else {
                i = i + 1
                val v = formatNumStr.substring(i, i + 1)
                if (v != "0") {
                    sb.append(formatNumStr.substring(0, i + 1)).append(nuit)
                } else {
                    sb.append(formatNumStr.substring(0, i - 1)).append(nuit)
                }
            }
        }
        return if (sb.length == 0) "0" else sb.toString()
    }
}