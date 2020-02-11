package com.wuhai.lotteryticket.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.databinding.AcLotteryCreateBinding;
import com.wuhai.lotteryticket.ui.base.NewLoadingBaseActivity;
import com.wuhai.lotteryticket.utils.MathUtils;
import com.wuhai.lotteryticket.widget.popupwindow.ListPopWindow;


/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：彩票生成
 */
public class LotteryCreateActivity extends NewLoadingBaseActivity implements View.OnClickListener, ListPopWindow.OnNumCallBackLitener {

    //Binding
    private AcLotteryCreateBinding binding;

    private ListPopWindow mRedListPopWindow;
    private ListPopWindow mBlueListPopWindow;

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
        binding.redTv.setOnClickListener(this);
        binding.blueTv.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.red_tv:
                if (mRedListPopWindow == null) {
                    mRedListPopWindow = new ListPopWindow(this, 0);
                    mRedListPopWindow.setOnNumCallBackLitener(this);
                }
                mRedListPopWindow.showPopupWindow(v);
                break;
            case R.id.blue_tv:
                if (mBlueListPopWindow == null) {
                    mBlueListPopWindow = new ListPopWindow(this, 1);
                    mBlueListPopWindow.setOnNumCallBackLitener(this);
                }
                mBlueListPopWindow.showPopupWindow(v);
                break;
        }
    }

    @Override
    public void onNumCallBack(int type, String num) {
        switch (type){
            case 0:
                binding.redTv.setText(num);
                break;
            case 1:
                binding.blueTv.setText(num);
                break;
        }

        int redNum = Integer.valueOf(binding.redTv.getText().toString());
        int blueNum = Integer.valueOf(binding.blueTv.getText().toString());
        int betNum = MathUtils.getCombinationNum(redNum,6) * blueNum;
        binding.betNumTv.setText(betNum+" 注");
        binding.moneyTv.setText("￥"+(betNum*2)+" 元");
    }
}
