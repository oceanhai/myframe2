package com.wuhai.demo.lotteryticketkotlin.model.userdatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.wuhai.demo.lotteryticketkotlin.application.BaseApplication
import com.wuhai.demo.lotteryticketkotlin.model.dao.DaoMaster
import com.wuhai.demo.lotteryticketkotlin.model.dao.DaoSession
import com.wuhai.demo.lotteryticketkotlin.utils.LogProxy.e
import org.greenrobot.greendao.async.AsyncSession
import org.greenrobot.greendao.query.QueryBuilder

/**
 * 作者 wh
 *
 * 创建日期 2020/2/14 16:36
 *
 * 描述：创建数据库
 */
class LotteryDbManager private constructor() {
    private val devOpenHelper: LotteryDbHelper
    var master: DaoMaster?
        private set
    var session: DaoSession?
        private set
    private val context: Context
    val asyncSession: AsyncSession

    /**
     * 获取可读数据库
     */
    private val readableDatabase: SQLiteDatabase
        private get() = devOpenHelper.readableDatabase

    /**
     * 获取可写数据库
     */
    private val writableDatabase: SQLiteDatabase
        private get() = devOpenHelper.writableDatabase

    val newSession: DaoSession?
        get() {
            session = master!!.newSession()
            return session
        }

    companion object {
        private const val dbName = "lottery.db"
        val mInstance: LotteryDbManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            LotteryDbManager()
        }

    }

    init {
        context = BaseApplication.context
        devOpenHelper = LotteryDbHelper(context, dbName, null)
        master = DaoMaster(devOpenHelper.readableDatabase)
        session = master!!.newSession()
        asyncSession = AsyncSession(session)

        //日志输出 默认false
        QueryBuilder.LOG_SQL = true
        QueryBuilder.LOG_VALUES = true
    }
}