package com.wuhai.lotteryticket.ui.base;

import android.content.Context;
import android.os.Bundle;

import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;
import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.utils.StatusBarUtil;


/**
 * BaseActivity
 */
public abstract class BaseActivity extends RxFragmentActivity {

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

//            PushServiceWrapperNew.onAppStart();

        TAG = getClass().getSimpleName();
        statistical = TAG;//默认名 ※可在setStatistical() 根据需求在每个页面里设置新id

        //新的 沉浸式
        setStatusBar();
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary),0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setStatistical();
//            StatisticalTools.onResume(this, statistical);
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
//            StatisticalTools.onPause(this, statistical);
    }

    /**
     * ac 销毁时候，取消订阅
     * @return
     */
    public LifecycleTransformer getLifecycleTransformer(){
        return this.bindUntilEvent(ActivityEvent.DESTROY);
    }


}
