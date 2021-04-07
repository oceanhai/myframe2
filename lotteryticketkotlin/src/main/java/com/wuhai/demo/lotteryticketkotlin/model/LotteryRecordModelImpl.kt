package com.wuhai.demo.lotteryticketkotlin.model

import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryRecord
import com.wuhai.demo.lotteryticketkotlin.model.dao.LotteryRecordDao
import com.wuhai.demo.lotteryticketkotlin.model.interfaces.ILotteryRecordModel
import com.wuhai.demo.lotteryticketkotlin.model.userdatabase.LotteryDbManager
import com.wuhai.demo.lotteryticketkotlin.utils.LogProxy.e
import org.greenrobot.greendao.query.QueryBuilder
import java.util.*

/**
 * Created by wuhai on 2017/4/6 0006 9:59.
 * 描述：彩票
 */
class LotteryRecordModelImpl : BaseModel(), ILotteryRecordModel {
    private var mLotteryRecordDao: LotteryRecordDao? = null
    override fun addLotteryRecord(lotteryRecord: LotteryRecord?) {
        if (mLotteryRecordDao == null) {
            mLotteryRecordDao = LotteryDbManager.mInstance?.session?.lotteryRecordDao
        }
        mLotteryRecordDao!!.detachAll() //TODO get时候不取缓存的数据
        try {
            if (lotteryRecord != null) {
                val result = mLotteryRecordDao!!.insert(lotteryRecord)
                e(mClassName, "成功 addLotteryRecord result=$result")
            }
        } catch (e: Exception) {
            //TODO 异常情况
        } finally {
            //TODO 异常情况数据处理，eg:信息插入db
        }
    }

    override fun queryLotteryRecordAll(): List<LotteryRecord?>? {
        if (mLotteryRecordDao == null) {
            mLotteryRecordDao = LotteryDbManager.mInstance?.session?.lotteryRecordDao
        }
        mLotteryRecordDao!!.detachAll()
        var list: List<LotteryRecord?> = ArrayList()
        try {
            list = mLotteryRecordDao!!.queryBuilder().orderDesc(LotteryRecordDao.Properties.Create_time).list()
            e(mClassName, "成功 queryLotteryAll list=$list")
        } catch (e: Exception) {
            //TODO 异常情况
        } finally {
            //TODO 异常情况数据处理，eg:信息插入db
        }
        return list
    }

    override fun queryLotteryRecord(page: Int, maxInPage: Int): List<LotteryRecord?>? {
        if (mLotteryRecordDao == null) {
            mLotteryRecordDao = LotteryDbManager.mInstance?.session?.lotteryRecordDao
        }
        mLotteryRecordDao!!.detachAll()
        var list: List<LotteryRecord?> = ArrayList()
        var queryBuilder: QueryBuilder<*>? = null
        try {
            queryBuilder = mLotteryRecordDao!!.queryBuilder().orderDesc(LotteryRecordDao.Properties.Create_time)
            list = queryBuilder.offset((page - 1) * maxInPage).limit(maxInPage).list()
            e(mClassName, "成功 queryLotteryRecord page=$page,list=$list")
        } catch (e: Exception) {
            //TODO 异常情况
        } finally {
            //TODO 异常情况数据处理，eg:信息插入db
        }
        return list
    }
}