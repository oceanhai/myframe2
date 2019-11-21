package com.wuhai.myframe2.ui.base;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;


/**
 * BaseActivity
 */
public abstract class BaseActivity extends FragmentActivity {

    private Context mContext;

    public static String TAG = "";

    /**
     * 统计id
     */
    public String statistical = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

//        PushServiceWrapperNew.onAppStart();

        TAG = getClass().getSimpleName();
        statistical = TAG;//默认名 ※可在setStatistical() 根据需求在每个页面里设置新id

    }

    @Override
    protected void onResume() {
        super.onResume();
        setStatistical();
//        StatisticalTools.onResume(this, statistical);
    }

    /**
     * 给统计赋值
     */
    public abstract void setStatistical();

    protected Context getContext() {
        return mContext;
    }

    @Override
    protected void onPause() {
        super.onPause();
//        StatisticalTools.onPause(this, statistical);
    }

}
