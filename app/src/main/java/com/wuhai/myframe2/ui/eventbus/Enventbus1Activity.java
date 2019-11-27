package com.wuhai.myframe2.ui.eventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity2;

/**
 * 左右fragment
 */
public class Enventbus1Activity extends BaseActivity2 {

    public static final String TAG = Enventbus1Activity.class.getSimpleName();

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, Enventbus1Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_enventbus1);
    }
}
