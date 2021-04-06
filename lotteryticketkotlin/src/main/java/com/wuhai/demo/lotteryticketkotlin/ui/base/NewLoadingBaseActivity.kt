package com.wuhai.demo.lotteryticketkotlin.ui.base

import android.os.Bundle
import com.wuhai.demo.lotteryticketkotlin.widget.dialog.LoadingDialog

abstract class NewLoadingBaseActivity: BaseActionBarActivity() {
    var mLoading //new loading dialog
            : LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLoading = LoadingDialog(this)
    }

    /**
     * showNewLoading
     * ※跟 基类里的showLoading() 区别，用基类showLoading()，成功后必须调showSuccessLayout()
     */
    open fun showNewLoading() {
        //TODO 这样并不是java 对应的&&的逻辑呢
//        if ((mLoading != null).and(mLoading!!.isShowing)) {
//            try {
//                mLoading?.show()
//            } catch (e: Exception) {
//            }
//        }

        if(mLoading != null){
            if(mLoading!!.isShowing){
                mLoading?.show()
            }
        }
    }

    /**
     * dimssNewLoading
     */
    open fun dimssNewLoading() {
        if(mLoading != null){
            if(mLoading!!.isShowing){
                mLoading?.dismiss()
            }
        }
    }

    override fun onDestroy() {
        dimssNewLoading()
        mLoading = null
        super.onDestroy()
    }
}