package com.wuhai.demo.lotteryticketkotlin.ui.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import com.trello.rxlifecycle.LifecycleTransformer
import com.trello.rxlifecycle.android.ActivityEvent
import com.trello.rxlifecycle.components.support.RxFragmentActivity
import com.wuhai.demo.lotteryticketkotlin.R
import com.wuhai.demo.lotteryticketkotlin.utils.StatusBarUtil

/**
 * BaseActivity
 */
abstract class BaseActivity : RxFragmentActivity() {

    private var mContext: Context = this

    var TAG = ""

    /**
     * 统计id
     */
    var statistical = ""

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        TAG = javaClass.simpleName
        statistical = TAG//默认名 ※可在setStatistical() 根据需求在每个页面里设置新id

        //新的 沉浸式
        setStatusBar()
    }

    protected fun setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary),0);
    }

    override fun onResume() {
        super.onResume()
        setStatistical();
        //            StatisticalTools.onResume(this, statistical);
    }

    /**
     * 给统计赋值
     */
    abstract fun setStatistical()

    fun getContext():Context{
        return mContext
    }

    override fun onPause() {
        super.onPause()
        //            StatisticalTools.onPause(this, statistical);
    }

    /**
     * ac 销毁时候，取消订阅
     * @return
     */
    val lifecycleTransformer: LifecycleTransformer<*>?
        = bindUntilEvent<Any>(ActivityEvent.DESTROY)
}
