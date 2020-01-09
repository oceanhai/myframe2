package com.wuhai.lotteryticket.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.config.Constants;
import com.wuhai.lotteryticket.contract.IHomeContract;
import com.wuhai.lotteryticket.databinding.AcMainBinding;
import com.wuhai.lotteryticket.model.bean.LotteryQueryEntity;
import com.wuhai.lotteryticket.presenter.HomePresenter;
import com.wuhai.lotteryticket.ui.base.BaseActionBarActivity;
import com.wuhai.lotteryticket.utils.DateUtil;
import com.wuhai.lotteryticket.utils.MatcherUtils;
import com.wuhai.lotteryticket.utils.MonetaryUnitUtil;

public class MainActivity extends BaseActionBarActivity implements IHomeContract.View {

    private AcMainBinding binding;

    private HomePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = View.inflate(this, R.layout.ac_main, null);
        setContentView(contentView);//※注意 这是把布局add到父容器里
        //绑定contentView 生成binding
        binding = DataBindingUtil.bind(contentView);

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
//        mPresenter.lotteryQueryDlt(Constants.JUHE_LOTTERY_KEY, Constants.JUHE_LOTTERY_ID_DLT,"");
    }

    @Override
    public void setStatistical() {

    }

    private void init() {
        //隐藏titlebar
        setActionBarVisibility(false);

        mPresenter = new HomePresenter(this);
    }

    private void setListener() {

    }

    /**
     * 沉浸式 开发       系统栏
     */
//    @Override
//    protected void setStatusBar() {
//        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
//    }

    @Override
    public void setLotteryQuerySsq(LotteryQueryEntity entity) {
        if(entity == null){
            return;
        }
        binding.ssqLotteryNoAndDateTv.setText(entity.getLottery_no()+"期  "+entity.getLottery_date().substring(5)+
                "  ("+ DateUtil.getCustomStr(entity.getLottery_date())+")");

        String lottery_pool_amount = entity.getLottery_pool_amount().replace(",","");
        if (!MatcherUtils.isAllNumber(lottery_pool_amount)){
            binding.ssqLotteryPoolAmountTv.setVisibility(View.GONE);
        }else {
            binding.ssqLotteryPoolAmountTv.setVisibility(View.VISIBLE);
            binding.ssqLotteryPoolAmountTv.setText("奖池 "+ MonetaryUnitUtil.
                    formatNum(lottery_pool_amount,false));
        }

        String[] res = entity.getLottery_res().split(",");
        if(res != null && res.length==7){
            binding.doubleBallRed1.setText(res[0]);
            binding.doubleBallRed2.setText(res[1]);
            binding.doubleBallRed3.setText(res[2]);
            binding.doubleBallRed4.setText(res[3]);
            binding.doubleBallRed5.setText(res[4]);
            binding.doubleBallRed6.setText(res[5]);
            binding.doubleBallBlue1.setText(res[6]);
        }
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
