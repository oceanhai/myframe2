package com.wuhai.demo.lotteryticketkotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wuhai.demo.lotteryticketkotlin.R
import com.wuhai.demo.lotteryticketkotlin.databinding.ItemLotteryBinding
import com.wuhai.demo.lotteryticketkotlin.model.bean.Lottery
import com.wuhai.demo.lotteryticketkotlin.ui.adapter.base.BaseDataAdapter
import com.wuhai.demo.lotteryticketkotlin.ui.holder.LotteryCreateHolder
import java.util.*

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/8 20:50
 *
 * 描述：生成彩票列表  adapter
 */
class LotteryCreateAdapter(context: Context?) : BaseDataAdapter<Lottery?>(context) {
    private val layoutInflater: LayoutInflater

    /**
     * 添加数据 单个数据
     * @param data
     */
    override fun addData(data: Lottery?) {
        if (mData != null) {
            mData?.add(0, data) //插入到列表头
            notifyDataSetChanged()
        }
    }

    /**
     * 删除
     * @param data
     */
    fun deleteData(data: Lottery?) {
        if (mData != null) {
            mData!!.remove(data)
            notifyDataSetChanged()
        }
    }

    /**
     * 获得要排除的红球和蓝球集合
     * @return
     */
    val factorSet: Map<String?, MutableSet<String?>?>?
        get() {
            if (mData == null) {
                return null
            }
            val map: MutableMap<String?, MutableSet<String?>?> = HashMap()
            val mRedNumSet: MutableSet<String?>? = TreeSet()
            val mBlueNumSet: MutableSet<String?>? = TreeSet()
            for (x in mData!!.indices) {
                val lottery = mData!![x]!!
                if (lottery.isSelected) {
                    val redBalls = lottery.lottery_red_ball!!.split(",".toRegex()).toTypedArray()
                    val blueBalls = lottery.lottery_blue_ball!!.split(",".toRegex()).toTypedArray()
                    for (y in redBalls.indices) {
                        mRedNumSet?.add(redBalls[y])
                    }
                    for (y in blueBalls.indices) {
                        mBlueNumSet?.add(blueBalls[y])
                    }
                }
            }
            if (!mRedNumSet!!.isEmpty() && !mBlueNumSet!!.isEmpty()) {
                map["red"] = mRedNumSet
                map["blue"] = mBlueNumSet
                return map
            }
            return null
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //DataBinding
        val binding: ItemLotteryBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_lottery, parent, false)
        return LotteryCreateHolder(binding, mContext)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder1 = holder as LotteryCreateHolder
        holder1.setmData(mData!![position])
        holder1.itemView.setOnClickListener {
            if (mOnItemClickLitener != null) {
                mOnItemClickLitener!!.onItemClick(holder1.itemView, position)
            }
        }
        holder1.itemView.setOnLongClickListener {
            if (mOnItemLongClickListener != null) {
                mOnItemLongClickListener!!.onItemLongClick(holder1.itemView, position)
            }
            true
        }
        holder1.refreshView() //对viewholder进行操作
    }

    init {
        layoutInflater = LayoutInflater.from(mContext)
    }
}