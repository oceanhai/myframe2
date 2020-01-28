package com.wuhai.lotteryticket.ui.adapter.base;

import android.content.Context;
import android.view.View;

import com.diycoder.library.adapter.BaseAdapter;

import java.util.List;

/**
 * Created by wuhai on 2017/2/8 17:28.
 * 描述：BaseAdapter基类 2  module 上拉加载框架recyclerview
 */
public abstract class BaseDataAdapter2<T> extends BaseAdapter {
    public OnItemClickLitener mOnItemClickLitener;

    public List<T> getData(){
        return dataList;
    }

    public BaseDataAdapter2(Context context) {
        super(context);
    }


    /**
     * 清空
     */
    public void clear(){
        if(dataList != null ){
            dataList.clear();
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
        dataList = data;
        notifyDataSetChanged();
    }

    /**
     * 初始empty数据
     * @param data
     */
    public void setEmptyData(List<T> data) {
        hasFooter = false;
        dataList = data;
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     * @param data
     */
    public void addAll(List<T> data){
        if(dataList != null){
            dataList.addAll(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加数据 单个数据
     * @param data
     */
    public void addData(T data){
        if(dataList != null){
            dataList.add(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 回调点击监听
     */
    public interface OnItemClickLitener{
        void onItemClick(View view, int position);
    }

}
