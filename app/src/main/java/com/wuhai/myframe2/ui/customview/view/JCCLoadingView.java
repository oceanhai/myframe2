package com.wuhai.myframe2.ui.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 作者 wh
 *
 * 创建日期 2021/9/1 14:30
 *
 * 描述：
 * 参考文章https://juejin.cn/post/7001756509084319781?utm_source=gold_browser_extension
 *
 */
public class JCCLoadingView extends View {

    private static final String TAG = "JCCLoadingView";

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private int useWidth, minwidth;
    private boolean viewContinue=true,viewContinue1=true;
    private float mSweep,mSweep1;


    public JCCLoadingView(Context context) {
        super(context);
        init();
    }

    public JCCLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public JCCLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void init() {
        initPaint();
    }


    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();        //创建画笔对象
        mPaint.setColor(Color.BLACK);    //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4f);     //设置画笔宽度为10px
        mPaint.setAntiAlias(true);     //设置抗锯齿
        mPaint.setAlpha(255);        //设置画笔透明度
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //取最短边界，正方形
        mWidth = w;
        mHeight = h;
        useWidth = mWidth;
        if (mWidth > mHeight) {
            useWidth = mHeight;
        }
        Log.e(TAG, "mWidth="+mWidth+",mHeight="+mHeight+",useWidth="+useWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将布局分为10份。以minwidth的1，3，5，7，9的倍数为标准点。
        minwidth = useWidth / 10;

        canvas.drawLine(minwidth*5,minwidth*5,minwidth*5+mSweep,minwidth*5+mSweep,mPaint);
        canvas.drawLine(minwidth*5,minwidth*5,minwidth*5-mSweep,minwidth*5-mSweep,mPaint);
        canvas.drawLine(minwidth*5,minwidth*5,minwidth*5+mSweep,minwidth*5-mSweep,mPaint);
        canvas.drawLine(minwidth*5,minwidth*5,minwidth*5-mSweep,minwidth*5+mSweep,mPaint);

        canvas.drawLine(minwidth*5+mSweep,minwidth*5+mSweep,minwidth*5+mSweep,minwidth*5+mSweep-mSweep1,mPaint);
        canvas.drawLine(minwidth*5-mSweep,minwidth*5-mSweep,minwidth*5-mSweep,minwidth*5-mSweep+mSweep1,mPaint);
        canvas.drawLine(minwidth*5+mSweep,minwidth*5-mSweep,minwidth*5+mSweep-mSweep1,minwidth*5-mSweep,mPaint);
        canvas.drawLine(minwidth*5-mSweep,minwidth*5+mSweep,minwidth*5-mSweep+mSweep1,minwidth*5+mSweep,mPaint);

        if (viewContinue&&viewContinue1){
            mSweep += 2;
            if (mSweep > minwidth*2) {
                viewContinue=false;

            }

        }
        if (!viewContinue&&viewContinue1){
            mSweep1 += 4;
            if (mSweep1 > 4*minwidth) {
                viewContinue1=false;
                viewContinue=true;

            }
        }
        if (viewContinue&&!viewContinue1){
            if (mSweep1 <=0) {
                mSweep-=4;
                if (mSweep<0){
                    viewContinue=true;
                    viewContinue1=true;
                }

            }else{
                mSweep1 -= 2;
            }
        }

        Log.e(TAG, "minwidth="+minwidth+",minwidth*5="+minwidth*5);
        Log.e(TAG, "viewContinue="+viewContinue+",viewContinue1="+viewContinue1);
        Log.e(TAG, "mSweep="+mSweep+",mSweep1="+mSweep1);

        //刷新View
        invalidate();
    }
}


