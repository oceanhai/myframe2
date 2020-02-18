package com.wuhai.lotteryticket.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.config.Constants;
import com.wuhai.lotteryticket.databinding.AcLotteryCreateBinding;
import com.wuhai.lotteryticket.model.LotteryModelImpl;
import com.wuhai.lotteryticket.model.bean.Lottery;
import com.wuhai.lotteryticket.model.bean.LotteryQueryEntity;
import com.wuhai.lotteryticket.ui.adapter.LotteryCreateAdapter;
import com.wuhai.lotteryticket.ui.base.NewLoadingBaseActivity;
import com.wuhai.lotteryticket.utils.DateUtil;
import com.wuhai.lotteryticket.utils.DateUtils;
import com.wuhai.lotteryticket.utils.MatcherUtils;
import com.wuhai.lotteryticket.utils.MathUtils;
import com.wuhai.lotteryticket.utils.MonetaryUnitUtil;
import com.wuhai.lotteryticket.utils.MyLuck;
import com.wuhai.lotteryticket.utils.StringUtil;
import com.wuhai.lotteryticket.widget.popupwindow.ListPopWindow;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;


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

    //彩票ID
    private String mLotteryId;
    private LotteryQueryEntity ssqEntity;//双色球数据
    private LotteryQueryEntity dltEntity;//大乐透数据

    //红球+蓝球
    private Set<String> mRedNumSet = new TreeSet<>();
    private Set<String> mBlueNumSet = new TreeSet<>();
    private int lottery_bet_num=1;//方案注数
    private int lottery_bet_money=2;//投注金额

    //impl
    private LotteryModelImpl mLotteryModelImpl;

    //生成的彩票列表 adapter
    private LotteryCreateAdapter mLotteryCreateAdapter;

    /**
     *
     * @param context
     */
    public static void startActivity(Context context, String lottery_id, LotteryQueryEntity entity) {
        Intent intent = new Intent();
        intent.putExtra("lottery_id",lottery_id);
        intent.putExtra("lottery_entity",entity);
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
        initData();
    }

    private void initData() {
        List<Lottery> list = mLotteryModelImpl.queryLotteryAll();
        mLotteryCreateAdapter.setData(list);
    }


    private void initView() {
        View contentView = View.inflate(this, R.layout.ac_lottery_create, null);
        setContentView(contentView);//※注意 这是把布局add到父容器里
        binding = DataBindingUtil.bind(contentView);
    }


    private void parseIntent() {
        Intent intent = getIntent();
        if(intent != null){
            mLotteryId = intent.getStringExtra("lottery_id");
            switch (mLotteryId){
                case Constants.JUHE_LOTTERY_ID_SSQ:
                    ssqEntity = (LotteryQueryEntity) intent.getSerializableExtra("lottery_entity");
                    break;
                case Constants.JUHE_LOTTERY_ID_DLT:
                    dltEntity = (LotteryQueryEntity) intent.getSerializableExtra("lottery_entity");
                    break;
            }
        }
    }

    @Override
    public void setStatistical() {

    }

    private void init() {
        setTitle("双球球计算器");
        setActionRightText("记录");
        mLotteryModelImpl = new LotteryModelImpl();

        switch (mLotteryId){
            case Constants.JUHE_LOTTERY_ID_SSQ:
                setLotteryQuerySsq(ssqEntity);
                break;
            case Constants.JUHE_LOTTERY_ID_DLT:
                //TODO
                break;
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.lotteryRv.setLayoutManager(linearLayoutManager);
        mLotteryCreateAdapter = new LotteryCreateAdapter(this);
        binding.lotteryRv.setAdapter(mLotteryCreateAdapter);
    }

    /**
     * 上期双色球
     * @param entity
     */
    private void setLotteryQuerySsq(LotteryQueryEntity entity) {
        if(entity == null){
            return;
        }

        /**
         * 红球+蓝球
         */
        String lottery_res = entity.getLottery_res();
        String[] arr = lottery_res.split(",");
        for(int x= 0;x<arr.length-1;x++){
            mRedNumSet.add(arr[x]);
        }
        mBlueNumSet.add(arr[6]);

        /**
         * 上期 UI set
         */
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

    private void setListener() {
        binding.redTv.setOnClickListener(this);
        binding.blueTv.setOnClickListener(this);
        binding.lotteryNoRandomTv.setOnClickListener(this);
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
            case R.id.lottery_no_random_tv:
                int redNum = Integer.valueOf(binding.redTv.getText().toString());
                int blueNum = Integer.valueOf(binding.blueTv.getText().toString());
                String lottery_red_ball = "";
                String lottery_blue_ball = "";
                if(binding.lotteryFactorCb.isChecked()){//排除上期号码
//                    addView(MyLuck.getLotteryRes(mRedNumSet, mBlueNumSet, redNum, blueNum));
                    lottery_red_ball = MyLuck.getLotteryResRedBall(mRedNumSet, redNum);
                    lottery_blue_ball = MyLuck.getLotteryResBlueBall(mBlueNumSet, blueNum);
                }else{
//                    addView(MyLuck.getLotteryRes(redNum, blueNum));
                    lottery_red_ball = MyLuck.getLotteryResRedBall(redNum);
                    lottery_blue_ball = MyLuck.getLotteryResBlueBall(blueNum);
                }

                Lottery lottery = new Lottery();
                lottery.setLottery_id(StringUtil.getUUid());
                switch (mLotteryId){
                    case Constants.JUHE_LOTTERY_ID_SSQ:
                        lottery.setLottery_type(Constants.JUHE_LOTTERY_ID_SSQ);
                        lottery.setLottery_name("双色球");
                        break;
                    case Constants.JUHE_LOTTERY_ID_DLT:
                        lottery.setLottery_type(Constants.JUHE_LOTTERY_ID_DLT);
                        lottery.setLottery_name("超级大乐透");
                        break;
                }
                lottery.setLottery_red_ball(lottery_red_ball);
                lottery.setLottery_blue_ball(lottery_blue_ball);
                lottery.setLottery_red_ball_count(Integer.valueOf(binding.redTv.getText().toString()));
                lottery.setLottery_blue_ball_count(Integer.valueOf(binding.blueTv.getText().toString()));
                lottery.setLottery_bet_num(lottery_bet_num);
                lottery.setLottery_bet_money(lottery_bet_money);
                if(binding.lotteryFactorCb.isChecked()){
                    lottery.setLottery_produce_method(1);
                }else{
                    lottery.setLottery_produce_method(0);
                }
                lottery.setLottery_no("");//TODO 开奖期数
                //添加时间,最后修改时间
                String time = DateUtils.getDateAllString();
                lottery.setCreate_time(time);
                lottery.setLast_modified(time);

                mLotteryModelImpl.addLottery(lottery);

                mLotteryCreateAdapter.addData(lottery);
                break;
        }
    }

    /**
     * 按钮点击事件，向容器中添加TextView
     */
    public void addView(String lottery_res) {
        TextView child = new TextView(this);
        child.setTextSize(18);
        child.setTextColor(getResources().getColor(R.color.text_color_black_one));
        child.setText(lottery_res);
        // 调用一个参数的addView方法
        binding.lotteryNoRandomListLl.addView(child);
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
        lottery_bet_num = MathUtils.getCombinationNum(redNum,6) * blueNum;
        lottery_bet_money = lottery_bet_num*2;
        binding.betNumTv.setText(lottery_bet_num+" 注");
        binding.moneyTv.setText("￥"+lottery_bet_money+" 元");
    }
}
