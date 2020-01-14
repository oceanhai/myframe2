package com.wuhai.myframe2.ui.homepage.qddgouwu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * createby yangzheng
 * date 2016/12/28
 * email yangzhenop@126.com
 * TODO wuhai
 */
public class CategoryCutTag extends View {

    private static final String TAG = "直降";
    private static int DP_WIDTH = 30;
    private static int DP_HEIGHT = 38;
    private int DP_TAG_TEXT_SIZE = 12;

    private Paint mPaint;
    private int cut;
    private Path mPath;

    public CategoryCutTag(Context context) {
        super(context);
        init();
    }


    public CategoryCutTag(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        cut = -1;
        mPaint = new Paint();
        mPaint.setTextSize(dp(DP_TAG_TEXT_SIZE));
        mPaint.setAntiAlias(true);
        mPath = new Path();
    }

    public void setCut(int cut){
        if(cut > 0){
            this.cut = cut;
            setVisibility(VISIBLE);
            invalidate();
        }else{
            setVisibility(GONE);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(dp(DP_WIDTH), dp(DP_HEIGHT));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(cut > 0){
            drawPath(canvas);
            drawText(canvas);
        }
    }

    private void drawText(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        mPaint.setTypeface(Typeface.DEFAULT);
        drawTextInCenterRect(TAG, new RectF(0,dp(5), dp(DP_WIDTH) , dp(DP_WIDTH) / 2), canvas);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        drawTextInCenterRect(cut+"", new RectF(0, dp(DP_WIDTH) / 2, dp(DP_WIDTH) ,dp(DP_WIDTH)), canvas);
    }

    private void drawPath(Canvas canvas) {
        mPath.moveTo(0, 0);
        mPath.lineTo(0, dp(DP_WIDTH));
        mPath.lineTo(dp(DP_WIDTH) / 2, dp(DP_HEIGHT));
        mPath.lineTo(dp(DP_WIDTH), dp(DP_WIDTH));
        mPath.lineTo(dp(DP_WIDTH), 0);
        mPath.lineTo(0, 0);
        mPaint.setColor(Color.rgb(255,51,51));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(mPath,mPaint);
    }

    private void drawTextInCenterRect(String target, RectF targetRect, Canvas canvas) {
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        int baseline = (int) ((targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2);
        float length = mPaint.measureText(target);
        canvas.drawText(target, targetRect.centerX() - length / 2, baseline, mPaint);
    }

    private int dp(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}