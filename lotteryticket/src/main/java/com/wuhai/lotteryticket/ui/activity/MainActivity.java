package com.wuhai.lotteryticket.ui.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.databinding.DataBindingUtil;

import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.config.Constants;
import com.wuhai.lotteryticket.contract.IHomeContract;
import com.wuhai.lotteryticket.databinding.AcMainBinding;
import com.wuhai.lotteryticket.model.bean.LotteryQueryEntity;
import com.wuhai.lotteryticket.presenter.HomePresenter;
import com.wuhai.lotteryticket.ui.base.BaseActivity;
import com.wuhai.lotteryticket.utils.StatusBarUtil;

public class MainActivity extends BaseActivity implements IHomeContract.View {

    private AcMainBinding binding;

    private HomePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ac_main);//※注意 这是把布局add到父容器里

        //TODO DataBinding和BaseActivity多状态布局使用  冲突
        //这里我们直接继承 BaseActivity，但 其他界面ac,如果想使用基类多状态布局，到时候再研究吧
        binding = DataBindingUtil.setContentView(this, R.layout.ac_main);

        //AppCompatActivity提供ActionBar,但我以前做开发还真没怎么用过ActionBar
        //所以如果不用ActionBar 直接继承FragmentActivity就行
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        init();
        setListener();
        getData();
    }

    private void getData() {
        //TODO Sring 空不能传NULL 只能""  菜鸡接口
        mPresenter.lotteryQuerySsq(Constants.JUHE_LOTTERY_KEY, Constants.JUHE_LOTTERY_ID_SSQ,"");
        mPresenter.lotteryQueryDlt(Constants.JUHE_LOTTERY_KEY, Constants.JUHE_LOTTERY_ID_DLT,"");
    }

    @Override
    public void setStatistical() {

    }

    private void init() {
        mPresenter = new HomePresenter(this);
    }

    private void setListener() {

    }

    /**
     * 沉浸式 开发       系统栏
     */
    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
    }

    @Override
    public void setLotteryQuerySsq(LotteryQueryEntity entity) {
        Log.e(TAG, "setLotteryQuerySsq="+entity.toString());
        if(entity == null){
            return;
        }
        binding.ssqLotteryNoAndDateTv.setText(entity.getLottery_no()+"期");

    }

    @Override
    public void setLotteryQueryDlt(LotteryQueryEntity entity) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dimssLoading() {

    }
}
