package com.wuhai.lotteryticket.contract;


import com.wuhai.lotteryticket.contract.base.IBasePresenterInterFace;
import com.wuhai.lotteryticket.contract.base.INormalRxLifecycleViewInterFace;
import com.wuhai.lotteryticket.model.bean.LotteryQueryEntity;

/**
 * 作者 wuhai
 *
 * 创建日期 2018/7/12 9:41
 *
 * 描述：
 */
public interface IHomeContract {

    interface View extends INormalRxLifecycleViewInterFace {

        /**
         * 双色球结果
         */
        void setLotteryQuerySsq(LotteryQueryEntity entity);

        /**
         * 超级大乐透 结果
         */
        void setLotteryQueryDlt(LotteryQueryEntity entity);
    }

    interface Presenter extends IBasePresenterInterFace {

        /**
         * 双色球
         */
        void lotteryQuerySsq(String key, String lottery_id, String lottery_no);

        /**
         * 超级大乐透
         */
        void lotteryQueryDlt(String key, String lottery_id, String lottery_no);
    }

}
