package com.wuhai.demo.lotteryticketkotlin.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.diycoder.library.listener.ScrollListener
import com.wuhai.demo.lotteryticketkotlin.R
import com.wuhai.demo.lotteryticketkotlin.databinding.AcLotteryRecordBinding
import com.wuhai.demo.lotteryticketkotlin.model.LotteryRecordModelImpl
import com.wuhai.demo.lotteryticketkotlin.ui.adapter.LotteryRecordAdapter
import com.wuhai.demo.lotteryticketkotlin.ui.base.NewLoadingBaseActivity
import com.wuhai.demo.lotteryticketkotlin.utils.LogProxy.e

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：彩票记录
 */
class LotteryRecordActivity : NewLoadingBaseActivity() {
    //Binding
    private var binding: AcLotteryRecordBinding? = null

    //impl
    private var mLotteryRecordModelImpl: LotteryRecordModelImpl? = null

    // adapter
    private var mLotteryRecordAdapter: LotteryRecordAdapter? = null
    private lateinit var mScrollListener : ScrollListener
    private var mCurPage = 1 //当前页数
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        parseIntent()
        init()
        setListener()
        initData()
    }

    private fun initData() {
        val list = mLotteryRecordModelImpl!!.queryLotteryRecord(mCurPage, 10)
        if (list!!.isNotEmpty()) {
            mLotteryRecordAdapter!!.data = list

            if (list.size < 10) {
                mLotteryRecordAdapter!!.setHasMoreDataAndFooter(false, true)
                mScrollListener!!.setLoadMore(false)
            }

        } else {
            binding!!.lotteryRv.visibility = View.GONE
        }
    }

    private fun initView() {
        val contentView = View.inflate(this, R.layout.ac_lottery_record, null)
        setContentView(contentView) //※注意 这是把布局add到父容器里
        binding = DataBindingUtil.bind(contentView)
    }

    private fun parseIntent() {
        val intent = intent
        if (intent != null) {
        }
    }

    override fun setStatistical() {}
    private fun init() {
        title = "彩票记录"
        mLotteryRecordModelImpl = LotteryRecordModelImpl()
        val linearLayoutManager = LinearLayoutManager(this)
        binding!!.lotteryRv.layoutManager = linearLayoutManager
        mLotteryRecordAdapter = LotteryRecordAdapter(this)
        //        mLotteryRecordAdapter.setOnItemClickLitener(this);
        mScrollListener = object : ScrollListener(linearLayoutManager) {
            override fun onLoadMore() {
                loadMore()
            }
        }
        binding!!.lotteryRv.addOnScrollListener(mScrollListener)
        binding!!.lotteryRv.adapter = mLotteryRecordAdapter
    }

    /**
     * 加载更多
     */
    private fun loadMore() {
        //TODO 我擦 还真得延迟下 ，否则 setLoadMore(false) 置成false不成功 (lll￢ω￢)
        Handler().postDelayed({
            mCurPage++
            val list = mLotteryRecordModelImpl!!.queryLotteryRecord(mCurPage, 10)
            e(TAG, "loadMore  size = " + list!!.size + ",mCurPage=" + mCurPage)
            if (list.size > 0 && list.size < 10) {
                mLotteryRecordAdapter!!.addAll(list)
                mLotteryRecordAdapter!!.setHasMoreDataAndFooter(false, true)
                mScrollListener!!.setLoadMore(false)
            } else if (list.size == 10) {
                mLotteryRecordAdapter!!.addAll(list)
                mScrollListener!!.setLoadMore(true)
            } else {
                mLotteryRecordAdapter!!.setHasMoreDataAndFooter(false, true)
                mScrollListener!!.setLoadMore(false)
            }
        }, 3000)
    }

    private fun setListener() {}
    override fun reloadData() {
        super.reloadData()
    }

    /**
     * titlebar  right 点击事件
     */
    override fun onRightActionClicked() {
        super.onRightActionClicked()
    }

    companion object {
        /**
         *
         * @param context
         */
        fun startActivity(context: Context) {
            val intent = Intent()
            intent.setClass(context, LotteryRecordActivity::class.java)
            context.startActivity(intent)
        }
    }
}