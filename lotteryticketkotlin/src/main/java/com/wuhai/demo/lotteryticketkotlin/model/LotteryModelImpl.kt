package com.wuhai.demo.lotteryticketkotlin.model

import com.wuhai.demo.lotteryticketkotlin.model.bean.Lottery
import com.wuhai.demo.lotteryticketkotlin.model.dao.LotteryDao
import com.wuhai.demo.lotteryticketkotlin.model.interfaces.ILotteryModel
import com.wuhai.demo.lotteryticketkotlin.model.userdatabase.LotteryDbManager
import com.wuhai.demo.lotteryticketkotlin.utils.LogProxy.e
import java.util.*

/**
 * Created by wuhai on 2017/4/6 0006 9:59.
 * 描述：彩票
 */
class LotteryModelImpl : BaseModel(), ILotteryModel {
    private var mLotteryDao: LotteryDao? = null
    override fun addLottery(lottery: Lottery?) {
        if (mLotteryDao == null) {
            mLotteryDao = LotteryDbManager.mInstance?.session?.lotteryDao
        }
        mLotteryDao!!.detachAll() //TODO get时候不取缓存的数据
        try {
            if (lottery != null) {
                val result = mLotteryDao!!.insert(lottery)
                e(mClassName, "成功 addLottery result=$result")
            }
        } catch (e: Exception) {
            //TODO 异常情况
        } finally {
            //TODO 异常情况数据处理，eg:信息插入db
        }
    }

    override fun queryLotteryAll(): List<Lottery?>? {
        if (mLotteryDao == null) {
            mLotteryDao = LotteryDbManager.mInstance?.session?.lotteryDao
        }
        mLotteryDao!!.detachAll()
        var list: List<Lottery?> = ArrayList()
        try {
            list = mLotteryDao!!.queryBuilder().orderDesc(LotteryDao.Properties.Create_time).list()
            e(mClassName, "成功 queryLotteryAll list=$list")
        } catch (e: Exception) {
            //TODO 异常情况
        } finally {
            //TODO 异常情况数据处理，eg:信息插入db
        }
        return list
    }

    override fun deleteLottery(lottery: Lottery?) {
        if (mLotteryDao == null) {
            mLotteryDao = LotteryDbManager.mInstance?.session?.lotteryDao
        }
        mLotteryDao!!.detachAll() //TODO get时候不取缓存的数据
        try {
            if (lottery != null) {
                mLotteryDao!!.delete(lottery)
                e(mClassName, "删除成功 deleteLottery")
            }
        } catch (e: Exception) {
            //TODO 异常情况
        } finally {
            //TODO 异常情况数据处理，eg:信息插入db
        }
    }
}