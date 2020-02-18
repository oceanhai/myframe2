package com.wuhai.lotteryticket.ui.holder.base;

import android.content.Context;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 作者 wuhai
 *
 * 创建日期 2018/5/25 9:54
 *
 * 描述：
 */

public abstract class BaseDataBindingHolder<T extends Object> extends RecyclerView.ViewHolder  {


    protected   T mData;
    protected Context mContext;

    public BaseDataBindingHolder(View itemView) {
        super(itemView);
    }

    public BaseDataBindingHolder(ViewDataBinding binding) {
        super(binding.getRoot());
    }
    public BaseDataBindingHolder(ViewDataBinding binding, Context context) {
        super(binding.getRoot());
        mContext = context;
    }

    public BaseDataBindingHolder(View itemView, Context context) {
        super(itemView);
        mContext = context;
    }

    public void setmData(T mData) {
        this.mData = mData;
    }

    public abstract void refreshView();
}
