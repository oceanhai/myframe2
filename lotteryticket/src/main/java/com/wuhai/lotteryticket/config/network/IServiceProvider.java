package com.wuhai.lotteryticket.config.network;


import com.trello.rxlifecycle.LifecycleTransformer;
import com.wuhai.lotteryticket.model.bean.LotteryQueryEntity;

/**
 * Created by wuhai on 2018/5/14.
 */

public interface IServiceProvider {


    /**
     * 彩票开奖结果查询 retrofit + rxJava + RxLifecycle
     * @param key           申请数据自动分配
     * @param lottery_id    彩票ID
     * @param lottery_no    彩票期号，默认最新一期
     * @param callBack
     * @param lifecycleTransformer
     */
    void lotteryQuery(String key, String lottery_id, String lottery_no,
                      RequestNetCallBack<RootResponse<LotteryQueryEntity>> callBack,
                      LifecycleTransformer lifecycleTransformer);

    /**
     * 彩票开奖结果查询 retrofit + rxJava + RxLifecycle   TODO RequestNetCallBack2
     * @param key           申请数据自动分配
     * @param lottery_id    彩票ID
     * @param lottery_no    彩票期号，默认最新一期
     * @param callBack
     * @param lifecycleTransformer
     */
    void lotteryQuery2(String key, String lottery_id, String lottery_no,
                      RequestNetCallBack2<RootResponse<LotteryQueryEntity>> callBack,
                      LifecycleTransformer lifecycleTransformer);
}







