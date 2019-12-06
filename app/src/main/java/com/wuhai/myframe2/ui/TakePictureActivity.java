package com.wuhai.myframe2.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.wuhai.myframe2.BuildConfig;
import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity;
import com.wuhai.myframe2.utils.ImageUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 wuhai
 * <p>
 * 创建日期 2019/4/3 11:45
 * <p>
 * 描述：TAKE_PICTURE(拍照 测试myhydemo为啥崩溃)
 */
public class TakePictureActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btn01)
    Button btn01;

    public static final int IMAGE_PICKER_FROM_CAMERA = 0;
    @BindView(R.id.image_view01)
    ImageView imageView01;

    //照相 路径
    private String mAvatarPhotoPath;
    //uri
    private Uri mUri;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TakePictureActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_take_picture);
        ButterKnife.bind(this);
        parseIntent();
        init();
        setListener();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if (intent != null) {

        }
    }

    @Override
    public void setStatistical() {

    }

    private void init() {

    }

    private void setListener() {
        btn01.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01://拍照
                takePicture();
                break;
        }
    }

    private void takePicture() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "D卡不存在，不能拍照", Toast.LENGTH_SHORT).show();
            return;
        }

        mAvatarPhotoPath = ImageUtil.getImageFilePath(getContext(), System.currentTimeMillis() + ".jpg");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mUri = FileProvider.getUriForFile(this,
                    BuildConfig.APPLICATION_ID + ".fileprovider", new File(mAvatarPhotoPath));
        } else {
            mUri = Uri.parse("file://" + mAvatarPhotoPath);
        }
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        startActivityForResult(openCameraIntent, IMAGE_PICKER_FROM_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case IMAGE_PICKER_FROM_CAMERA:
                    Bitmap image = null;
                    if (data != null) {//不传路径时，拍照直接返回
                        image = (Bitmap) data.getExtras().get("data");
                    } else {//传路径时
                        image = ImageUtil.getImage(mAvatarPhotoPath);
                    }
                    imageView01.setImageBitmap(image);
                    break;
            }
        }
    }
}
