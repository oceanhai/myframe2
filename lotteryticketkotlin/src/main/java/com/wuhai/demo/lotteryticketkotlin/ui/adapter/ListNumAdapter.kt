package com.wuhai.demo.lotteryticketkotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wuhai.demo.lotteryticketkotlin.R
import com.wuhai.demo.lotteryticketkotlin.ui.adapter.base.BaseDataAdapter

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/8 20:50
 *
 * 描述：红球数和蓝球数  下拉选择列表
 */
class ListNumAdapter(context: Context?) : BaseDataAdapter<String?>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater.inflate(R.layout.item_list_num, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder1 = holder as ViewHolder
        holder1.mNumTv.text = getDataItem(position)
        holder1.mNumTv.setOnClickListener { v ->
            if (mOnItemClickLitener != null) {
                mOnItemClickLitener!!.onItemClick(v, position)
            }
        }
    }

    internal class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mNumTv: TextView

        init {
            mNumTv = view.findViewById<View>(R.id.item_num_tv) as TextView
        }
    }
}