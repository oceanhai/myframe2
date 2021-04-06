package com.wuhai.demo.lotteryticketkotlin.presenter

import android.app.Dialog
import com.trello.rxlifecycle.LifecycleTransformer
import com.wuhai.demo.lotteryticketkotlin.config.network.*
import com.wuhai.demo.lotteryticketkotlin.contract.IHomeContract
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryQueryEntity
import com.wuhai.demo.lotteryticketkotlin.presenter.base.BasePresenter
import com.wuhai.retrofit.utils.LogUtil

/**
 * Created by wuhai on 2018/7/12.
 * 首页 P
 */
class HomePresenter(mView: IHomeContract.View?) : BasePresenter(), IHomeContract.Presenter {
    private var mView: IHomeContract.View?
    private val serviceProvider: ServiceProvider
    override fun onDestroy() {
        mView = null
    }

    override fun lotteryQuerySsq(key: String?, lottery_id: String?, lottery_no: String?) {
        serviceProvider.lotteryQuery2(key, lottery_id, lottery_no,
                object : RequestNetCallBack2<RootResponse<*>?>() {

                    override fun onFailure(responseError: ResponseError?) {
                        //TODO 自行封装 或者重写直接用父类  根据情况吧
                    }

                    override fun showLoadingDialog() {
                        mView?.showLoading()
                    }

                    override fun disMissLoading() {
                        mView?.dimssLoading()
                    }

                    override fun onSuccess(dataResponse: RootResponse<*>?) {
                        if (dataResponse?.error_code === 0) {
                            mView?.setLotteryQuerySsq(dataResponse?.result as LotteryQueryEntity?)
                        } else {
                            LogUtil.e(TAG, "errorMsg=" + dataResponse?.reason)
                        }
                    }
                }, mView?.lifecycleTransformer as LifecycleTransformer<RootResponse<*>>?)
    }

    override fun lotteryQueryDlt(key: String?, lottery_id: String?, lottery_no: String?) {
        serviceProvider.lotteryQuery(key, lottery_id, lottery_no,
                object : RequestNetCallBack<RootResponse<*>?>() {

                    override fun onFailure(responseError: ResponseError?) {
                        //TODO 自行封装 或者重写直接用父类  根据情况吧
                    }

                    override fun baseGetLoadingDialog(): Dialog? {
                        return null
                    }

                    override fun onSuccess(dataResponse: RootResponse<*>?) {
                        if (dataResponse?.error_code === 0) {
                            mView?.setLotteryQueryDlt(dataResponse.result as LotteryQueryEntity?)
                        } else {
                            LogUtil.e(TAG, "errorMsg=" + dataResponse?.reason)
                        }
                    }
                }, mView?.lifecycleTransformer as LifecycleTransformer<RootResponse<*>>?)
    }

    init {
        this.mView = mView
        serviceProvider = ServiceProvider()
    }
}