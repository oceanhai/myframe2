package com.wuhai.myframe2.ui.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.config.Constants;
import com.wuhai.retrofit.utils.LogUtil;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 作者 wh
 *
 * 创建日期 2019/11/19 9:49
 *
 * 描述：随机数字
 */
public class RandomNumberView extends View {

    /**
     * 文本
     */
    private String mTitleText;

    /**
     * 文本的颜色
     */
    private int mTitleTextColor;

    /**
     * 文本的大小
     */

    private int mTitleTextSize;

    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBounds;
    private Paint mPaint;

    public RandomNumberView(Context context)	{
        this(context, null);
    }

    public RandomNumberView(Context context, AttributeSet attrs)	{
        this(context, attrs, 0);
    }

    /**
     * 获得我自定义的样式属性
     *
     * @param context
     * @param attrs
     * @param defStyle
     */

    public RandomNumberView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);

        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.RandomNumberView, defStyle, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++){
            int attr = a.getIndex(i);
            switch (attr){
                case R.styleable.RandomNumberView_titleText:
                    mTitleText = a.getString(attr);
                    break;
                case R.styleable.RandomNumberView_titleTextColor:
                    // 默认颜色设置为黑色
                    mTitleTextColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.RandomNumberView_titleTextSize:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }

        }

        a.recycle();

        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mBounds = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBounds);
        LogUtil.v(Constants.CUSTOM_VIEW_TAG, "width="+mBounds.width()+",height="+mBounds.height());
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height ;

        if (widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        } else{
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBounds);
            float textWidth = mBounds.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        } else{
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBounds);
            float textHeight = mBounds.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }

        LogUtil.v(Constants.CUSTOM_VIEW_TAG,"onMeasure width="+width+",height="+height);
        setMeasuredDimension(width, height);

    }


    @Override
    protected void onDraw(Canvas canvas){

        //绘制rect
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        //绘制text
        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText, getWidth() / 2 - mBounds.width() / 2,
                getHeight() / 2 + mBounds.height() / 2, mPaint);

        //点击 重新绘制text
        setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                mTitleText = randomText();
                postInvalidate();
            }
        });

    }

    /**
     * 生成随机数
     * @return
     */
    private String randomText(){
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();

        while (set.size() < 4){
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }

        StringBuffer sb = new StringBuffer();
        for (Integer i : set){
            sb.append("" + i);
        }

        return sb.toString();
    }


}
