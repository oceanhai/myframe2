package com.diycoder.library.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.diycoder.library.R;
import com.diycoder.library.widget.ProgressWheel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lq on 16/6/29.
 */
public abstract class BaseAdapter<M, IVH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {
    /**
     * M  item  数据类型
     * HVH  header  viewHolder
     * IVH  Item    viewHolder
     * BVH  bottom  viewHolder
     */

    public final static String TAG = "BaseAdapter2";

    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_FOOTER = 3;
    private LayoutInflater mLayoutInflater;
    public Context mContext;
    public boolean hasMoreData = true;
    public boolean hasFooter = true;
    public List<M> dataList = new ArrayList<>();
    public String hint;
    public String hint2;

    public AnimationDrawable animationDrawable;

    public BaseAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        hint = context.getString(R.string.no_more_data);
        hint2 = context.getString(R.string.loading);
    }

    //内容长度
    public int getContentItemCount() {
        Log.v(TAG,"dataList.size()="+dataList.size());
        return dataList.size();
    }

    //判断是否是Footer
    public boolean isFooter(int position) {
        return position == getContentItemCount();
    }

    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
        if (isFooter(position)) {
            //Footer
            return ITEM_TYPE_FOOTER;
        } else {
            //内容View
            return ITEM_TYPE_CONTENT;
        }
    }

    //Footer ViewHolder
    public static class FooterViewHolder extends RecyclerView.ViewHolder {

        public final ProgressWheel mProgressView;
        public final ImageView mLoadingIv;
        public final TextView loadMore;

        public FooterViewHolder(View view) {
            super(view);
            mProgressView = (ProgressWheel) itemView.findViewById(R.id.progress_view);
            mLoadingIv = (ImageView) itemView.findViewById(R.id.ivLoading);
            loadMore = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    RecyclerView.Adapter adapter = recyclerView.getAdapter();
                    if (isFullSpanType(adapter.getItemViewType(position))) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        int type = getItemViewType(position);
        if (isFullSpanType(type)) {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
                lp.setFullSpan(true);
            }
        }
    }

    //根据类型判断是否单独占一行
    private boolean isFullSpanType(int type) {
        return type == ITEM_TYPE_FOOTER || type == ITEM_TYPE_HEADER;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_CONTENT) {
            return onCreateItemViewHolder(parent, viewType);
        } else if (viewType == ITEM_TYPE_FOOTER) {
            return new FooterViewHolder(mLayoutInflater.inflate(R.layout.item_view_load_more, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            //没有更多数据
            if (!hasMoreData) {
//                footerViewHolder.mProgressView.setVisibility(View.GONE);
                footerViewHolder.mLoadingIv.setVisibility(View.GONE);
                footerViewHolder.loadMore.setVisibility(View.VISIBLE);
                footerViewHolder.loadMore.setText(hint);

                if(animationDrawable != null){
                    animationDrawable.stop();
                }
            }else{
//                footerViewHolder.mProgressView.setVisibility(View.VISIBLE);
                footerViewHolder.mLoadingIv.setVisibility(View.VISIBLE);
                footerViewHolder.loadMore.setVisibility(View.VISIBLE);
                footerViewHolder.loadMore.setText(hint2);

                animationDrawable = (AnimationDrawable) footerViewHolder.mLoadingIv.getBackground();
                animationDrawable.start();
            }
        } else {
            onBindItemViewHolder(((IVH) holder), position);
        }
    }

    @Override
    public int getItemCount() {
        int count = getContentItemCount() + (hasFooter ? 1 : 0);
        Log.i(TAG, "getItemCount = " + count+", hasFooter = "+hasFooter);
        return count;
    }


    public void setHasMoreData(boolean isMoreData) {
        Log.i("BaseAdapter", "isMoreData="+isMoreData);
        if (this.hasMoreData != isMoreData) {
            Log.i("BaseAdapter", "setHasMoreData notifyDataSetChanged");
            this.hasMoreData = isMoreData;
            notifyDataSetChanged();
        }
    }

    public void setDataList(List<M> data) {
        if (data == null || data.size() == 0)
            return;
        dataList.addAll(data);
        notifyDataSetChanged();
    }

    public M getItemData(int positon) {
        return dataList.get(positon);
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setHint(int resId) {
        hint = mContext.getResources().getString(resId);
    }

    public void setHasFooter(boolean hasFooter) {
        if (this.hasFooter != hasFooter) {
            this.hasFooter = hasFooter;
            notifyDataSetChanged();
        }
    }

    public void setHasMoreDataAndFooter(boolean hasMoreData, boolean hasFooter) {
        Log.i("BaseAdapter", "hasMoreData="+hasMoreData+", hasFooter = "+hasFooter);
        if (this.hasMoreData != hasMoreData || this.hasFooter != hasFooter) {
            Log.i("BaseAdapter", "setHasMoreDataAndFooter notifyDataSetChanged");
            this.hasMoreData = hasMoreData;
            this.hasFooter = hasFooter;
            notifyDataSetChanged();
        }
    }

    //item   ViewHolder 实现
    public abstract IVH onCreateItemViewHolder(ViewGroup parent, int viewType);

    //item   ViewHolder data实现
    public abstract void onBindItemViewHolder(final IVH holder, int position);

}
