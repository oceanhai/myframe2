package com.wuhai.myframe2.ui.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myframe2.R;

/**
 * 自定义控件 集合
 *
 * @author wuhai
 *         create at 2016/7/28 9:52
 */
public class CustomViewActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CustomViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_custom_view);
    }
}
