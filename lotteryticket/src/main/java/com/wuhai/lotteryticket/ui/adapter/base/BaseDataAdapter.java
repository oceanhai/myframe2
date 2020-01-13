package com.wuhai.lotteryticket.ui.adapter.base;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by wuhai on 2017/2/8 17:28.
 * 描述：BaseAdapter基类
 */
public abstract class BaseDataAdapter<T> extends RecyclerView.Adapter {
    protected Context mContext;
    protected List<T> mData;
    public OnItemClickLitener mOnItemClickLitener;

    public List<T> getData(){
        return mData;
    }

    public BaseDataAdapter() {
    }

    public BaseDataAdapter(Context context) {
        mContext = context;
    }


    /**
     * 清空
     */
    public void clear(){
        if(mData != null ){
            mData.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * 设置点击监听
     * @param mOnItemClickLitener
     */
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    /**
     * 初始数据
     * @param data
     */
    public void setData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     * @param data
     */
    public void addAll(List<T> data){
        if(mData != null){
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加数据 单个数据
     * @param data
     */
    public void addData(T data){
        if(mData != null){
            mData.add(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return (mData != null) ? mData.size() : 0;
    }

    /**
     * 获取position位置数据
     * @param position
     * @return
     */
    public T getDataItem(int position) {
        return (mData != null) ? mData.get(position) : null;
    }

    /**
     * 回调点击监听
     */
    public interface OnItemClickLitener{
        void onItemClick(View view, int position);
    }

}
