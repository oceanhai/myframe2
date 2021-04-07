package com.wuhai.demo.lotteryticketkotlin.ui.adapter.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by wuhai on 2017/2/8 17:28.
 * 描述：BaseAdapter基类
 */
abstract class BaseDataAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected var mContext: Context? = null
    protected var mData: MutableList<T>? = null
    var mOnItemClickLitener: OnItemClickLitener? = null
    var mOnItemLongClickListener: OnItemLongClickListener? = null
    val data: List<T>?
        get() = mData

    constructor() {}
    constructor(context: Context?) {
        mContext = context
    }

    /**
     * 清空
     */
    fun clear() {
        if (mData != null) {
            mData!!.clear()
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
     * 设置长按监听
     * @param mOnItemLongClickListener
     */
    fun setOnItemLongClickListener(mOnItemLongClickListener: OnItemLongClickListener?) {
        this.mOnItemLongClickListener = mOnItemLongClickListener
    }

    /**
     * 初始数据
     * @param data
     */
    fun setData(data: MutableList<T>?) {
        mData = data
        notifyDataSetChanged()
    }

    /**
     * 添加数据
     * @param data
     */
    fun addAll(data: List<T>?) {
        if (mData != null) {
            mData?.addAll(data!!)
            notifyDataSetChanged()
        }
    }

    /**
     * 添加数据 单个数据
     * @param data
     */
    open fun addData(data: T) {
        if (mData != null) {
            mData?.add(data)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return if (mData != null) mData!!.size else 0
    }

    /**
     * 获取position位置数据
     * @param position
     * @return
     */
    fun getDataItem(position: Int): T? {
        return if (mData != null) mData?.get(position) else null
    }

    /**
     * 回调点击监听
     */
    interface OnItemClickLitener {
        fun onItemClick(view: View?, position: Int)
    }

    /**
     * 长按监听
     */
    interface OnItemLongClickListener {
        fun onItemLongClick(view: View?, position: Int)
    }
}