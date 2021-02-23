package com.wuhai.myframe2.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcTestBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：空ac
 */
public class TestActivity extends BaseActivity {

    private AcTestBinding binding;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TestActivity.class);
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
        binding = DataBindingUtil.setContentView(this, R.layout.ac_test);

        int i = 1;
        ClassLoader classLoader = getClassLoader();
        if (classLoader != null) {
            Log.i(TAG, "[onCreate] classLoader " + i + " : " + classLoader.toString());
            while (classLoader.getParent() != null) {
                i++;
                classLoader = classLoader.getParent();
                Log.i(TAG, "[onCreate] classLoader " + i + " : " + classLoader.toString());
            }
        }
    }

    private void setListener() {

    }
}
