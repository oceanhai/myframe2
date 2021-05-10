package com.wuhai.myframe2.ui.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wuhai.myframe2.R;

public class DrawBitmapView extends View {

    private Bitmap mBitmap;

    public DrawBitmapView(Context context) {
        super(context);
        init();
    }

    public DrawBitmapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawBitmapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat_1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        PointF pointF = new PointF();
        Point point = new Point();
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);


        // 画出原图像
////        canvas.drawBitmap(mBitmap, 0, 0, null);
//        // 指定图片绘制区域(左上角的四分之一)
//        Rect src = new Rect(0,0,mBitmap.getWidth()/2,mBitmap.getHeight()/2);
//        // 指定图片在屏幕上显示的区域(原图大小)
////        Rect dst = new Rect(mBitmap.getWidth()+50,0,mBitmap.getWidth()+
////                50+mBitmap.getWidth(),mBitmap.getHeight());
//        Rect dst = new Rect(0,0,100,100);
//        canvas.drawBitmap(mBitmap, src,dst,null);


        Matrix matrix = new Matrix();
        canvas.drawBitmap(mBitmap, matrix, paint);

        matrix.setTranslate(100, 1000);
        canvas.drawBitmap(mBitmap, matrix, paint);
    }
}
