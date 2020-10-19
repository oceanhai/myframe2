package com.wuhai.myframe2.ui.lifecycle;

import android.os.Bundle;
import android.util.Log;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity2;


public class TargetActivity extends BaseActivity2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        Log.v(ThreadStartActivity.TAG, "onCreate-2");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(ThreadStartActivity.TAG, "onRestart-2");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(ThreadStartActivity.TAG, "onStart-2");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(ThreadStartActivity.TAG, "onResume-2");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(ThreadStartActivity.TAG, "onPause-2");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(ThreadStartActivity.TAG, "onStop-2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(ThreadStartActivity.TAG, "onDestroy-2");
    }
}
