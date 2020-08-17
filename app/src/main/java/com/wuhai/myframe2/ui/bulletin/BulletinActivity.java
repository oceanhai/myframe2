package com.wuhai.myframe2.ui.bulletin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcBulletinBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：空ac
 */
public class BulletinActivity extends BaseActivity implements RollingBulletinView.OnPositionClickListener {

    private AcBulletinBinding binding;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, BulletinActivity.class);
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
        binding = DataBindingUtil.setContentView(this, R.layout.ac_bulletin);

        String[] arr = new String[]{"我是公告1","我是公告2","我是公告3",
                "我是公告我是公告我是公告我是公告我是公告我是公告我是公告我是公告我是公告我是公告我是公告"};
//        String[] arr = new String[]{"我是公告1"};

        binding.rollingBulletin.setTextArraysAndClickListener(arr,this);
    }

    private void setListener() {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.rollingBulletin.releaseResources();
    }

    @Override
    public void onClick(int postion) {
        Log.e(TAG, "点击了第"+postion+"公告");
        Toast.makeText(this, "点击了第"+postion+"公告", Toast.LENGTH_SHORT).show();
    }
}
