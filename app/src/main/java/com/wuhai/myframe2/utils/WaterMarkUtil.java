package com.wuhai.myframe2.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;

/**
 * create_user: zhengzaihong
 * Email:1096877329@qq.com
 * create_date: 2018/6/12
 * create_time: 9:44
 * describe 给图片添加水印Util 如果图片过大可能导致 oom，建议配合提供的ImageUtil 压缩到指定大小后添加
 *
 * https://www.yisu.com/zixun/211198.html
 *
 **/
public class WaterMarkUtil {

    public static final String TAG = "WaterMarkUtil";

    /**
     * 设置水印图片在左上角
     * @param context
     * @param src
     * @param watermark
     * @param paddingLeft
     * @param paddingTop
     * @return
     */
    public static Bitmap createWaterMaskLeftTop(
            Context context, Bitmap src, Bitmap watermark,
            int paddingLeft, int paddingTop) {
        return createWaterMaskBitmap(src, watermark,
                dp2px(context, paddingLeft), dp2px(context, paddingTop));
    }

    /**
     * @param src 原图
     * @param watermark 水印图
     * @param paddingLeft 内距 左边距
     * @param paddingTop 内距 上边距
     * @return 返回带有水印的图片
     */

    private static Bitmap createWaterMaskBitmap(Bitmap src, Bitmap watermark,
                                                int paddingLeft, int paddingTop) {
        if (src == null) {
            return null;
        }
        int width = src.getWidth();
        int height = src.getHeight();
        //创建一个bitmap
        Bitmap newb = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        //将该图片作为画布
        Canvas canvas = new Canvas(newb);
        //在画布 0，0坐标上开始绘制原始图片
        canvas.drawBitmap(src, 0, 0, null);
        //在画布上绘制水印图片
        canvas.drawBitmap(watermark, paddingLeft, paddingTop, null);
        // 保存
        canvas.save();
        // 存储
        canvas.restore();
        return newb;
    }

    /**
     * 设置水印图片在右下角
     * @param context
     * @param src
     * @param watermark
     * @param paddingRight
     * @param paddingBottom
     * @return
     */
    public static Bitmap createWaterMaskRightBottom(
            Context context, Bitmap src, Bitmap watermark,
            int paddingRight, int paddingBottom) {
        return createWaterMaskBitmap(src, watermark,
                src.getWidth() - watermark.getWidth() - dp2px(context, paddingRight),
                src.getHeight() - watermark.getHeight() - dp2px(context, paddingBottom));
    }

    /**
     * 设置水印图片到右上角
     *
     * @param context
     * @param src
     * @param watermark
     * @param paddingRight
     * @param paddingTop
     * @return
     */
    public static Bitmap createWaterMaskRightTop(
            Context context, Bitmap src, Bitmap watermark,
            int paddingRight, int paddingTop) {
        return createWaterMaskBitmap(src, watermark,
                src.getWidth() - watermark.getWidth() - dp2px(context, paddingRight),
                dp2px(context, paddingTop));
    }

    /**
     * 设置水印图片到左下角
     * @param context
     * @param src
     * @param watermark
     * @param paddingLeft
     * @param paddingBottom
     * @return
     */
    public static Bitmap createWaterMaskLeftBottom(
            Context context, Bitmap src, Bitmap watermark,
            int paddingLeft, int paddingBottom) {
        return createWaterMaskBitmap(src, watermark, dp2px(context, paddingLeft),
                src.getHeight() - watermark.getHeight() - dp2px(context, paddingBottom));
    }

    /**
     * 设置水印图片到中间
     *
     * @param context
     * @param src
     * @param watermark
     * @return
     */
    public static Bitmap createWaterMaskCenter(Bitmap src, Bitmap watermark) {
        return createWaterMaskBitmap(src, watermark,
                (src.getWidth() - watermark.getWidth()) / 2,
                (src.getHeight() - watermark.getHeight()) / 2);
    }

