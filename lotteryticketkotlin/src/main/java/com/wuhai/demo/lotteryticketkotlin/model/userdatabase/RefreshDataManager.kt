package com.wuhai.demo.lotteryticketkotlin.model.userdatabase

import android.database.sqlite.SQLiteDatabase
import com.wuhai.demo.lotteryticketkotlin.utils.LogProxy.e

/**
 * Created by wuhai on 2017/09/19 10:00.
 * 描述：数据库升级带来的一些刷数据问题
 */
object RefreshDataManager {
    /**
     * 刷数据
     */
    fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        e(LotteryDbHelper.TAG, "RefreshDataManager oldVersion=$oldVersion,newVersion=$newVersion")
        val sql = ""
        for (i in oldVersion until newVersion) {
            when (i) {
                2 -> {
                }
            }
        }
    }
}