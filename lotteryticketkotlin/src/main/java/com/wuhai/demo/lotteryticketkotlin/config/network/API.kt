package com.wuhai.demo.lotteryticketkotlin.config.network

import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryHistoryEntity
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryQueryEntity
import retrofit2.http.GET
import retrofit2.http.QueryMap
import rx.Observable

/**
 * Created by wuhai on 2018/5/14.
 */
interface API {
    //彩票开奖结果查询--------------------------------------------------------------------------------
    /**
     * 支持彩票列表
     * @param map
     * @return
     */
    //    @GET("lottery/types")
    //    Observable<RootResponse<LotteryQueryEntity>> lotteryQuery(@QueryMap Map<String, String> map);
    /**
     * 开奖结果查询
     * @param map
     * @return
     */
    @GET("lottery/query")
    fun lotteryQuery(@QueryMap map: Map<String?, String?>?): Observable<RootResponse<LotteryQueryEntity?>?>?

    /**
     * 历史开奖结果查询
     * @param map
     * @return
     */
    @GET("lottery/history")
    fun lotteryHistory(@QueryMap map: Map<String?, String?>?): Observable<RootResponse<LotteryHistoryEntity?>?>?
}