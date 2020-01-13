package com.wuhai.lotteryticket.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.config.Constants;
import com.wuhai.lotteryticket.contract.ILotteryHistoryContract;
import com.wuhai.lotteryticket.databinding.AcLotteryHistoryBinding;
import com.wuhai.lotteryticket.model.bean.LotteryHistoryEntity;
import com.wuhai.lotteryticket.presenter.LotteryHistoryPresenter;
import com.wuhai.lotteryticket.ui.base.NewLoadingBaseActivity;


/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：彩票历史列表
 */
public class LotteryHistoryActivity extends NewLoadingBaseActivity implements ILotteryHistoryContract.View {

    //彩票ID
    private String mLotteryId;

    //Binding
    private AcLotteryHistoryBinding binding;

    //P
    private LotteryHistoryPresenter mPresenter;

    /**
     *
     * @param context
     * @param lottery_id        ssq,dlt
     */
    public static void startActivity(Context context, String lottery_id) {
        Intent intent = new Intent();
        intent.putExtra("lottery_id",lottery_id);
        intent.setClass(context, LotteryHistoryActivity.class);
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
        View contentView = View.inflate(this, R.layout.ac_lottery_history, null);
        setContentView(contentView);//※注意 这是把布局add到父容器里
        binding = DataBindingUtil.bind(contentView);
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if(intent != null){
            mLotteryId = intent.getStringExtra("lottery_id");
        }
    }

    @Override
    public void setStatistical() {

    }

    private void init() {
        switch (mLotteryId){
            case Constants.JUHE_LOTTERY_ID_SSQ:
                setTitle("双色球");
                break;
            case Constants.JUHE_LOTTERY_ID_DLT:
                setTitle("超级大乐透");
                break;
        }

        mPresenter = new LotteryHistoryPresenter(this);

    }

    private void setListener() {

    }


    @Override
    protected void reloadData() {
        super.reloadData();
    }

    @Override
    public void setLotteryHistory(LotteryHistoryEntity entity) {

    }
}
