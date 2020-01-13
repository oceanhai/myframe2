package com.wuhai.lotteryticket.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.databinding.AcNullBinding;


/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：空ac
 */
public class NullActivity extends NewLoadingBaseActivity {


    private String mActivityId;

    //Binding
    private AcNullBinding binding;

    /**
     *
     * @param context
     * @param activityId        活动id
     */
    public static void startActivity(Context context, String activityId) {
        Intent intent = new Intent();
        intent.putExtra("activityId",activityId);
        intent.setClass(context, NullActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        parseIntent();
        init();
        setListener();
    }


    private void initView() {
        View contentView = View.inflate(this, R.layout.ac_null, null);
        setContentView(contentView);//※注意 这是把布局add到父容器里
        binding = DataBindingUtil.bind(contentView);
    }


    private void parseIntent() {
        Intent intent = getIntent();
        if(intent != null){
            mActivityId = intent.getStringExtra("activityId");
        }
    }

    @Override
    public void setStatistical() {

    }

    private void init() {
        setTitle("关联手机号码");

//        setStatusBarColor(0,true);
    }

    private void setListener() {

    }


    @Override
    protected void reloadData() {
        super.reloadData();
    }
}
