package com.wuhai.demo.lotteryticketkotlin.config.network

import android.util.Log
import com.trello.rxlifecycle.LifecycleTransformer
import com.wuhai.demo.lotteryticketkotlin.config.Constants
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryHistoryEntity
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryQueryEntity
import com.wuhai.retrofit.retrofit.RxUtil

/**
 * Created by wuhai on 2018/5/16.
 */
class ServiceProvider : APIBaseService(), IServiceProvider {
    override fun lotteryQuery(key: String?, lottery_id: String?, lottery_no: String?, callBack: RequestNetCallBack<RootResponse<*>?>?, lifecycleTransformer: LifecycleTransformer<RootResponse<*>>?) {
        Log.i(TAG, "<------ make lotteryQuery request ------>")
        val getApiParams = ApiParams(ApiParams.Method.GET_PARMS)
                .with("key", key)
                .with("lottery_id", lottery_id)
                .with("lottery_no", lottery_no)
                .print()
        val observable = api.lotteryQuery(getApiParams)
        observable!!.compose(RxUtil.normalSchedulers<RootResponse<*>?>())
                .compose(lifecycleTransformer)
                .subscribe(callBack)
    }

    override fun lotteryQuery2(key: String?, lottery_id: String?, lottery_no: String?, callBack: RequestNetCallBack2<RootResponse<*>?>, lifecycleTransformer: LifecycleTransformer<RootResponse<*>>?) {
        Log.i(TAG, "<------ make lotteryQuery request ------>")
        val getApiParams = ApiParams(ApiParams.Method.GET_PARMS)
                .with("key", key)
                .with("lottery_id", lottery_id)
                .with("lottery_no", lottery_no)
                .print()
        val observable = api.lotteryQuery(getApiParams)
        observable!!.compose(RxUtil.normalSchedulers<RootResponse<*>?>())
                .compose(lifecycleTransformer)
                .subscribe(callBack)
    }

    override fun lotteryHistory(lottery_id: String?, page: Int, page_size: Int, callBack: RequestNetCallBack2<RootResponse<*>?>?, lifecycleTransformer: LifecycleTransformer<RootResponse<*>>?) {
        Log.i(TAG, "<------ make lotteryQuery request ------>")
        val getApiParams = ApiParams(ApiParams.Method.GET_PARMS)
                .juhe(Constants.JUHE_LOTTERY_KEY)
                .with("lottery_id", lottery_id)
                .with("page", "" + page)
                .with("page_size", "" + page_size)
                .print()
        val observable = api.lotteryHistory(getApiParams)
        observable!!.compose(RxUtil.normalSchedulers<RootResponse<*>?>())
                .compose(lifecycleTransformer)
                .subscribe(callBack)
    }

    companion object {
        const val TAG = "qjj_network"
    }
}