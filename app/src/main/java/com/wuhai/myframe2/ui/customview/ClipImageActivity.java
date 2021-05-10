package com.wuhai.myframe2.ui.customview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.customview.view.ClipImageView;

/**
 * 裁剪页
 */
public class ClipImageActivity extends Activity {

    private ClipImageView imageView;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ClipImageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_clip_image);

        initView();
        imageView.setBitmapData(BitmapFactory.decodeResource(getResources(), R.drawable.cat_1));

    }

    private void initView() {
        imageView = findViewById(R.id.process_img);
        imageView.setWidthAndHeight(358, 441);
    }

}
