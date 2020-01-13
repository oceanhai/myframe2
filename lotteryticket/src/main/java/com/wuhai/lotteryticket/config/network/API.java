package com.wuhai.lotteryticket.config.network;


import com.wuhai.lotteryticket.model.bean.LotteryHistoryEntity;
import com.wuhai.lotteryticket.model.bean.LotteryQueryEntity;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by wuhai on 2018/5/14.
 */

public interface API {

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
    Observable<RootResponse<LotteryQueryEntity>> lotteryQuery(@QueryMap Map<String, String> map);


    /**
     * 历史开奖结果查询
     * @param map
     * @return
     */
    @GET("lottery/history")
    Observable<RootResponse<LotteryHistoryEntity>> lotteryHistory(@QueryMap Map<String, String> map);
}
