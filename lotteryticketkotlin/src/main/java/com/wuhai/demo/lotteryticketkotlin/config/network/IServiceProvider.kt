package com.wuhai.demo.lotteryticketkotlin.config.network

import com.trello.rxlifecycle.LifecycleTransformer
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryHistoryEntity
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryQueryEntity

/**
 * Created by wuhai on 2018/5/14.
 */
interface IServiceProvider {
    /**
     * 彩票开奖结果查询 retrofit + rxJava + RxLifecycle
     * @param key           申请数据自动分配
     * @param lottery_id    彩票ID
     * @param lottery_no    彩票期号，默认最新一期
     * @param callBack
     * @param lifecycleTransformer
     */
    fun lotteryQuery(key: String?, lottery_id: String?, lottery_no: String?,
                     callBack: RequestNetCallBack<RootResponse<*>?>?,
                     lifecycleTransformer: LifecycleTransformer<RootResponse<*>>?)

    /**
     * 彩票开奖结果查询 retrofit + rxJava + RxLifecycle   TODO RequestNetCallBack2
     * @param key           申请数据自动分配
     * @param lottery_id    彩票ID
     * @param lottery_no    彩票期号，默认最新一期
     * @param callBack
     * @param lifecycleTransformer
     */
    fun lotteryQuery2(key: String?, lottery_id: String?, lottery_no: String?,
                      callBack: RequestNetCallBack2<RootResponse<*>?>,
                      lifecycleTransformer: LifecycleTransformer<RootResponse<*>>?)

    /**
     * 历史开奖结果查询
     * @param lottery_id               彩票ID
     * @param page
     * @param page_size
     * @param callBack
     * @param lifecycleTransformer
     */
    fun lotteryHistory(lottery_id: String?, page: Int, page_size: Int,
                       callBack: RequestNetCallBack2<RootResponse<*>?>?,
                       lifecycleTransformer: LifecycleTransformer<RootResponse<*>>?)
}