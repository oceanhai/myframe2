package com.wuhai.lotteryticket.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.config.Constants;
import com.wuhai.lotteryticket.databinding.AcLotteryCreateBinding;
import com.wuhai.lotteryticket.model.LotteryModelImpl;
import com.wuhai.lotteryticket.model.LotteryRecordModelImpl;
import com.wuhai.lotteryticket.model.bean.Lottery;
import com.wuhai.lotteryticket.model.bean.LotteryQueryEntity;
import com.wuhai.lotteryticket.model.bean.LotteryRecord;
import com.wuhai.lotteryticket.ui.adapter.LotteryCreateAdapter;
import com.wuhai.lotteryticket.ui.adapter.base.BaseDataAdapter;
import com.wuhai.lotteryticket.ui.base.NewLoadingBaseActivity;
import com.wuhai.lotteryticket.utils.DateUtil;
import com.wuhai.lotteryticket.utils.DateUtils;
import com.wuhai.lotteryticket.utils.MatcherUtils;
import com.wuhai.lotteryticket.utils.MathUtils;
import com.wuhai.lotteryticket.utils.MonetaryUnitUtil;
import com.wuhai.lotteryticket.utils.MyLuck;
import com.wuhai.lotteryticket.utils.StringUtil;
import com.wuhai.lotteryticket.widget.popupwindow.ListPopWindow;
import com.wuhai.lotteryticket.widget.popupwindow.PositionCanChangePopupWindow;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import me.jessyan.autosize.utils.ScreenUtils;


/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：彩票生成
 */
public class LotteryCreateActivity extends NewLoadingBaseActivity implements View.OnClickListener, ListPopWindow.OnNumCallBackLitener, BaseDataAdapter.OnItemLongClickListener, View.OnTouchListener, BaseDataAdapter.OnItemClickLitener {

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
    private LotteryRecordModelImpl mLotteryRecordModelImpl;

    //生成的彩票列表 adapter
    private LotteryCreateAdapter mLotteryCreateAdapter;

    //
    private PositionCanChangePopupWindow positionCanChangePopupWindow;

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
        mLotteryRecordModelImpl = new LotteryRecordModelImpl();

        switch (mLotteryId){
            case Constants.JUHE_LOTTERY_ID_SSQ:
                setLotteryQuerySsq(ssqEntity);
                break;
            case Constants.JUHE_LOTTERY_ID_DLT:
                //TODO
                break;
        }

        /**
         * 方式一 无法自适应
         * ?之前是因为嵌套在ScrollView 无法全部展示才造成的不能item自适应高度?rv外加rl处理后，就跟趣竞价竞价页一样
         * 居然可以自适应高度了
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.lotteryRv.setLayoutManager(linearLayoutManager);

        /**
         * 方式二
         * HORIZONTAL 倒是能自适应高度
         * VERTICAL     却不能自适应高度
         * https://www.jianshu.com/p/1d39bcc8fa94
         */
//        StaggeredGridLayoutManager horizontalManager =
//                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
//        binding.lotteryRv.setLayoutManager(horizontalManager);

        /**
         * 方式三
         * 特么的也不行
         * 而且还有问题 4个的时候只显示3个
         * https://blog.csdn.net/singleton1900/article/details/48369635
         */
//        SyLinearLayoutManager syLinearLayoutManager =
//                new SyLinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
//        binding.lotteryRv.setLayoutManager(syLinearLayoutManager);

        mLotteryCreateAdapter = new LotteryCreateAdapter(this);
        mLotteryCreateAdapter.setOnItemLongClickListener(this);
        mLotteryCreateAdapter.setOnItemClickLitener(this);
        binding.lotteryRv.setAdapter(mLotteryCreateAdapter);

        binding.observableScrollView.setFloatView(binding.layoutIn, binding.viewOutScroll);//设置非浮动View和固定View
        binding.observableScrollView.setHorizontalFadingEdgeEnabled(false);//删除android ScrollView边界阴影方法方法
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
        binding.lotteryFactorCb.setOnClickListener(this);

        binding.redTv2.setOnClickListener(this);
        binding.blueTv2.setOnClickListener(this);
        binding.lotteryNoRandomTv2.setOnClickListener(this);
        binding.lotteryFactorCb2.setOnClickListener(this);

