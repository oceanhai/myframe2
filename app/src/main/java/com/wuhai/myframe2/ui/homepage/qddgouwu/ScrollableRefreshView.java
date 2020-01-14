package com.wuhai.myframe2.ui.homepage.qddgouwu;

import android.content.Context;
import android.util.AttributeSet;

/**
 * createby yangzheng
 * date 2017/1/10
 * email yangzhenop@126.com
 */
public class ScrollableRefreshView extends com.chanven.lib.cptr.PtrClassicFrameLayout {

    private OnScrollListener onScrollListener;

    public ScrollableRefreshView(Context context) {
        super(context);
    }

    public ScrollableRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollableRefreshView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener != null) {
            onScrollListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public interface OnScrollListener{
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }
}