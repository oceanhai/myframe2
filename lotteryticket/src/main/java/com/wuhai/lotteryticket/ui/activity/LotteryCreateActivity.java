package com.wuhai.lotteryticket.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.databinding.AcLotteryCreateBinding;
import com.wuhai.lotteryticket.ui.base.NewLoadingBaseActivity;


/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：彩票生成
 */
public class LotteryCreateActivity extends NewLoadingBaseActivity {

    //Binding
    private AcLotteryCreateBinding binding;

    /**
     *
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LotteryCreateActivity.class);
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
        View contentView = View.inflate(this, R.layout.ac_lottery_create, null);
        setContentView(contentView);//※注意 这是把布局add到父容器里
        binding = DataBindingUtil.bind(contentView);
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
        setTitle("双球球计算器");
        setActionRightText("记录");

//        setStatusBarColor(0,true);
    }

    private void setListener() {

    }


    @Override
    protected void reloadData() {
        super.reloadData();
    }

    /**
     * titlebar  right 点击事件
     */
    @Override
    protected void onRightActionClicked() {
        super.onRightActionClicked();

    }
}
