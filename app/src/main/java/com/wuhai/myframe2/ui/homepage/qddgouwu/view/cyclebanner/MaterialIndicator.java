package com.wuhai.myframe2.ui.homepage.qddgouwu.view.cyclebanner;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;

import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.wuhai.myframe2.ui.homepage.qddgouwu.util.DimensionUtil;


/**
 * createby yangzheng
 * date 2017/12/22
 * email yangzhenop@126.com
 * TODO wuhai 这也是一个指示器，就是banner图下的点点，不过没用到这个类 用的SimpleLineIndicator
 */
public class MaterialIndicator extends View implements ViewPager.OnPageChangeListener {

    private static final int UNDEFINED_PADDING = -1;
    private final Interpolator interpolator = new FastOutSlowInInterpolator();
    private final Paint indicatorPaint;
    private final Paint selectedIndicatorPaint;
    private final float indicatorRadius;
    private final float indicatorPadding;

    private final RectF selectorRect;
    private int count;
    private int selectedPage = 0;
    private float deselectedAlpha = 0.5f;
    private float offset;

    public MaterialIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaterialIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        selectedIndicatorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        indicatorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        indicatorPaint.setColor(Color.WHITE);
        indicatorPaint.setAlpha((int) (deselectedAlpha * 255));
        selectorRect = new RectF();
        if (isInEditMode()) {
            count = 3;
        }
        indicatorRadius = DimensionUtil.convertDpToPx(context,2.5f);
        indicatorPadding = DimensionUtil.convertDpToPx(context,5f);
        selectedIndicatorPaint.setColor(Color.parseColor("#ffffff"));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        selectedPage = position;
        offset = positionOffset;
        invalidate();
    }

    @Override
    public void onPageSelected(int position) {
        selectedPage = position;
        offset = 0;
        invalidate();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setAdapter(PagerAdapter adapter) {
        this.count = adapter.getCount();
        requestLayout();
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        if (getLayoutParams().width == ViewPager.LayoutParams.WRAP_CONTENT) {
            width = getSuggestedMinimumWidth();
        }
        setMeasuredDimension(width, getSuggestedMinimumHeight());
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return (int) (indicatorDiameter() * count + getInternalPadding());
    }

    private float getInternalPadding() {
        if (indicatorPadding == UNDEFINED_PADDING || indicatorPadding == 0 || count == 0) {
            return 0;
        }
        return indicatorPadding * (count - 1);
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return getPaddingTop() + getPaddingBottom() + (int) indicatorDiameter();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float gap = getGapBetweenIndicators();
        for (int i = 0; i < count; i++) {
            float position = indicatorStartX(gap, i);
            canvas.drawCircle(position + indicatorRadius, midY(), indicatorRadius, indicatorPaint);
        }
        float extenderStart = indicatorStartX(gap, selectedPage) + Math.max(gap * (interpolatedOffset() - 0.5f) * 2, 0);
        float extenderEnd = indicatorStartX(gap, selectedPage) + indicatorDiameter() + Math.min(gap * interpolatedOffset() * 2, gap);
        selectorRect.set(extenderStart, midY() - indicatorRadius, extenderEnd, midY() + indicatorRadius);
        canvas.drawRoundRect(selectorRect, indicatorRadius, indicatorRadius, selectedIndicatorPaint);
    }

    private float getGapBetweenIndicators() {
        if (indicatorPadding == UNDEFINED_PADDING) {
            return (getWidth() - indicatorDiameter()) / (count + 1);
        } else {
            return indicatorPadding;
        }
    }

    private float indicatorStartX(float gap, int page) {
        return ViewCompat.getPaddingStart(this) + gap * page + indicatorRadius;
    }

    private float interpolatedOffset() {
        return interpolator.getInterpolation(offset);
    }

    private float indicatorDiameter() {
        return indicatorRadius * 2;
    }

    private float midY() {
        return getHeight() / 2f;
    }

}
