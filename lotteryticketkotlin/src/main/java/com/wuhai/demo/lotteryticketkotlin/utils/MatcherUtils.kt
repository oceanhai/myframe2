package com.wuhai.demo.lotteryticketkotlin.utils

import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

/**
 * 作者 wh
 *
 * 创建日期 2020/1/9 21:42
 *
 * 描述：匹配器
 */
object MatcherUtils {
    fun strSplitR(str: String?): Boolean {
        if (str != null) {
            val strs = str.split("\\.".toRegex()).toTypedArray()
            return strs.size > 0 && "R" == strs[0]
        }
        return false
    }

    @Throws(PatternSyntaxException::class)
    fun isUrl(str: String?): Boolean {
        // 只允许字母和数字
        val regEx = "[a-zA-z]+://[^\\s]*"
        val p = Pattern.compile(regEx)
        val m = p.matcher(str)
        return m.matches()
    }

    /**
     * 纯数字
     * @param str
     * @return
     */
    fun isAllNumber(str: String): Boolean {
        val reg = "^\\d+$"
        return str.matches(Regex(str))
    }
}