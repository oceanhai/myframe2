package com.wuhai.lotteryticket.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.config.Constants;
import com.wuhai.lotteryticket.contract.IHomeContract;
import com.wuhai.lotteryticket.databinding.AcMainBinding;
import com.wuhai.lotteryticket.model.bean.LotteryQueryEntity;
import com.wuhai.lotteryticket.presenter.HomePresenter;
import com.wuhai.lotteryticket.ui.base.NewLoadingBaseActivity;
import com.wuhai.lotteryticket.utils.CommonUtils;
import com.wuhai.lotteryticket.utils.DateUtil;
import com.wuhai.lotteryticket.utils.GsonUtils;
import com.wuhai.lotteryticket.utils.MatcherUtils;
import com.wuhai.lotteryticket.utils.MonetaryUnitUtil;

import org.json.JSONObject;

public class MainActivity extends NewLoadingBaseActivity implements IHomeContract.View, View.OnClickListener {

    private AcMainBinding binding;

    private HomePresenter mPresenter;

    private LotteryQueryEntity ssqEntity;//双色球数据
    private LotteryQueryEntity dltEntity;//大乐透数据

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
//        mPresenter.lotteryQuerySsq(Constants.JUHE_LOTTERY_KEY, Constants.JUHE_LOTTERY_ID_SSQ,"");
//        mPresenter.lotteryQueryDlt(Constants.JUHE_LOTTERY_KEY, Constants.JUHE_LOTTERY_ID_DLT,"");

        String lottery_ssq = CommonUtils.getFromAssets("lottery_ssq", this);
        String lottery_dlt = CommonUtils.getFromAssets("lottery_dlt", this);

        try {
            //方式一
//            JSONObject object = new JSONObject(lottery_ssq);
//            String result_ssq = object.getString("result");

            //方式二
            JsonObject object = new JsonParser().parse(lottery_ssq).getAsJsonObject();
//            String result_ssq = object.get("result").getAsString();//TODO 这样是不对的，getAsString所get的字段必须是字符串
            String result_ssq = object.get("result").toString();

            ssqEntity = GsonUtils.getInstance().fromJson(result_ssq, LotteryQueryEntity.class);
            setLotteryQuerySsq(ssqEntity);

            //方式一
            JSONObject object2 = new JSONObject(lottery_dlt);
            String result_dlt = object2.getString("result");
            dltEntity = GsonUtils.getInstance().fromJson(result_dlt, LotteryQueryEntity.class);
            setLotteryQueryDlt(dltEntity);

        } catch (Exception e) {
            e.printStackTrace();
        }

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
        binding.acMainDltLl.setOnClickListener(this);
        binding.acMainSsqLl.setOnClickListener(this);
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
        if(entity == null){
            return;
        }
        binding.dltLotteryNoAndDateTv.setText(entity.getLottery_no()+"期  "+entity.getLottery_date().substring(5)+
                "  ("+ DateUtil.getCustomStr(entity.getLottery_date())+")");

        String lottery_pool_amount = entity.getLottery_pool_amount().replace(",","");
        if (TextUtils.isEmpty(lottery_pool_amount)){
            binding.dltLotteryPoolAmountTv.setVisibility(View.GONE);
        }else {
            binding.dltLotteryPoolAmountTv.setVisibility(View.VISIBLE);
            binding.dltLotteryPoolAmountTv.setText("奖池 "+ lottery_pool_amount);
        }

        String[] res = entity.getLottery_res().split(",");
        if(res != null && res.length==7){
            binding.lottoRed1.setText(res[0]);
            binding.lottoRed2.setText(res[1]);
            binding.lottoRed3.setText(res[2]);
            binding.lottoRed4.setText(res[3]);
            binding.lottoRed5.setText(res[4]);
            binding.lottoBlue1.setText(res[5]);
            binding.lottoBlue2.setText(res[6]);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dimssLoading() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ac_main_dlt_ll://超级大乐透
//                LotteryHistoryActivity.startActivity(this, Constants.JUHE_LOTTERY_ID_DLT, dltEntity);
                showToast("客观别急！正在开发中");
                break;
            case R.id.ac_main_ssq_ll://双色球
                LotteryHistoryActivity.startActivity(this, Constants.JUHE_LOTTERY_ID_SSQ, ssqEntity);
                break;
        }
    }
}
