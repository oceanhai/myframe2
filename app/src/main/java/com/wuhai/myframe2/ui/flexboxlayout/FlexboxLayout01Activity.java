package com.wuhai.myframe2.ui.flexboxlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：谷歌FlexboxLayout
 */
public class FlexboxLayout01Activity extends BaseActivity {

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, FlexboxLayout01Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_flexbox_layout01);
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

    }

    private void setListener() {

    }
}