        //碰到了就 弹窗存在的时候 让弹窗消失
        binding.layoutOut.setOnTouchListener(this);
        binding.observableScrollView.setOnTouchListener(this);
        binding.lotteryRv.setOnTouchListener(this);
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
        LotteryRecordActivity.startActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.red_tv:
            case R.id.red_tv2:
                if (mRedListPopWindow == null) {
                    mRedListPopWindow = new ListPopWindow(this, 0);
                    mRedListPopWindow.setOnNumCallBackLitener(this);
                }
                mRedListPopWindow.showPopupWindow(v);
                break;
            case R.id.blue_tv:
            case R.id.blue_tv2:
                if (mBlueListPopWindow == null) {
                    mBlueListPopWindow = new ListPopWindow(this, 1);
                    mBlueListPopWindow.setOnNumCallBackLitener(this);
                }
                mBlueListPopWindow.showPopupWindow(v);
                break;
            case R.id.lottery_factor_cb://非浮动cb
                binding.lotteryFactorCb2.setChecked(binding.lotteryFactorCb.isChecked());
                break;
            case R.id.lottery_factor_cb2://浮动cb
                binding.lotteryFactorCb.setChecked(binding.lotteryFactorCb2.isChecked());
                break;
            case R.id.lottery_no_random_tv:
            case R.id.lottery_no_random_tv2:
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
                binding.redTv2.setText(num);
                break;
            case 1:
                binding.blueTv.setText(num);
                binding.blueTv2.setText(num);
                break;
        }

        int redNum = Integer.valueOf(binding.redTv.getText().toString());
        int blueNum = Integer.valueOf(binding.blueTv.getText().toString());
        lottery_bet_num = MathUtils.getCombinationNum(redNum,6) * blueNum;
        lottery_bet_money = lottery_bet_num*2;
        binding.betNumTv.setText(lottery_bet_num+" 注");
        binding.betNumTv2.setText(lottery_bet_num+" 注");
        binding.moneyTv.setText("￥"+lottery_bet_money+" 元");
        binding.moneyTv2.setText("￥"+lottery_bet_money+" 元");
    }

    @Override
    public void onItemLongClick(View v, final int position) {
//        showToast("长按了"+position);
        positionCanChangePopupWindow = new PositionCanChangePopupWindow(this,v);
        positionCanChangePopupWindow.setFloatingOperation(new PositionCanChangePopupWindow.IFloatingOperation() {
            @Override
            public void onEdit() {
                showToast("还未开发！");
            }

            @Override
            public void onDelete() {
                Lottery lottery = mLotteryCreateAdapter.getDataItem(position);
                mLotteryModelImpl.deleteLottery(lottery);
                mLotteryCreateAdapter.deleteData(lottery);
            }

            @Override
            public void onSave() {
                Lottery lottery = mLotteryCreateAdapter.getDataItem(position);
                mLotteryModelImpl.deleteLottery(lottery);
                mLotteryCreateAdapter.deleteData(lottery);
                mLotteryRecordModelImpl.addLotteryRecord(new LotteryRecord(lottery));
            }
        });

        /**
         * 这样并不能获得 在屏幕内的坐标
         */
//        Log.e(TAG, "v.getX()="+v.getX()+", v.getY()="+v.getY());
//        Log.e(TAG, "v.getLeft()="+v.getLeft()+", v.getTop()="+v.getTop()+
//                ",v.getRight()="+v.getRight()+",v.getBottom()="+v.getBottom());
//        positionCanChangePopupWindow.
//                showPopupWindow((v.getRight()+v.getLeft())/2,(v.getBottom()+v.getTop())/2);

        int[] location = new int[2];
        v.getLocationOnScreen(location);
        Log.e(TAG, "location[0]="+location[0]+", location[1]="+location[1]);
        positionCanChangePopupWindow.
                showPopupWindow(ScreenUtils.getScreenSize(this)[0]/2,location[1]);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(positionCanChangePopupWindow != null && positionCanChangePopupWindow.isShowing()){
            positionCanChangePopupWindow.hide();
        }
        return false;
    }

    @Override
    public void onItemClick(View view, int position) {
        if(positionCanChangePopupWindow != null && positionCanChangePopupWindow.isShowing()){
            positionCanChangePopupWindow.hide();
        }
    }
}
