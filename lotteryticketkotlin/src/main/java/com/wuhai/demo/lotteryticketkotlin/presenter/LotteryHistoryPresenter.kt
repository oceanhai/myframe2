package com.wuhai.demo.lotteryticketkotlin.presenter

import com.trello.rxlifecycle.LifecycleTransformer
import com.wuhai.demo.lotteryticketkotlin.config.network.RequestNetCallBack2
import com.wuhai.demo.lotteryticketkotlin.config.network.ResponseError
import com.wuhai.demo.lotteryticketkotlin.config.network.RootResponse
import com.wuhai.demo.lotteryticketkotlin.config.network.ServiceProvider
import com.wuhai.demo.lotteryticketkotlin.contract.ILotteryHistoryContract
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryHistoryEntity
import com.wuhai.demo.lotteryticketkotlin.presenter.base.BasePresenter
import com.wuhai.retrofit.utils.LogUtil

/**
 * Created by wuhai on 2018/7/12.
 * 首页 P
 */
class LotteryHistoryPresenter(private var mView: ILotteryHistoryContract.View?) : BasePresenter(), ILotteryHistoryContract.Presenter {
    private val serviceProvider: ServiceProvider
    override fun onDestroy() {
        mView = null
    }

    override fun lotteryHistory(lottery_id: String?, page: Int, page_size: Int) {
        serviceProvider.lotteryHistory(lottery_id, page, page_size,
                object : RequestNetCallBack2<RootResponse<*>?>() {
                    override fun onSuccess(dataResponse: RootResponse<*>?) {
                        if (dataResponse?.error_code == 0) {
                            if (mView != null) {
                                mView!!.setLotteryHistory(dataResponse.result as LotteryHistoryEntity?)
                            }
                        } else {
                            LogUtil.e(TAG, "errorMsg=" + dataResponse?.reason)
                        }
                    }

                    override fun onFailure(responseError: ResponseError?) {
                        super.onFailure(responseError)
                    }

                    override fun showLoadingDialog() {
                        if (mView != null) {
                            mView!!.showLoading()
                        }
                    }

                    override fun disMissLoading() {
                        if (mView != null) {
                            mView!!.dimssLoading()
                        }
                    }

                }, mView!!.lifecycleTransformer as LifecycleTransformer<RootResponse<*>>?)
    }

    init {
        serviceProvider = ServiceProvider()
    }
}