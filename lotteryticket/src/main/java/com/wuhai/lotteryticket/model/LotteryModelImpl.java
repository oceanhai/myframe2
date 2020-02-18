package com.wuhai.lotteryticket.model;

import com.wuhai.lotteryticket.model.bean.Lottery;
import com.wuhai.lotteryticket.model.dao.LotteryDao;
import com.wuhai.lotteryticket.model.interfaces.ILotteryModel;
import com.wuhai.lotteryticket.model.userdatabase.LotteryDbManager;
import com.wuhai.lotteryticket.utils.LogProxy;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wuhai on 2017/4/6 0006 9:59.
 * 描述：彩票
 */
public class LotteryModelImpl extends BaseModel implements ILotteryModel {

    private LotteryDao mLotteryDao;

    @Override
    public void addLottery(Lottery lottery) {
        if (mLotteryDao == null) {
            mLotteryDao = LotteryDbManager.getInstance().getSession().getLotteryDao();
        }
        mLotteryDao.detachAll();//TODO get时候不取缓存的数据
        try {
            if (lottery != null) {
                long result = mLotteryDao.insert(lottery);
                LogProxy.e(mClassName, "成功 addLottery result="+result);
            }
        } catch (Exception e) {
            //TODO 异常情况
        } finally {
            //TODO 异常情况数据处理，eg:信息插入db
        }
    }

    @Override
    public List<Lottery> queryLotteryAll() {
        if (mLotteryDao == null) {
            mLotteryDao = LotteryDbManager.getInstance().getSession().getLotteryDao();
        }
        mLotteryDao.detachAll();
        List<Lottery> list = new ArrayList<>();
        try {
            list = mLotteryDao.queryBuilder().orderDesc(LotteryDao.Properties.Create_time).list();
            LogProxy.e(mClassName, "成功 queryLotteryAll list="+list);
        } catch (Exception e) {
            //TODO 异常情况
        } finally {
            //TODO 异常情况数据处理，eg:信息插入db
        }
        return list;
    }
}
