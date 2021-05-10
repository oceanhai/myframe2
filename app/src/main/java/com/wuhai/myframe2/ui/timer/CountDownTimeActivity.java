package com.wuhai.myframe2.ui.timer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcCountDownTimeBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;
import com.wuhai.myframe2.ui.timer.countdowntimer.CountDownTimerSupport;
import com.wuhai.myframe2.ui.timer.countdowntimer.OnCountDownTimerListener;
import com.wuhai.myframe2.ui.xywy.widget.CountDownTimeUtil;
import com.wuhai.retrofit.utils.LogUtil;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：倒计时
 */
public class CountDownTimeActivity extends BaseActivity implements View.OnClickListener {

    private AcCountDownTimeBinding binding;

    //倒计时
    private CountDownTimeUtil count;

    //倒计时 可暂停 TODO 这是雄安社保用到的一个可暂停的倒计时
    private CountDownTimerSupport mCountDownTimer;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, CountDownTimeActivity.class);
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
        binding = DataBindingUtil.setContentView(this, R.layout.ac_count_down_time);

        if (mCountDownTimer == null) {
            mCountDownTimer = new CountDownTimerSupport(60000, 1000);
        }

        mCountDownTimer.setOnCountDownTimerListener(new OnCountDownTimerListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                String time = millisUntilFinished / 1000 + "s";
                LogUtil.e(TAG, "mCountDownTimer onTick "+time);
            }

            @Override
            public void onFinish() {
                LogUtil.e(TAG, "mCountDownTimer onFinish ");
            }

            @Override
            public void onCancel() {
                LogUtil.e(TAG, "mCountDownTimer onCancel ");
            }
        });
        mCountDownTimer.start();
    }

    private void setListener() {
        binding.tv01.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv01:
                if (count == null) {
                    count = new CountDownTimeUtil(60 * 1000, 1000,
                            binding.tv01);
                }
                count.start();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(count != null){
            count.start();
        }

        if (mCountDownTimer != null) {
            mCountDownTimer.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(count != null) {
            count.cancel();
        }

        if (mCountDownTimer != null) {
            mCountDownTimer.pause();
        }
    }

}
