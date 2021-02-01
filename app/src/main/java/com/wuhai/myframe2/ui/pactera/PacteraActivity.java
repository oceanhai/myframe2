package com.wuhai.myframe2.ui.pactera;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcPecteraBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：一些公司里碰到没接触过的知识点 研究
 */
public class PacteraActivity extends BaseActivity implements View.OnClickListener {

    private AcPecteraBinding binding;

    //图片 路径
    private String photo_path;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, PacteraActivity.class);
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
        binding = DataBindingUtil.setContentView(this, R.layout.ac_pectera);
    }

    private void setListener() {
        binding.tv01.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv01:
                pickPictureFromAblum();
                break;
        }
    }

    /**
     * 获取带二维码的相片进行扫描
     *
     * 相关文章
     * https://blog.csdn.net/m0_37559046/article/details/55517618
     *
     */
    public void pickPictureFromAblum() {
        //  打开手机中的相册
//        Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT);//错误的
        Intent innerIntent = new Intent(Intent.ACTION_PICK);//正确的
        innerIntent.setType("image/*");
        Intent wrapperIntent = Intent.createChooser(innerIntent, "选择二维码图片");
        this.startActivityForResult(wrapperIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        if (!TextUtils.isEmpty(uri.getAuthority())) {

            Cursor cursor = getContentResolver().query(uri, new String[] { MediaStore.Images.Media.DATA }, null, null, null);
            if (null == cursor) {
                Toast.makeText(this, "图片没找到", Toast.LENGTH_SHORT).show();
                return;
            }

            cursor.moveToFirst();
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            photo_path = cursor.getString(column_index);
            Log.e(TAG, "photo_path-1 = " + photo_path);
            cursor.close();
        } else {
            photo_path = data.getData().getPath();
            Log.e(TAG, "photo_path-2 = " + photo_path);
        }

    }
}
