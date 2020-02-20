package com.wuhai.lotteryticket.model;

import com.wuhai.lotteryticket.model.bean.LotteryRecord;
import com.wuhai.lotteryticket.model.dao.LotteryRecordDao;
import com.wuhai.lotteryticket.model.interfaces.ILotteryRecordModel;
import com.wuhai.lotteryticket.model.userdatabase.LotteryDbManager;
import com.wuhai.lotteryticket.utils.LogProxy;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wuhai on 2017/4/6 0006 9:59.
 * 描述：彩票
 */
public class LotteryRecordModelImpl extends BaseModel implements ILotteryRecordModel {

    private LotteryRecordDao mLotteryRecordDao;

    @Override
    public void addLotteryRecord(LotteryRecord lotteryRecord) {
        if (mLotteryRecordDao == null) {
            mLotteryRecordDao = LotteryDbManager.getInstance().getSession().getLotteryRecordDao();
        }
        mLotteryRecordDao.detachAll();//TODO get时候不取缓存的数据
        try {
            if (lotteryRecord != null) {
                long result = mLotteryRecordDao.insert(lotteryRecord);
                LogProxy.e(mClassName, "成功 addLotteryRecord result="+result);
            }
        } catch (Exception e) {
            //TODO 异常情况
        } finally {
            //TODO 异常情况数据处理，eg:信息插入db
        }
    }

    @Override
    public List<LotteryRecord> queryLotteryRecordAll() {
        if (mLotteryRecordDao == null) {
            mLotteryRecordDao = LotteryDbManager.getInstance().getSession().getLotteryRecordDao();
        }
        mLotteryRecordDao.detachAll();
        List<LotteryRecord> list = new ArrayList<>();
        try {
            list = mLotteryRecordDao.queryBuilder().orderDesc(LotteryRecordDao.Properties.Create_time).list();
            LogProxy.e(mClassName, "成功 queryLotteryAll list="+list);
        } catch (Exception e) {
            //TODO 异常情况
        } finally {
            //TODO 异常情况数据处理，eg:信息插入db
        }
        return list;
    }

    @Override
    public List<LotteryRecord> queryLotteryRecord(int page, int maxInPage) {
        if (mLotteryRecordDao == null) {
            mLotteryRecordDao = LotteryDbManager.getInstance().getSession().getLotteryRecordDao();
        }
        mLotteryRecordDao.detachAll();
        List<LotteryRecord> list = new ArrayList<>();
        QueryBuilder queryBuilder = null;
        try {
            queryBuilder = mLotteryRecordDao.queryBuilder().orderDesc(LotteryRecordDao.Properties.Create_time);
            list = queryBuilder.offset((page - 1) * maxInPage).limit(maxInPage).list();
            LogProxy.e(mClassName, "成功 queryLotteryRecord page="+page+",list="+list);
        } catch (Exception e) {
            //TODO 异常情况
        } finally {
            //TODO 异常情况数据处理，eg:信息插入db
        }
        return list;
    }

}