    /**
     * 给图片添加文字到左上角
     *
     * @param context
     * @param bitmap
     * @param text
     * @return
     */
    public static Bitmap drawTextToLeftTop(Context context, Bitmap bitmap, String text,
                                           int size, int color, int paddingLeft, int paddingTop) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setTextSize(dp2px(context, size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds,
                dp2px(context, paddingLeft),
                dp2px(context, paddingTop) + bounds.height());
    }

    /**
     * 绘制文字到右下角
     *
     * @param context
     * @param bitmap
     * @param text
     * @param size
     * @param color
     * @param paddingRight
     * @param paddingBottom
     * @return
     */
    public static Bitmap drawTextToRightBottom(Context context, Bitmap bitmap, String text,
                                               int size, int color, int paddingRight, int paddingBottom) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setTextSize(dp2px(context, size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds,
                bitmap.getWidth() - bounds.width() - dp2px(context, paddingRight),
                bitmap.getHeight() - dp2px(context, paddingBottom));
    }

    /**
     * 绘制文字到右上方
     *
     * @param context
     * @param bitmap
     * @param text
     * @param size
     * @param color
     * @param paddingRight
     * @param paddingTop
     * @return
     */
    public static Bitmap drawTextToRightTop(Context context, Bitmap bitmap, String text,
                                            int size, int color, int paddingRight, int paddingTop) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setTextSize(dp2px(context, size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds,
                bitmap.getWidth() - bounds.width() - dp2px(context, paddingRight),
                dp2px(context, paddingTop) + bounds.height());
    }

    /**
     * 绘制文字到左下方
     *
     * @param context
     * @param bitmap
     * @param text
     * @param size
     * @param color
     * @param paddingLeft
     * @param paddingBottom
     * @return
     */
    public static Bitmap drawTextToLeftBottom(Context context, Bitmap bitmap, String text,
                                              int size, int color, int paddingLeft, int paddingBottom) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setTextSize(dp2px(context, size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds,
                dp2px(context, paddingLeft),
                bitmap.getHeight() - dp2px(context, paddingBottom));
    }

    /**
     * 绘制文字到中间
     *
     * @param context
     * @param bitmap
     * @param text
     * @param size
     * @param color
     * @return
     */
    public static Bitmap drawTextToCenter(Context context, Bitmap bitmap, String text,
                                          int size, int color) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setTextSize(dp2px(context, size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds,
                (bitmap.getWidth() - bounds.width()) / 2,
                (bitmap.getHeight() + bounds.height()) / 2);
    }

    //图片上绘制文字
    private static Bitmap drawTextToBitmap(Context context, Bitmap bitmap, String text,
                                           Paint paint, Rect bounds, int paddingLeft, int paddingTop) {
        Bitmap.Config bitmapConfig = bitmap.getConfig();

        paint.setDither(true); // 获取跟清晰的图像采样
        paint.setFilterBitmap(true);// 过滤一些
        if (bitmapConfig == null) {
            bitmapConfig = Bitmap.Config.ARGB_8888;
        }
        bitmap = bitmap.copy(bitmapConfig, true);
        Canvas canvas = new Canvas(bitmap);

        canvas.drawText(text, paddingLeft, paddingTop, paint);
        return bitmap;
    }

    /**
     * TODO wuhai
     *
     * @return
     */
    public static Bitmap drawTextSlanting(Context context, Bitmap src, String text) {

        if (src == null) {
            return null;
        }
        int width = src.getWidth();
        int height = src.getHeight();
        //创建一个bitmap
        Bitmap newb = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        //将该图片作为画布
        Canvas canvas = new Canvas(newb);
        //在画布 0，0坐标上开始绘制原始图片
        canvas.drawBitmap(src, 0, 0, null);
        //获取水印bitmap
        Bitmap watermark = getMarkTextBitmap(context, text, src.getWidth(), src.getHeight(), true);
        //在画布上绘制水印图片
        canvas.drawBitmap(watermark, 0, 0, null);
        // 保存
        canvas.save();
        // 存储
        canvas.restore();
        return newb;

    }

