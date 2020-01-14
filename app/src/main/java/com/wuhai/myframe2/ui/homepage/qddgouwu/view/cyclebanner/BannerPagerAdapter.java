/*
 * Copyright 2014 trinea.cn All right reserved. This software is the confidential and proprietary information of
 * trinea.cn ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with trinea.cn.
 */

package com.wuhai.myframe2.ui.homepage.qddgouwu.view.cyclebanner;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by flyeek on 3/3/16.
 */
public class BannerPagerAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mImageIdList;
    private List<String> mLinkUrlArray;
    private List<String> mUrlTitlesList;
    private List<String> bannerIdList;
    private int mCount;
    private boolean mIsInfiniteLoop;

    public BannerPagerAdapter(Context context, List<String> imageIdList,
                              List<String> urllist, List<String> urlTitlesList) {
        this.mContext = context;
        this.mImageIdList = imageIdList;
        if (imageIdList != null) {
            this.mCount = imageIdList.size();
        }
        this.mLinkUrlArray = urllist;
        this.mUrlTitlesList = urlTitlesList;
        mIsInfiniteLoop = false;
    }

    public BannerPagerAdapter(Context context, List<String> imageIdList,
                              List<String> urllist, List<String> urlTitlesList, List<String> bannerIdList) {
        this.mContext = context;
        this.mImageIdList = imageIdList;
        if (imageIdList != null) {
            this.mCount = imageIdList.size();
        }
        this.mLinkUrlArray = urllist;
        this.mUrlTitlesList = urlTitlesList;
        mIsInfiniteLoop = false;

        this.bannerIdList = bannerIdList;
    }

    @Override
    public int getCount() {
        // Infinite loop
        return mIsInfiniteLoop ? Integer.MAX_VALUE : mImageIdList.size();
    }

    /**
     * get really position
     *
     * @param position
     * @return
     */
    private int getPosition(int position) {
        return mIsInfiniteLoop ? position % mCount : position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup container) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = holder.imageView = new SimpleDraweeView(mContext);
            holder.imageView
                    .setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
            holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.imageView.setImageURI(Uri.parse(mImageIdList.get(getPosition(position))));
        holder.imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String uri = mLinkUrlArray.get(getPosition(position));

                if(bannerIdList != null && bannerIdList.size()>0){
                    //友盟统计点击事件
//                    MobclickAgent.onEvent(mContext, bannerIdList.get(getPosition(position)));
                }

                //TODO banner图点击跳转
                Toast.makeText(mContext,"banner图被点击了",Toast.LENGTH_LONG).show();
//                if(QiandaodaoUri.isQiandaodaoUri(uri)){
//                    Intent intent = new Intent(mContext, MainActivity.class);
//                    intent.putExtra(QiandaodaoUri.TAG, uri);
//                    mContext.startActivity(intent);
//                }else{
//                    Intent intent = new Intent(mContext, CategoryWebActivity.class);
//                    intent.putExtra(CategoryWebActivity.EXTRA_URL, uri);
//                    intent.putExtra(CategoryWebActivity.EXTRA_SOURCE, mContext.getString(R.string
//                            .sensors_data_page_source_home_banner));
//                    mContext.startActivity(intent);
//                }
            }
        });

        return view;
    }

    private static class ViewHolder {
        SimpleDraweeView imageView;
    }

    /**
     * @return the mIsInfiniteLoop
     */
    public boolean ismIsInfiniteLoop() {
        return mIsInfiniteLoop;
    }

    /**
     * @param isInfiniteLoop the mIsInfiniteLoop to set
     */
    public BannerPagerAdapter setmIsInfiniteLoop(boolean isInfiniteLoop) {
        this.mIsInfiniteLoop = isInfiniteLoop;
        return this;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
