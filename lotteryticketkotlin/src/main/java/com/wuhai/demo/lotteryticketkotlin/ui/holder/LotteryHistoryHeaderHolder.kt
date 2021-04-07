package com.wuhai.demo.lotteryticketkotlin.ui.holder

import androidx.databinding.ViewDataBinding
import com.wuhai.demo.lotteryticketkotlin.config.Constants
import com.wuhai.demo.lotteryticketkotlin.databinding.ItemLotteryHistoryHeaderBinding
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryHistory
import com.wuhai.demo.lotteryticketkotlin.ui.holder.base.BaseDataBindingHolder

/**
 * 作者 wuhai
 *
 * 创建日期 2018/8/1 17:06
 *
 * 描述：
 */
class LotteryHistoryHeaderHolder(binding: ViewDataBinding, lottery_id: String) : BaseDataBindingHolder<LotteryHistory?>(binding) {
    val binding: ItemLotteryHistoryHeaderBinding
    private val lottery_id: String
    override fun refreshView() {
        if (Constants.JUHE_LOTTERY_ID_SSQ == lottery_id) {
            binding.itemLotteryTimeTv.text = "每周二、四、日 21:15开奖"
        } else {
            binding.itemLotteryTimeTv.text = "每周一、三、六 20:30开奖"
        }
    }

    init {
        this.binding = binding as ItemLotteryHistoryHeaderBinding
        this.lottery_id = lottery_id
    }
}