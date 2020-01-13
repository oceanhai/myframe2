package com.wuhai.myframe2.ui.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

public class LotteryView extends View {

    public static final String TAG = "LotteryView";

    private Paint paint = new Paint();
    private Paint paintRectF = new Paint();
    private int redColor;//红色
    private int blueColor;//蓝色
    private int verticalLineColor;//竖线颜色

    private float width;//宽
    private float height;//高
    private int y = 7;//宽7等分
    private int spacing = 10;//球之间的间距
    private float circle_width;//=view宽/7
    private float radius;//球的半径

    private RectF textBounds;//球内 文本区域
//    private String text[] = new String[]{"01","02","03","04","11","16","01"};//球内数字
    private String text[];//球内数字

    public LotteryView(Context context) {
        super(context);
        init();
    }

    public LotteryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LotteryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        textBounds = new RectF();
        paintRectF.setColor(Color.BLACK);

        redColor = Color.parseColor("#f10215");
        blueColor = Color.parseColor("#616bf0");
        verticalLineColor = Color.parseColor("#e9ecef");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getMeasuredWidth();
        height = getMeasuredHeight();

        //绘制背景
        drawBackground(canvas);
        //绘制圆
        drawCircle(canvas);
        //绘制文字
        drawText(canvas);
    }

    /**
     * 绘制背景
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {
        /**
         * 绘制白色背景
         */
        paint.setAntiAlias(true);//抗锯齿
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawRect(new RectF(0, 0, width, height), paint);

        paint.reset();//重置 paint
    }

    /**
     * 绘制圆
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        paint.setColor(redColor);
        paint.setStyle(Paint.Style.FILL);//画笔属性是实心圆
//        paint.setStyle(Paint.Style.STROKE);//画笔属性是空心圆
        paint.setStrokeWidth(4);//设置画笔粗细

        /*四个参数：
        参数一：圆心的x坐标
        参数二：圆心的y坐标
        参数三：圆的半径
        参数四：定义好的画笔
        */
        circle_width = width/7/2;
        float circle_heigh = height/2;
        radius = Math.min(circle_width, circle_heigh);
        Log.e(TAG, "circle_width="+circle_width+",circle_heigh="+circle_heigh+",radius="+radius);
        float cx = 0;//圆心x轴位置
        for(int x=0;x<7;x++){
            cx = circle_width+radius*x*2+spacing*x;
            if(x==6){
                //竖线 第六个球圆心x轴位置+球半径+2个间隔距离
                paint.setColor(verticalLineColor);
                canvas.drawLine(circle_width+radius*(x-1)*2+radius+spacing*(x+1), 20,
                        circle_width+radius*(x-1)*2+radius+spacing*(x+1), height-20, paint);

                //第七个球 和第六个球之间间隔为4个spacing
                paint.setColor(blueColor);
                cx = circle_width+radius*x*2+spacing*(x+3);
            }
            canvas.drawCircle(cx, circle_heigh, radius, paint);
        }
    }

    /**
     * 绘制文字
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        if(text == null || text.length==0){
            return;
        }

        paint.setColor(Color.WHITE);
        paint.setTextSize(sp(16));

        for(int x=0; x<text.length;x++){

            if(x != 6){
                textBounds.set(circle_width+radius*x*2+spacing*x-radius,
                        20,
                        circle_width+radius*x*2+spacing*x+radius,
                        height-20);
            }else{
                textBounds.set(circle_width+radius*x*2+spacing*(x+3)-radius,
                        20,
                        circle_width+radius*x*2+spacing*(x+3)+radius,
                        height-20);
            }
//            canvas.drawRect(textBounds, paintRectF);//TODO rectf有颜色时候用来查看textviw位置的
            drawTextInCenterRect(text[x],textBounds,canvas);
        }
    }

    /**
     * 把非标准尺寸转换成标准尺寸
     * @param sp
     * @return
     */
    private int sp(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    private void drawTextInCenterRect(String target, RectF targetRect, Canvas canvas) {
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        /**
         * 两种计算baseline的方法
         */
        int baseline = (int) ((targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2);
        Log.e(TAG,"targetRect.bottom="+targetRect.bottom+",targetRect.top="+targetRect.top+
                ",fontMetrics.bottom="+fontMetrics.bottom+",fontMetrics.top="+fontMetrics.top+",baseline="+baseline);
        float length = paint.measureText(target);//文本长
//        baseline = (int) (targetRect.centerY()-fontMetrics.top/2-fontMetrics.bottom/2);
//        L.e(TAG,"targetRect.centerY()="+targetRect.centerY()+",baseline="+baseline);
        /**
         * targetRect.centerX() - length / 2  => 开始的位置
         */
        canvas.drawText(target,targetRect.centerX() - length / 2,baseline,paint);
    }

    /**
     * 设置数据
     * @param source
     */
    public void setResource(String source){
        if(!TextUtils.isEmpty(source) && source.length() > 0){
            String[] arr = source.split(",");
            text = arr;
            invalidate();
        }
    }

}
