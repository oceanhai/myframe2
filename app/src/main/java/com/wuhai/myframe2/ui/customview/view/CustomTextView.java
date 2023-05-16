package com.wuhai.myframe2.ui.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * 作者 wh
 * 
 * 创建日期 2023/5/16 14:44
 * 
 * 描述：数值view
 *  示例 28.36%,小数点前字体大，小数点后字体小
 */
public class CustomTextView extends AppCompatTextView {
    private TextPaint bigTextPaint;
    private TextPaint smallTextPaint;
    private float percentTextSize;
    private float decimalTextSize;

    public CustomTextView(Context context) {
        super(context);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 初始化大字体的TextPaint
        bigTextPaint = new TextPaint(getPaint());
        bigTextPaint.setTextSize(getTextSize());

        // 计算小字体的大小
        percentTextSize = getTextSize();
        decimalTextSize = percentTextSize * 0.6f; // 根据需要自行调整大小比例

        // 初始化小字体的TextPaint
        smallTextPaint = new TextPaint(getPaint());
        smallTextPaint.setTextSize(decimalTextSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        String text = getText().toString();
        String[] parts = text.split("\\.");

        // 绘制大字体的部分
        canvas.drawText(parts[0], 0, parts[0].length(), 0, getTextSize(), bigTextPaint);

        // 绘制小字体的部分
        if (parts.length > 1) {
            float decimalWidth = smallTextPaint.measureText(parts[0] + ".");
            canvas.drawText(parts[1], 0, parts[1].length(), decimalWidth, getTextSize(), smallTextPaint);
        }

        super.onDraw(canvas);
    }
}

