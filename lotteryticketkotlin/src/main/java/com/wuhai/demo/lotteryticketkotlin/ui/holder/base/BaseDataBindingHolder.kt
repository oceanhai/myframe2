package com.wuhai.demo.lotteryticketkotlin.ui.holder.base

import android.content.Context
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * 作者 wuhai
 *
 * 创建日期 2018/5/25 9:54
 *
 * 描述：
 */
abstract class BaseDataBindingHolder<T : Any?> : RecyclerView.ViewHolder {
    protected var mData: T? = null
    protected var mContext: Context? = null

    constructor(itemView: View?) : super(itemView!!) {}
    constructor(binding: ViewDataBinding) : super(binding.root) {}
    constructor(binding: ViewDataBinding, context: Context?) : super(binding.root) {
        mContext = context
    }

    constructor(itemView: View?, context: Context?) : super(itemView!!) {
        mContext = context
    }

    fun setmData(mData: T) {
        this.mData = mData
    }

    abstract fun refreshView()
}