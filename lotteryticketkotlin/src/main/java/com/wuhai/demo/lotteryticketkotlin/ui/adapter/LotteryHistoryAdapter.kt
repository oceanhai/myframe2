package com.wuhai.demo.lotteryticketkotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wuhai.demo.lotteryticketkotlin.R
import com.wuhai.demo.lotteryticketkotlin.databinding.ItemLotteryHistoryBinding
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryHistory
import com.wuhai.demo.lotteryticketkotlin.ui.adapter.base.BaseDataAdapter
import com.wuhai.demo.lotteryticketkotlin.ui.holder.LotteryHistoryHolder

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/8 20:50
 *
 * 描述：
 */
class LotteryHistoryAdapter(context: Context?) : BaseDataAdapter<LotteryHistory?>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //以往方式
        val layoutInflater = LayoutInflater.from(mContext)
        //        View view = layoutInflater.inflate(R.layout.item_lottery_history, parent, false);
//        LocationNamesHolder holder = new LocationNamesHolder(view);

        //DataBinding
        val binding: ItemLotteryHistoryBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_lottery_history, parent, false)
        return LotteryHistoryHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder1 = holder as LotteryHistoryHolder
        holder1.setmData(mData!![position])
        holder1.itemView.setOnClickListener {
            if (mOnItemClickLitener != null) {
                mOnItemClickLitener!!.onItemClick(holder1.itemView, position)
            }
        }
        holder1.refreshView() //对viewholder进行操作
        if (position == mData!!.size - 1) {
            holder1.hideLine()
        }
    }
}