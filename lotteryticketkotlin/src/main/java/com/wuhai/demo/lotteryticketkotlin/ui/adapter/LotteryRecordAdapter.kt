package com.wuhai.demo.lotteryticketkotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.wuhai.demo.lotteryticketkotlin.R
import com.wuhai.demo.lotteryticketkotlin.databinding.ItemLotteryBinding
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryRecord
import com.wuhai.demo.lotteryticketkotlin.ui.adapter.base.BaseDataAdapter2
import com.wuhai.demo.lotteryticketkotlin.ui.holder.LotteryRecordHolder

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/8 20:50
 *
 * 描述：彩票记录  adapter
 */
class LotteryRecordAdapter(context: Context?) : BaseDataAdapter2<LotteryRecord?, LotteryRecordHolder?>(context) {
    private val layoutInflater: LayoutInflater
    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): LotteryRecordHolder {
        //DataBinding
        val binding: ItemLotteryBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_lottery, parent, false)
        return LotteryRecordHolder(binding, mContext)
    }

    override fun onBindItemViewHolder(holder: LotteryRecordHolder?, position: Int) {
        holder?.setmData(dataList[position])
        holder?.refreshView() //对viewholder进行操作
    }

    init {
        layoutInflater = LayoutInflater.from(mContext)
    }
}