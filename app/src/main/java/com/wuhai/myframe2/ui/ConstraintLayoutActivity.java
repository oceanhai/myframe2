package com.wuhai.myframe2.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myframe2.R;

/**
 * 1、约束布局
 * 2、清单文件，又追加了一个小测试，scheme和雄安智慧社保相同，h5跳转是会去选择是跳哪一个
 */
public class ConstraintLayoutActivity extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, ConstraintLayoutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_constraint_layout);
    }
}
