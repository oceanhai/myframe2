package com.wuhai.demo.lotteryticketkotlin.ui.adapter.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.diycoder.library.adapter.BaseAdapter

/**
 * Created by wuhai on 2017/2/8 17:28.
 * 描述：BaseAdapter基类 2  module 上拉加载框架recyclerview
 */
abstract class BaseDataAdapter2<T, IVH : RecyclerView.ViewHolder?>(context: Context?) : BaseAdapter<T, IVH>(context) {
    var mOnItemClickLitener: OnItemClickLitener? = null

    /**
     * 初始数据
     * @param data
     */
    var data: List<T>
        get() = dataList
        set(data) {
            dataList = data
            notifyDataSetChanged()
        }

    /**
     * 清空
     */
    fun clear() {
        if (dataList != null) {
            dataList.clear()
            notifyDataSetChanged()
        }
    }

    /**
     * 设置点击监听
     * @param mOnItemClickLitener
     */
    fun setOnItemClickLitener(mOnItemClickLitener: OnItemClickLitener?) {
        this.mOnItemClickLitener = mOnItemClickLitener
    }

    /**
     * 初始empty数据
     * @param data
     */
    fun setEmptyData(data: List<T>) {
        hasFooter = false
        dataList = data
        notifyDataSetChanged()
    }

    /**
     * 添加数据
     * @param data
     */
    fun addAll(data: List<T>?) {
        if (dataList != null) {
            dataList.addAll(data!!)
            notifyDataSetChanged()
        }
    }

    /**
     * 添加数据 单个数据
     * @param data
     */
    fun addData(data: T) {
        if (dataList != null) {
            dataList.add(data)
            notifyDataSetChanged()
        }
    }

    /**
     * 回调点击监听
     */
    interface OnItemClickLitener {
        fun onItemClick(view: View?, position: Int)
    }
}