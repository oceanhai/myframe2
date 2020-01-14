package com.wuhai.myframe2.ui.homepage.qddgouwu.view.cyclebanner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.wuhai.myframe2.ui.homepage.qddgouwu.util.DimensionUtil;


/**
 * createby yangzheng
 * date 2017/1/5
 * email yangzhenop@126.com
 */
public class SimpleLineIndicator extends View {

    private Paint mPaint = new Paint();
    private int mFocusColor;
    private int mNormalColor;
    private int mNumber;
    private int position;

    public SimpleLineIndicator(Context context) {
        super(context);
        init();
    }


    public SimpleLineIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mFocusColor = Color.parseColor("#ff6666");
        mNormalColor = Color.parseColor("#e6e9ed");
    }

    public void setNumber(int number){
        mNumber = number;
        invalidate();
    }

    public void setPosition(int position) {
        if(mNumber > 0){
            this.position = position % mNumber;
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if(changed){
            layout(left, top, right, bottom);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, (int) DimensionUtil.convertDpToPx(getContext(),1f));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mNumber >= 1){
            drawIndicator(canvas);
        }
    }

    private void drawIndicator(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(getMeasuredHeight());
        mPaint.setColor(mNormalColor);
        canvas.drawLine(0, getMeasuredHeight() / 2, getMeasuredWidth(), getMeasuredHeight() / 2, mPaint);
        mPaint.setColor(mFocusColor);
        float left = getMeasuredWidth() * position / mNumber;
        canvas.drawLine(left,getMeasuredHeight()/2, left + getMeasuredWidth() / mNumber,getMeasuredHeight()/2, mPaint);
    }
}