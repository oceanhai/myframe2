package com.wuhai.myframe2.ui.xingneng.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DroidCard {

    public int x;//左侧绘制起点
    public int width;
    public int height;
    public Bitmap bitmap;

    public DroidCard(Resources res, int resId, int x){
        this.bitmap = BitmapFactory.decodeResource(res,resId);
        this.x = x;
        this.width = this.bitmap.getWidth();
        this.height = this.bitmap.getHeight();
    }
}

