package com.wuhai.demo.lotteryticketkotlin.utils

import android.util.Log
import com.wuhai.demo.lotteryticketkotlin.config.network.APIConstants

/**
 * 打印log
 */
object LogProxy {
    private const val LOG_UNSUPPORT_VALUE = -1
    fun v(tag: String?, msg: String?): Int {
        return if (APIConstants.DEBUG) {
            Log.v(tag, msg!!)
        } else {
            LOG_UNSUPPORT_VALUE
        }
    }

    fun v(tag: String?, msg: String?, tr: Throwable?): Int {
        return if (APIConstants.DEBUG) {
            Log.v(tag, msg, tr)
        } else {
            LOG_UNSUPPORT_VALUE
        }
    }

    fun d(tag: String?, msg: String?): Int {
        return if (APIConstants.DEBUG) {
            Log.d(tag, msg!!)
        } else {
            LOG_UNSUPPORT_VALUE
        }
    }

    fun d(tag: String?, msg: String?, tr: Throwable?): Int {
        return if (APIConstants.DEBUG) {
            Log.d(tag, msg, tr)
        } else {
            LOG_UNSUPPORT_VALUE
        }
    }

    fun i(tag: String?, msg: String?): Int {
        return if (APIConstants.DEBUG) {
            Log.i(tag, msg!!)
        } else {
            LOG_UNSUPPORT_VALUE
        }
    }

    fun i(tag: String?, msg: String?, tr: Throwable?): Int {
        return if (APIConstants.DEBUG) {
            Log.i(tag, msg, tr)
        } else {
            LOG_UNSUPPORT_VALUE
        }
    }

    fun w(tag: String?, msg: String?): Int {
        return if (APIConstants.DEBUG) {
            Log.w(tag, msg!!)
        } else {
            LOG_UNSUPPORT_VALUE
        }
    }

    fun w(tag: String?, msg: String?, tr: Throwable?): Int {
        return if (APIConstants.DEBUG) {
            Log.w(tag, msg, tr)
        } else {
            LOG_UNSUPPORT_VALUE
        }
    }

    fun w(tag: String?, tr: Throwable?): Int {
        return if (APIConstants.DEBUG) {
            Log.w(tag, tr)
        } else {
            LOG_UNSUPPORT_VALUE
        }
    }

    fun e(tag: String?, msg: String?): Int {
        return if (APIConstants.DEBUG) {
            Log.e(tag, msg!!)
        } else {
            LOG_UNSUPPORT_VALUE
        }
    }

    fun e(tag: String?, msg: String?, tr: Throwable?): Int {
        return if (APIConstants.DEBUG) {
            Log.e(tag, msg, tr)
        } else {
            LOG_UNSUPPORT_VALUE
        }
    }

    fun getStackTraceString(tr: Throwable?): String {
        return if (APIConstants.DEBUG) {
            Log.getStackTraceString(tr)
        } else {
            ""
        }
    }
}