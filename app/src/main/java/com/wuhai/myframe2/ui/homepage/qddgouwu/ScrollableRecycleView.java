package com.wuhai.myframe2.ui.homepage.qddgouwu;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.RecyclerView;

/**
 * createby yangzheng
 * date 2017/1/10
 * email yangzhenop@126.com
 */
public class ScrollableRecycleView extends RecyclerView {

    private OnRecycleViewScrollListener listener;

    public ScrollableRecycleView(Context context) {
        super(context);
    }

    public ScrollableRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollableRecycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (listener != null) {
            listener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    public void setOnRecycleViewScrollListener(OnRecycleViewScrollListener onScrollListener) {
        this.listener = onScrollListener;
    }

    public interface OnRecycleViewScrollListener{
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }
}