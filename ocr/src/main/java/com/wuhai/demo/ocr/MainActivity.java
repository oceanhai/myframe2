package com.wuhai.demo.ocr;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String FILE_NAME = "tessdata";
    private static final String LANGUAGE_NAME = "chi_sim.traineddata";
    private static final String LANGUAGE_FILE_NAME = "chi_sim";

    
    private ImageView image;
    private TextView text;
    private ProgressDialog mProgressDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.init).setOnClickListener(this);
        findViewById(R.id.add_image).setOnClickListener(this);
        image = findViewById(R.id.image);
        text = findViewById(R.id.text);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCanceledOnTouchOutside(false);

        checkAndRequestPermission();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.init:
                init();
                break;
            case R.id.add_image:
                addImage();
                break;
        }
    }

    private void addImage() {
        PictureSelector.create(this).openGallery(PictureMimeType.ofImage())
                .isCamera(true)
                .loadImageEngine(new GlideEngine())
                .selectionMode(PictureConfig.SINGLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    private void init() {
        //TODO  check 是否有文件了 有直接提示初始化成功
        File outFile = new File(getExternalFilesDir(FILE_NAME), LANGUAGE_NAME);
        if (!outFile.exists()) {
            FileUtils1.getInstance(getApplicationContext())
                    .copyAssetsSingleToSD(LANGUAGE_NAME, outFile.getAbsolutePath())
                    .setFileOperateCallback(new FileUtils1.FileOperateCallback() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(MainActivity.this, "init成功",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailed(String error) {
                            Toast.makeText(MainActivity.this, "init失败",Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkAndRequestPermission() {
        List<String> lackedPermission = new ArrayList<String>();

        if (!(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        // 权限都已经有了，那么直接调用SDK
        if (lackedPermission.size() != 0) {
            // 请求所缺少的权限，在onRequestPermissionsResult中再看是否获得权限，如果获得权限就可以调用SDK，否则不要调用SDK。
            String[] requestPermissions = new String[lackedPermission.size()];
            lackedPermission.toArray(requestPermissions);
            requestPermissions(requestPermissions, 1024);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1024 && hasAllPermissionsGranted(grantResults)) {
            //TODO 获取完所有权限
        } else {
            // 如果用户没有授权，那么应该说明意图，引导用户去设置里面授权。
            Toast.makeText(this, "应用缺少必要的权限！请点击\"权限\"，打开所需要的权限。", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            finish();
        }
    }

    private boolean hasAllPermissionsGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode){
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    if (!selectList.isEmpty()) {
                        LocalMedia media = selectList.get(0);
                        Bitmap bitmap = BitmapFactory.decodeFile(media.getPath());
                        image.setImageBitmap(bitmap);
                        load(bitmap);
                    }
                    break;
            }
        }
    }

    private void load(final Bitmap bitmap) {
        File outFile = new File(getExternalFilesDir(FILE_NAME), LANGUAGE_NAME);
        if (!outFile.exists()) {
            Toast.makeText(this,"找不到tessdata",Toast.LENGTH_SHORT).show();
            return;
        }
        final TessBaseAPI baseApi = new TessBaseAPI();
        baseApi.setDebug(BuildConfig.DEBUG);
        baseApi.init(getExternalFilesDir("").getAbsolutePath(), LANGUAGE_FILE_NAME);
        mProgressDialog.setMessage("文字识别中...");
        mProgressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                baseApi.setImage(bitmap);
                final String textStr = baseApi.getUTF8Text();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(textStr);
                        mProgressDialog.dismiss();
                    }
                });
            }
        }).start();
    }
}
