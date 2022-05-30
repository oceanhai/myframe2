package com.wuhai.myframe2.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcWatermarkBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;
import com.wuhai.myframe2.utils.DateUtils;
import com.wuhai.myframe2.utils.WaterMarkUtil;
import com.wuhai.myframe2.utils.WaterMarkUtil2;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：水印照片
 */
public class WaterMarkActivity extends BaseActivity implements View.OnClickListener {

    private AcWatermarkBinding binding;

    private Bitmap sourBitmap;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, WaterMarkActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ac_null);
        parseIntent();
        init();
        setListener();
        initData();
    }

    private void initData() {
        sourBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat_1);
        binding.iv01.setImageBitmap(sourBitmap);
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
        binding = DataBindingUtil.setContentView(this, R.layout.ac_watermark);
    }

    private void setListener() {
        binding.btn01.setOnClickListener(this);
        binding.btn02.setOnClickListener(this);
        binding.btn03.setOnClickListener(this);
        binding.btn04.setOnClickListener(this);
        binding.btn05.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                addWatermark();
                break;
            case R.id.btn02:
                addWatermarkText();
                break;
            case R.id.btn03:
                addWatermarkText2();
                break;
            case R.id.btn04:
                addWatermarkText3();
                break;
            case R.id.btn05:
                addWatermarkText5();
                break;
        }
    }

    /**
     * 最终版
     * 1、绘制倾斜文案的bitmap
     * 2、在原图上绘制倾斜bitmap 生成新的bitmap
     */
    private void addWatermarkText5() {
        Bitmap bitmap = WaterMarkUtil.drawTextSlanting(this,sourBitmap,
                DateUtils.getDateString(null, System.currentTimeMillis()));
        binding.iv02.setImageBitmap(bitmap);
    }

    /**
     * WaterMarkUtil2
     */
    private void addWatermarkText3() {
        //sourBitmap.getWidth()=672,sourBitmap.getHeight()=447
        Log.e(TAG, "sourBitmap.getWidth()="+sourBitmap.getWidth()+
                ",sourBitmap.getHeight()="+sourBitmap.getHeight());
        Bitmap bitmap = WaterMarkUtil2.INSTANCE.getMarkTextBitmap(this,
                DateUtils.getDateString(null, System.currentTimeMillis()),
                sourBitmap.getWidth(), sourBitmap.getHeight());
        binding.iv02.setImageBitmap(bitmap);
    }

    /**
     * WaterMarkUtil 绘制倾斜bitmap
     */
    private void addWatermarkText2() {
        Log.e(TAG, "sourBitmap.getWidth()="+sourBitmap.getWidth()+
                ",sourBitmap.getHeight()="+sourBitmap.getHeight());
        Bitmap bitmap = WaterMarkUtil.getMarkTextBitmap(this,
                DateUtils.getDateString(null, System.currentTimeMillis()),
                sourBitmap.getWidth(), sourBitmap.getHeight(), true);
        binding.iv02.setImageBitmap(bitmap);
    }

    /**
     * WaterMarkUtil  绘制文字
     */
    private void addWatermarkText() {
        Bitmap textBitmap = WaterMarkUtil.
                drawTextToLeftTop(this, sourBitmap,
                        DateUtils.getDateString(null, System.currentTimeMillis()),
                        16, Color.GRAY, 10, 10);
        binding.iv02.setImageBitmap(textBitmap);
    }

    /**
     * WaterMarkUtil 
     */
    private void addWatermark() {

        Bitmap waterBitmap = BitmapFactory.
                decodeResource(getResources(), R.drawable.ic_share_wechat_friend_circle);

        Bitmap watermarkBitmap = WaterMarkUtil.createWaterMaskCenter(sourBitmap, waterBitmap);
        watermarkBitmap = WaterMarkUtil.
                createWaterMaskLeftBottom(this, watermarkBitmap, waterBitmap, 0, 0);
        watermarkBitmap = WaterMarkUtil.
                createWaterMaskRightBottom(this, watermarkBitmap, waterBitmap, 0, 0);
        watermarkBitmap = WaterMarkUtil.
                createWaterMaskLeftTop(this, watermarkBitmap, waterBitmap, 0, 0);
        watermarkBitmap = WaterMarkUtil.
                createWaterMaskRightTop(this, watermarkBitmap, waterBitmap, 0, 0);

        Bitmap textBitmap = WaterMarkUtil.
                drawTextToLeftTop(this, watermarkBitmap, "左上角", 16, Color.RED, 0, 0);
        textBitmap = WaterMarkUtil.
                drawTextToRightBottom(this, textBitmap, "右下角", 16, Color.RED, 0, 0);
        textBitmap = WaterMarkUtil.
                drawTextToRightTop(this, textBitmap, "右上角", 16, Color.RED, 0, 0);
        textBitmap = WaterMarkUtil.
                drawTextToLeftBottom(this, textBitmap, "左下角", 16, Color.RED, 0, 0);
        textBitmap = WaterMarkUtil.
                drawTextToCenter(this, textBitmap, "中间", 16, Color.RED);

        binding.iv02.setImageBitmap(textBitmap);
    }
}
