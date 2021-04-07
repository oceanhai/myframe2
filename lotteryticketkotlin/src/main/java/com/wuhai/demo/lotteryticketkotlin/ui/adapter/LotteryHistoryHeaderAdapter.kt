package com.wuhai.demo.lotteryticketkotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.diycoder.library.adapter.HeaderAdapter
import com.wuhai.demo.lotteryticketkotlin.R
import com.wuhai.demo.lotteryticketkotlin.databinding.ItemLotteryHistoryBinding
import com.wuhai.demo.lotteryticketkotlin.databinding.ItemLotteryHistoryHeaderBinding
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryHistory
import com.wuhai.demo.lotteryticketkotlin.ui.holder.LotteryHistoryHeaderHolder
import com.wuhai.demo.lotteryticketkotlin.ui.holder.LotteryHistoryHolder

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/8 20:50
 *
 * 描述：
 */
class LotteryHistoryHeaderAdapter(context: Context?, private val lottery_id: String) : HeaderAdapter<LotteryHistory?, LotteryHistoryHeaderHolder?, LotteryHistoryHolder?>(context) {

    /**
     * 回调点击监听
     */
    interface OnItemClickLitener {
        fun onItemClick(view: View?, position: Int)
    }

    var mOnItemClickLitener: OnItemClickLitener? = null

    /**
     * 设置点击监听
     * @param mOnItemClickLitener
     */
    fun setOnItemClickLitener(mOnItemClickLitener: OnItemClickLitener?) {
        this.mOnItemClickLitener = mOnItemClickLitener
    }

    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): LotteryHistoryHolder {
        //以往方式
        val layoutInflater = LayoutInflater.from(mContext)
        //        View view = layoutInflater.inflate(R.layout.item_lottery_history, parent, false);
//        LocationNamesHolder holder = new LocationNamesHolder(view);

        //DataBinding
        val binding: ItemLotteryHistoryBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_lottery_history, parent, false)
        return LotteryHistoryHolder(binding)
    }

    override fun onBindItemViewHolder(holder: LotteryHistoryHolder?, position: Int) {
        holder?.setmData(getItemData(position))
        holder?.itemView?.setOnClickListener {
            if (mOnItemClickLitener != null) {
                mOnItemClickLitener!!.onItemClick(holder.itemView, position)
            }
        }
        holder?.refreshView() //对viewholder进行操作
        //        if(position == dataList.size()-1){
//            holder.hideLine();
//        }
    }

    override fun onCreateHeaderViewHolder(parent: ViewGroup, viewType: Int): LotteryHistoryHeaderHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        //DataBinding
        val binding: ItemLotteryHistoryHeaderBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_lottery_history_header, parent, false)
        return LotteryHistoryHeaderHolder(binding, lottery_id)
    }

    override fun onBindHeaderViewHolder(holder: LotteryHistoryHeaderHolder?, position: Int) {
        val binding = holder?.binding
        binding?.itemLotteryHintTv?.setOnClickListener { Toast.makeText(mContext, "开奖提醒点击", Toast.LENGTH_SHORT).show() }
        binding?.itemLotteryRuleTv?.setOnClickListener { Toast.makeText(mContext, "开奖规则点击", Toast.LENGTH_SHORT).show() }
    }

}