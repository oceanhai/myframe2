package com.wuhai.hhsc.widget.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by zhudi on 2015/9/18.
 * android Viewpager禁用/开启滑动切换功能
 */
public class CustomViewPager extends ViewPager {
    /**
     * true 表示禁止滑动，false表示允许滑动
     */
    private boolean isCanScroll = false;
    /**
     * 是否为动态计算测量
     */
    private boolean isDynamicOnMeasure;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (isCanScroll) {
            return false;
        } else {
            try {
                return super.onInterceptTouchEvent(event);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isCanScroll) {
            return false;
        }
        return super.onTouchEvent(ev);
    }


    public boolean isCanScroll() {
        return isCanScroll;
    }

    public void setIsCanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    public void setDynamicOnMeasure(boolean dynamicOnMeasure) {
        isDynamicOnMeasure = dynamicOnMeasure;
    }

    //去除页面切换时的效果
    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        // TODO Auto-generated method stub
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        // TODO Auto-generated method stub
        super.setCurrentItem(item, false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isDynamicOnMeasure) {
            int childCount = getChildCount();
            int h = 0;
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                //1.测量子View的宽高
                child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                //2.获取view的高度
                int measuredHeight = child.getMeasuredHeight();
                //3.取所有的子View中的高度最高的那个
                if (measuredHeight > h) {
                    h = measuredHeight;
                }

            }
            //4、最后设置高度的测量模式为EXACTLY
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
