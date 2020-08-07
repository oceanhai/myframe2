package com.wuhai.myframe2.ui.bitmap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcBitmapBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：bitmap 的一些操作
 */
public class BitmapActivity extends BaseActivity implements View.OnClickListener {

    private AcBitmapBinding binding;

    private float ratio;
    private int alpha, beta;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, BitmapActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ac_null);
        parseIntent();
        init();
        setListener();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if(intent != null){

        }
    }

    @Override
    public void setStatistical() {

    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.ac_bitmap);

        binding.originalIv.setImageDrawable(getResources().getDrawable(R.drawable.face01));
    }

    private void setListener() {
        binding.btn1.setOnClickListener(this);
        binding.btn2.setOnClickListener(this);
        binding.btn3.setOnClickListener(this);
        binding.btn4.setOnClickListener(this);
        binding.btn5.setOnClickListener(this);
        binding.btn6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bitmap originBM = BitmapFactory.decodeResource(getResources(),
                R.drawable.face01);
        switch (v.getId()) {
            case R.id.btn1: {// 按尺寸缩放
                Bitmap nBM = scaleBitmap(originBM, 100, 72);
                binding.newIv.setImageBitmap(nBM);
                break;
            }
            case R.id.btn2: {// 按比例缩放，每次点击缩放比例都会不同
                if (ratio < 3) {
                    ratio += 0.05f;
                } else {
                    ratio = 0.1f;
                }
                Bitmap nBM = scaleBitmap(originBM, ratio);
                binding.newIv.setImageBitmap(nBM);
                break;
            }
            case R.id.btn3: {// 裁剪
                Bitmap cropBitmap = cropBitmap(originBM);
                binding.newIv.setImageBitmap(cropBitmap);
                break;
            }
            case R.id.btn4: {// 顺时针旋转效果；每次点击更新旋转角度
                if (alpha < 345) {
                    alpha += 15;
                } else {
                    alpha = 0;
                }
                Bitmap rotateBitmap = rotateBitmap(originBM, alpha);
                binding.newIv.setImageBitmap(rotateBitmap);
                break;
            }
            case R.id.btn5: {// 逆时针旋转效果；每次点击更新旋转角度
                if (beta > 15) {
                    beta -= 15;
                } else {
                    beta = 360;
                }
                Bitmap rotateBitmap = rotateBitmap(originBM, beta);
                binding.newIv.setImageBitmap(rotateBitmap);
                break;
            }
            case R.id.btn6: {// 偏移效果；偏移量在方法中
                Bitmap skewBM = skewBitmap(originBM);
                binding.newIv.setImageBitmap(skewBM);
                break;
            }
        }
    }

    /**
     * 根据给定的宽和高进行拉伸
     *
     * @param origin    原图
     * @param newWidth  新图的宽
     * @param newHeight 新图的高
     * @return new Bitmap
     */
    private Bitmap scaleBitmap(Bitmap origin, int newWidth, int newHeight) {
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);// 使用后乘
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (!origin.isRecycled()) {
            origin.recycle();
        }
        return newBM;
    }

    /**
     * 按比例缩放图片
     *
     * @param origin 原图
     * @param ratio  比例
     * @return 新的bitmap
     */
    private Bitmap scaleBitmap(Bitmap origin, float ratio) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(ratio, ratio);
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }

    /**
     * 裁剪
     *
     * @param bitmap 原图
     * @return 裁剪后的图像
     */
    private Bitmap cropBitmap(Bitmap bitmap) {
        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();
        int cropWidth = w >= h ? h : w;// 裁切后所取的正方形区域边长
        cropWidth /= 2;
        int cropHeight = (int) (cropWidth / 1.2);
        return Bitmap.createBitmap(bitmap, w / 3, 0, cropWidth, cropHeight, null, false);
    }

    /**
     * 选择变换
     *
     * @param origin 原图
     * @param alpha  旋转角度，可正可负
     * @return 旋转后的图片
     */
    private Bitmap rotateBitmap(Bitmap origin, float alpha) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(alpha);
        // 围绕原地进行旋转
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }

    /**
     * 偏移效果
     * @param origin 原图
     * @return 偏移后的bitmap
     */
    private Bitmap skewBitmap(Bitmap origin) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.postSkew(-0.6f, -0.3f);
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }
}
