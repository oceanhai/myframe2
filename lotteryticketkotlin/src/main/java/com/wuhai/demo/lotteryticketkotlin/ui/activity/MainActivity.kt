package com.wuhai.demo.lotteryticketkotlin.ui.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.google.gson.JsonParser
import com.wuhai.demo.lotteryticketkotlin.R
import com.wuhai.demo.lotteryticketkotlin.config.Constants
import com.wuhai.demo.lotteryticketkotlin.contract.IHomeContract
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryHistoryEntity
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryQueryEntity
import com.wuhai.demo.lotteryticketkotlin.presenter.HomePresenter
import com.wuhai.demo.lotteryticketkotlin.ui.base.NewLoadingBaseActivity
import com.wuhai.demo.lotteryticketkotlin.utils.*
import kotlinx.android.synthetic.main.ac_main.*

class MainActivity : NewLoadingBaseActivity(), View.OnClickListener, IHomeContract.View {

    var mPresenter:HomePresenter?=null
    var ssqEntity:LotteryQueryEntity?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main)

        init()
        setListener()
        getData()
    }

    private fun getData() {
//        mPresenter?.lotteryQuerySsq(Constants.JUHE_LOTTERY_KEY, Constants.JUHE_LOTTERY_ID_SSQ,"");

        val lottery_ssq = CommonUtils.getFromAssets("lottery_ssq", this)
        val `object` = JsonParser().parse(lottery_ssq).asJsonObject
        val result_ssq = `object`["result"].toString()
        val lotteryQueryEntity = GsonUtils.instance.fromJson(result_ssq, LotteryQueryEntity::class.java)
        setLotteryQuerySsq(lotteryQueryEntity)
    }

    override fun setStatistical() {
    }

    private fun init() {
        //隐藏titlebar
        setActionBarVisibility(false)
        mPresenter = HomePresenter(this)
    }

    private fun setListener() {
        ac_main_dlt_ll.setOnClickListener(this)
        ac_main_ssq_ll.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ac_main_dlt_ll ->
                //                LotteryHistoryActivity.startActivity(this, Constants.JUHE_LOTTERY_ID_DLT, dltEntity);
                showToast("客观别急！正在开发中")
            R.id.ac_main_ssq_ll -> {
                LotteryHistoryActivity.startActivity(this, Constants.JUHE_LOTTERY_ID_SSQ, ssqEntity)
            }
        }
    }

    override fun setLotteryQuerySsq(entity: LotteryQueryEntity?) {
        if (entity == null) {
            return
        }
        ssqEntity = entity

        ssq_lottery_no_and_date_tv.text =
                entity.lottery_no + "期  " + entity.lottery_date?.substring(5) +
                "  (" + DateUtil.getCustomStr(entity.lottery_date!!) + ")"

        val lottery_pool_amount: String = entity.lottery_pool_amount!!.replace(",", "")
        if (!MatcherUtils.isAllNumber(lottery_pool_amount)) {
            ssq_lottery_pool_amount_tv.visibility = View.GONE
        } else {
            ssq_lottery_pool_amount_tv.visibility = View.VISIBLE
            ssq_lottery_pool_amount_tv.text =
                    "奖池 " + MonetaryUnitUtil.formatNum(lottery_pool_amount, false)
        }

        val res: Array<String> = entity.lottery_res?.split(",".toRegex())!!.toTypedArray()
        if (res != null && res.size == 7) {
            double_ball_red1.text = res[0]
            double_ball_red2.text = res[1]
            double_ball_red3.text = res[2]
            double_ball_red4.text = res[3]
            double_ball_red5.text = res[4]
            double_ball_red6.text = res[5]
            double_ball_blue1.text = res[6]
        }
    }

    override fun setLotteryQueryDlt(entity: LotteryQueryEntity?) {
        if (entity == null) {
            return
        }
        dlt_lottery_no_and_date_tv.text =
                entity.lottery_no + "期  " + entity.lottery_date?.substring(5) +
                "  (" + DateUtil.getCustomStr(entity.lottery_date!!) + ")"

        val lottery_pool_amount: String = entity.lottery_pool_amount!!.replace(",", "")
        if (TextUtils.isEmpty(lottery_pool_amount)) {
            dlt_lottery_pool_amount_tv.visibility = View.GONE
        } else {
            dlt_lottery_pool_amount_tv.visibility = View.VISIBLE
            dlt_lottery_pool_amount_tv.text = "奖池 $lottery_pool_amount"
        }

        val res: Array<String> = entity.lottery_res?.split(",".toRegex())!!.toTypedArray()
        if (res != null && res.size == 7) {
            lotto_red1.text = res[0]
            lotto_red2.text = res[1]
            lotto_red3.text = res[2]
            lotto_red4.text = res[3]
            lotto_red5.text = res[4]
            lotto_blue1.text = res[5]
            lotto_blue2.text = res[6]
        }
    }

    /**
     * TODO  showLoading、dimssLoading 不重写的话，就要在p层进行showSuccessLayout()
     */
    override fun showLoading() {}

    override fun dimssLoading() {}
}
