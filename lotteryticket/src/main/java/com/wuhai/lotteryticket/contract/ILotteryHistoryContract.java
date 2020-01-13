package com.wuhai.lotteryticket.contract;


import com.wuhai.lotteryticket.contract.base.IBasePresenterInterFace;
import com.wuhai.lotteryticket.contract.base.INormalRxLifecycleViewInterFace;
import com.wuhai.lotteryticket.model.bean.LotteryHistoryEntity;

/**
 * 作者 wuhai
 *
 * 创建日期 2018/7/12 9:41
 *
 * 描述：
 */
public interface ILotteryHistoryContract {

    interface View extends INormalRxLifecycleViewInterFace {

        /**
         * 历史开奖结果查询
         */
        void setLotteryHistory(LotteryHistoryEntity entity);

    }

    interface Presenter extends IBasePresenterInterFace {

        /**
         * 历史开奖结果查询
         */
        void lotteryHistory(String lottery_id, int page, int page_size);

    }

}
