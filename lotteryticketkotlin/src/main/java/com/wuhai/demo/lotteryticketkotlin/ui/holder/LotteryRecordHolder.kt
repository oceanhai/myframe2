package com.wuhai.demo.lotteryticketkotlin.ui.holder

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import com.wuhai.demo.lotteryticketkotlin.R
import com.wuhai.demo.lotteryticketkotlin.databinding.ItemLotteryBinding
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryRecord
import com.wuhai.demo.lotteryticketkotlin.ui.holder.base.BaseDataBindingHolder
import com.wuhai.demo.lotteryticketkotlin.widget.tagflowlayout.FlowLayout
import com.wuhai.demo.lotteryticketkotlin.widget.tagflowlayout.TagAdapter
import java.util.*

/**
 * 作者 wuhai
 *
 * 创建日期 2018/8/1 17:06
 *
 * 描述：
 */
class LotteryRecordHolder(binding: ViewDataBinding, context: Context?) : BaseDataBindingHolder<LotteryRecord?>(binding, context) {
    private val binding: ItemLotteryBinding
    private val mInflater: LayoutInflater
    private var redAdapter: TagAdapter<*>? = null
    private var blueAdapter: TagAdapter<*>? = null
    override fun refreshView() {
        binding.betNumTv.setText(mData!!.lottery_bet_num.toString() + " 注")
        binding.moneyTv.text = "￥" + mData!!.lottery_bet_money + " 元"
        if (TextUtils.isEmpty(mData!!.lottery_no)) {
            binding.lotteryNoTv.visibility = View.GONE
        } else {
            binding.lotteryNoTv.visibility = View.VISIBLE
            binding.lotteryNoTv.text = mData!!.lottery_no + "期"
        }
        if (mData!!.lottery_red_ball_count > 6 || mData!!.lottery_blue_ball_count > 1) {
            binding.lotteryMultipleTv.visibility = View.VISIBLE
        } else {
            binding.lotteryMultipleTv.visibility = View.GONE
        }
        val redBalls = Arrays.asList(*mData!!.lottery_red_ball!!.split(",".toRegex()).toTypedArray())
        val blueBalls = Arrays.asList(*mData!!.lottery_blue_ball!!.split(",".toRegex()).toTypedArray())
        redAdapter = object : TagAdapter<String?>(redBalls) {
            override fun getView(parent: FlowLayout, position: Int, tag: String?): View {
                val tv = mInflater.inflate(R.layout.item_red_ball_view,
                        binding.redBallList, false) as TextView
                tv.text = tag
                return tv
            }
        }
        binding.redBallList.adapter = redAdapter
        blueAdapter = object : TagAdapter<String?>(blueBalls) {
            override fun getView(parent: FlowLayout, position: Int, tag: String?): View {
                val tv = mInflater.inflate(R.layout.item_blue_ball_view,
                        binding.blueBallList, false) as TextView
                tv.text = tag
                return tv
            }
        }
        binding.blueBallList.adapter = blueAdapter
    }

    companion object {
        const val TAG = "LotteryRecordHolder"
    }

    init {
        this.binding = binding as ItemLotteryBinding
        mInflater = LayoutInflater.from(mContext)
    }
}