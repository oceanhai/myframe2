package com.wuhai.myframe2.ui.customview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.customview.utils.ImageUtil;
import com.wuhai.myframe2.ui.customview.utils.StringUtils;
import com.wuhai.myframe2.ui.customview.view.ClipImageView;

import java.io.File;
import java.util.Calendar;
import java.util.Locale;

import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * 裁剪页
 */
public class ClipImageActivity extends Activity {

    private ClipImageView imageView;
    private FrameLayout btnConfirm;
    private FrameLayout btnBack;

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

        btnConfirm = findViewById(R.id.btn_confirm);
        btnBack = findViewById(R.id.btn_back);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView.getDrawable() != null) {
//                    btnConfirm.setEnabled(false);
                    confirm(imageView.clipImage());
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void confirm(Bitmap bitmap) {
        String imagePath = null;
        if (bitmap != null) {
            String name = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.getDefault())).toString();
            String path = ImageUtil.getImageCacheDir(this);
            imagePath = ImageUtil.saveImage(bitmap, path, name);
            bitmap.recycle();
            bitmap = null;
        }

        if (StringUtils.isNotEmptyString(imagePath)) {
            luban(imagePath);
        }
    }

    /**
     * 压缩
     *
     * @param imagePath
     */
    private void luban(String imagePath) {
        Luban.with(this)
                .load(imagePath)
                .ignoreBy(80)//图片小于80kb 忽略不进行压缩
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        Log.d("luban", "压缩前图片大小=" + ImageUtil
                                .getReadableFileSize(new File(imagePath).length()));
                    }

                    @Override
                    public void onSuccess(File file) {
                        Log.d("luban", "file==" + file);
                        Log.d("luban", "压缩后图片大小=" + ImageUtil.getReadableFileSize(file.length()));
//                        Bitmap bitmap = CompressHelper.getDefault(WebviewActivity.this).compressToBitmap(file);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                }).launch();
    }
}