    /**
     * TODO 待检验
     * @param gContext
     * @param gText
     * @param width
     * @param height
     * @param is4Showing
     * @return
     */
    public static Bitmap getMarkTextBitmap(Context gContext, String gText, int width, int height, boolean is4Showing){

        float textSize;
        float inter;
        if (is4Showing){
            textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                    18, gContext.getResources().getDisplayMetrics());
            inter = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    25, gContext.getResources().getDisplayMetrics());
        } else {
            textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                    54, gContext.getResources().getDisplayMetrics());
            inter = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    75, gContext.getResources().getDisplayMetrics());
        }

        int sideLength;
        if (width > height) {
            sideLength = (int) Math.sqrt(2*(width * width));
        } else {
            sideLength = (int) Math.sqrt(2*(height * height));
        }
        Log.e(TAG, "sideLength="+sideLength);


        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Rect rect = new Rect();
        paint.setTextSize(textSize);
        //获取文字长度和宽度
        paint.getTextBounds(gText, 0, gText.length(), rect);

        int strwid = rect.width();
        int strhei = rect.height();
        Log.e(TAG, "strwid="+strwid+",strhei="+strhei);

        Bitmap markBitmap = null;
        try {
            markBitmap = Bitmap.createBitmap(sideLength, sideLength, Bitmap.Config.ARGB_4444);
            Canvas canvas = new Canvas(markBitmap);
            //创建透明画布
            canvas.drawColor(Color.TRANSPARENT);

            paint.setColor(Color.BLACK);
            paint.setAlpha((int) (0.1*255f));
            // 获取跟清晰的图像采样
            paint.setDither(true);
            paint.setFilterBitmap(true);

            //先平移，再旋转才不会有空白，使整个图片充满
            if (width > height) {
                canvas.translate(width - sideLength - inter, sideLength - width + inter);
            } else {
                canvas.translate(height - sideLength - inter, sideLength - height + inter);
            }

            //将该文字图片逆时针方向倾斜45度
            canvas.rotate(-45);

            for (int i =0; i <= sideLength; ){
                int count = 0;
                for (int j =0; j <= sideLength; count++){
                    if (count % 2 == 0){
                        canvas.drawText(gText, i, j, paint);
                    } else {
                        //偶数行进行错开
                        canvas.drawText(gText, i + strwid/2, j, paint);
                    }
                    j = (int) (j + inter + strhei);
                }
                i = (int) (i + strwid + inter);
            }
            canvas.save();
        } catch (OutOfMemoryError e) {
            if(markBitmap != null && !markBitmap.isRecycled()){
                markBitmap.recycle();
                markBitmap = null;
            }
        }

        return markBitmap;
    }

    /**
     * TODO 待检验
     * 获得文字水印的图片
     * @param width
     * @param height
     * @return
     */
    public static Drawable getMarkTextBitmapDrawable(Context gContext, String gText,
                                                     int width, int height, boolean is4Showing){
        Bitmap bitmap = getMarkTextBitmap(gContext, gText, width, height, is4Showing);
        if (bitmap != null){
            BitmapDrawable drawable = new BitmapDrawable(gContext.getResources(), bitmap);
            drawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            drawable.setDither(true);
            return drawable;
        }
        return null;
    }

    /**
     * 缩放图片
     *
     * @param src
     * @param w
     * @param h
     * @return
     */
    public static Bitmap scaleWithWH(Bitmap src, double w, double h) {
        if (w == 0 || h == 0 || src == null) {
            return src;
        } else {
            // 记录src的宽高
            int width = src.getWidth();
            int height = src.getHeight();
            // 创建一个matrix容器
            Matrix matrix = new Matrix();
            // 计算缩放比例
            float scaleWidth = (float) (w / width);
            float scaleHeight = (float) (h / height);
            // 开始缩放
            matrix.postScale(scaleWidth, scaleHeight);
            // 创建缩放后的图片
            return Bitmap.createBitmap(src, 0, 0, width, height, matrix, true);
        }
    }

    /**
     * dip转pix
     * @param context
     * @param dp
     * @return
     */
    public static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
