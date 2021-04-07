package com.wuhai.demo.lotteryticketkotlin.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.diycoder.library.listener.ScrollListener
import com.google.gson.JsonParser
import com.wuhai.demo.lotteryticketkotlin.R
import com.wuhai.demo.lotteryticketkotlin.config.Constants
import com.wuhai.demo.lotteryticketkotlin.contract.ILotteryHistoryContract
import com.wuhai.demo.lotteryticketkotlin.databinding.AcLotteryHistoryBinding
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryHistoryEntity
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryQueryEntity
import com.wuhai.demo.lotteryticketkotlin.presenter.LotteryHistoryPresenter
import com.wuhai.demo.lotteryticketkotlin.ui.adapter.LotteryHistoryHeaderAdapter
import com.wuhai.demo.lotteryticketkotlin.ui.base.NewLoadingBaseActivity
import com.wuhai.demo.lotteryticketkotlin.utils.CommonUtils.getFromAssets
import com.wuhai.demo.lotteryticketkotlin.utils.GsonUtils.Companion.instance

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：彩票历史列表
 */
class LotteryHistoryActivity : NewLoadingBaseActivity(), ILotteryHistoryContract.View, LotteryHistoryHeaderAdapter.OnItemClickLitener, View.OnClickListener {
    //彩票ID
    private var mLotteryId: String? = null
    private var mLotteryEntity //数据
            : LotteryQueryEntity? = null

    //Binding
    private var binding: AcLotteryHistoryBinding? = null

    //P
    private var mPresenter: LotteryHistoryPresenter? = null

    //彩票历史 adapter
    private var mLotteryHistoryHeaderAdapter: LotteryHistoryHeaderAdapter? = null
    private lateinit var mScrollListener: ScrollListener

    //假数据
    private var lotteryHistoryEntity: LotteryHistoryEntity? = null
    private var newPage = 1 //最多加载三页
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        parseIntent()
        init()
        setListener()
        data
    }

    //        mPresenter.lotteryHistory(mLotteryId, 1, 10);
    private val data: Unit
        private get() {
//        mPresenter.lotteryHistory(mLotteryId, 1, 10);
            val lottery_ssq_history = getFromAssets("lottery_ssq_history", this)
            val `object` = JsonParser().parse(lottery_ssq_history).asJsonObject
            val result_ssq = `object`["result"].toString()
            lotteryHistoryEntity = instance.fromJson(result_ssq, LotteryHistoryEntity::class.java)
            setLotteryHistory(lotteryHistoryEntity)
        }

    private fun initView() {
        val contentView = View.inflate(this, R.layout.ac_lottery_history, null)
        setContentView(contentView) //※注意 这是把布局add到父容器里
        binding = DataBindingUtil.bind(contentView)
    }

    private fun parseIntent() {
        val intent = intent
        if (intent != null) {
            mLotteryId = intent.getStringExtra("lottery_id")
            mLotteryEntity = intent.getSerializableExtra("lottery_entity") as LotteryQueryEntity?
        }
    }

    override fun setStatistical() {}
    private fun init() {
        when (mLotteryId) {
            Constants.JUHE_LOTTERY_ID_SSQ -> title = "双色球"
            Constants.JUHE_LOTTERY_ID_DLT -> title = "超级大乐透"
        }
        mPresenter = LotteryHistoryPresenter(this)
        val linearLayoutManager = LinearLayoutManager(this)
        binding!!.lotteryHistoryRv.layoutManager = linearLayoutManager
        mLotteryHistoryHeaderAdapter = LotteryHistoryHeaderAdapter(this, mLotteryId!!)
        mLotteryHistoryHeaderAdapter!!.setOnItemClickLitener(this)
        mScrollListener = object : ScrollListener(linearLayoutManager) {
            override fun onLoadMore() {
                loadMore()
            }
        }
        binding!!.lotteryHistoryRv.addOnScrollListener(mScrollListener)
        binding!!.lotteryHistoryRv.adapter = mLotteryHistoryHeaderAdapter
    }

    /**
     * 加载更多
     */
    private fun loadMore() {
        Handler().postDelayed({
            newPage++
            if (newPage > 3) {
                mLotteryHistoryHeaderAdapter!!.setHasMoreDataAndFooter(false, true)
                mScrollListener!!.setLoadMore(false)
            } else {
                mScrollListener!!.setLoadMore(true)
            }
            addNewData()
        }, 3000)
    }

    private fun addNewData() {
        mLotteryHistoryHeaderAdapter!!.setDataList(lotteryHistoryEntity!!.lotteryResList)
    }

    private fun setListener() {
        binding!!.lotteryHistoryCreateRl.setOnClickListener(this)
        binding!!.lotteryHistoryTrendChartRl.setOnClickListener(this)
        binding!!.lotteryHistoryCountingAwardRl.setOnClickListener(this)
    }

    override fun reloadData() {
        super.reloadData()
    }

    override fun setLotteryHistory(entity: LotteryHistoryEntity?) {
        if (mLotteryHistoryHeaderAdapter == null) {
            return
        }
        mLotteryHistoryHeaderAdapter!!.setDataList(entity!!.lotteryResList)
        mLotteryHistoryHeaderAdapter!!.setHasMoreData(true) //可加载更多
    }

    override fun onItemClick(view: View?, position: Int) {
        //彩票历史列表 点击事件
        Toast.makeText(this, "item 点击位置$position", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.lottery_history_create_rl -> {
            }
            R.id.lottery_history_trend_chart_rl -> Toast.makeText(this, "趋势图暂未开通", Toast.LENGTH_SHORT).show()
            R.id.lottery_history_counting_award_rl -> showToast("算奖工具暂未开通")
        }
    }

    companion object {
        /**
         *
         * @param context
         * @param lottery_id        ssq,dlt
         */
        fun startActivity(context: Context, lottery_id: String?, entity: LotteryQueryEntity?) {
            val intent = Intent()
            intent.putExtra("lottery_id", lottery_id)
            intent.putExtra("lottery_entity", entity)
            intent.setClass(context, LotteryHistoryActivity::class.java)
            context.startActivity(intent)
        }
    }
}