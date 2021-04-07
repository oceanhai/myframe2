package com.wuhai.demo.lotteryticketkotlin.ui.holder

import android.view.View
import androidx.databinding.ViewDataBinding
import com.wuhai.demo.lotteryticketkotlin.databinding.ItemLotteryHistoryBinding
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryHistory
import com.wuhai.demo.lotteryticketkotlin.ui.holder.base.BaseDataBindingHolder
import com.wuhai.demo.lotteryticketkotlin.utils.DateUtil.getCustomStr
import com.wuhai.demo.lotteryticketkotlin.utils.MatcherUtils.isAllNumber
import com.wuhai.demo.lotteryticketkotlin.utils.MonetaryUnitUtil.formatNum

/**
 * 作者 wuhai
 *
 * 创建日期 2018/8/1 17:06
 *
 * 描述：
 */
class LotteryHistoryHolder(binding: ViewDataBinding) : BaseDataBindingHolder<LotteryHistory?>(binding) {
    private val binding: ItemLotteryHistoryBinding
    override fun refreshView() {
        binding.itemLotteryNoAndDateTv.text = mData!!.lottery_no + "期  " + mData!!.lottery_date!!.substring(5) +
                "  (" + getCustomStr(mData!!.lottery_date!!) + ")"
        val lottery_pool_amount = mData!!.lottery_pool_amount!!.replace(",", "")
        if (!isAllNumber(lottery_pool_amount)) {
            binding.itemLotteryPoolAmountTv.visibility = View.GONE
        } else {
            binding.itemLotteryPoolAmountTv.visibility = View.VISIBLE
            binding.itemLotteryPoolAmountTv.text = "奖池 " + formatNum(lottery_pool_amount, false)
        }
        binding.itemLotteryView.setResource(mData!!.lottery_res)
    }

    fun hideLine() {
        binding.itemLine.visibility = View.GONE
    }

    init {
        this.binding = binding as ItemLotteryHistoryBinding
    }
}