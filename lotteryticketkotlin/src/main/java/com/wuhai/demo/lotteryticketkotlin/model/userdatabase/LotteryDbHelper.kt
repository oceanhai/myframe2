package com.wuhai.demo.lotteryticketkotlin.model.userdatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.wuhai.demo.lotteryticketkotlin.model.dao.DaoMaster
import com.wuhai.demo.lotteryticketkotlin.model.dao.LotteryDao
import com.wuhai.demo.lotteryticketkotlin.model.dao.LotteryRecordDao
import com.wuhai.demo.lotteryticketkotlin.utils.DateUtils
import com.wuhai.demo.lotteryticketkotlin.utils.LogProxy.e
import org.greenrobot.greendao.database.Database

/**
 * Created by wangC on 2017/4/27.
 */
/**
 * 数据库升级类
 */
class LotteryDbHelper : DaoMaster.OpenHelper {
    constructor(context: Context?, name: String?) : super(context, name) {}
    constructor(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?) : super(context, name, factory) {
        e(TAG, "  SCHEMA_VERSION=" + DaoMaster.SCHEMA_VERSION)
    }

    override fun onCreate(db: Database) {
        super.onCreate(db)
        e(TAG, "  SCHEMA_VERSION=" + DaoMaster.SCHEMA_VERSION)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        e(TAG, "开始升级数据库 " + DateUtils.dateAllString)
        e(TAG, "Upgrading schema from version $oldVersion to $newVersion by dropping all tables")
        MigrationHelper.migrate(db,
                LotteryDao::class.java,
                LotteryRecordDao::class.java
        )
        e(TAG, " 结束升级数据库 " + DateUtils.dateAllString)
        RefreshDataManager.onUpgrade(db, oldVersion, newVersion) //升级之后，必要的刷数据操作
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
        e(TAG, "onDowngrade  旧版本$oldVersion    oldVersion   $oldVersion  by dropping all tables")
    }

    companion object {
        const val TAG = "db_lottery"
    }
}