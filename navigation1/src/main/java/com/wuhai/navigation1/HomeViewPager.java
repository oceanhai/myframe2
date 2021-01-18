package com.wuhai.navigation1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * createby yangzheng
 * date 2016/12/27
 * email yangzhenop@126.com
 */
public class HomeViewPager extends ViewPager {

    private boolean isScrollEnable = false;

    public HomeViewPager(Context context) {
        super(context);
    }

    public HomeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollEnable(boolean enable){
        isScrollEnable = enable;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return isScrollEnable && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return isScrollEnable && super.onInterceptTouchEvent(event);
    }
}