package com.wuhai.lotteryticket.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.diycoder.library.listener.ScrollListener;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.config.Constants;
import com.wuhai.lotteryticket.contract.ILotteryHistoryContract;
import com.wuhai.lotteryticket.databinding.AcLotteryHistoryBinding;
import com.wuhai.lotteryticket.model.bean.LotteryHistoryEntity;
import com.wuhai.lotteryticket.model.bean.LotteryQueryEntity;
import com.wuhai.lotteryticket.presenter.LotteryHistoryPresenter;
import com.wuhai.lotteryticket.ui.adapter.LotteryHistoryHeaderAdapter;
import com.wuhai.lotteryticket.ui.base.NewLoadingBaseActivity;
import com.wuhai.lotteryticket.utils.CommonUtils;
import com.wuhai.lotteryticket.utils.GsonUtils;


/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：彩票历史列表
 */
public class LotteryHistoryActivity extends NewLoadingBaseActivity implements ILotteryHistoryContract.View, LotteryHistoryHeaderAdapter.OnItemClickLitener, View.OnClickListener {

    //彩票ID
    private String mLotteryId;
    private LotteryQueryEntity mLotteryEntity;//数据

    //Binding
    private AcLotteryHistoryBinding binding;

    //P
    private LotteryHistoryPresenter mPresenter;

    //彩票历史 adapter
    private LotteryHistoryHeaderAdapter mLotteryHistoryHeaderAdapter;
    private ScrollListener mScrollListener;
    //假数据
    private LotteryHistoryEntity lotteryHistoryEntity;
    private int newPage = 1;//最多加载三页



    /**
     *
     * @param context
     * @param lottery_id        ssq,dlt
     */
    public static void startActivity(Context context, String lottery_id, LotteryQueryEntity entity) {
        Intent intent = new Intent();
        intent.putExtra("lottery_id",lottery_id);
        intent.putExtra("lottery_entity",entity);
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
        getData();
    }

    private void getData() {
//        mPresenter.lotteryHistory(mLotteryId, 1, 10);

        String lottery_ssq_history = CommonUtils.getFromAssets("lottery_ssq_history", this);
        JsonObject object = new JsonParser().parse(lottery_ssq_history).getAsJsonObject();
        String result_ssq = object.get("result").toString();
        lotteryHistoryEntity = GsonUtils.getInstance().fromJson(result_ssq, LotteryHistoryEntity.class);
        setLotteryHistory(lotteryHistoryEntity);
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
            mLotteryEntity = (LotteryQueryEntity) intent.getSerializableExtra("lottery_entity");
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.lotteryHistoryRv.setLayoutManager(linearLayoutManager);
        mLotteryHistoryHeaderAdapter = new LotteryHistoryHeaderAdapter(this, mLotteryId);
        mLotteryHistoryHeaderAdapter.setOnItemClickLitener(this);
        mScrollListener = new ScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore() {
                loadMore();
            }
        };
        binding.lotteryHistoryRv.addOnScrollListener(mScrollListener);
        binding.lotteryHistoryRv.setAdapter(mLotteryHistoryHeaderAdapter);
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                newPage++;
                if(newPage>3){
                    mLotteryHistoryHeaderAdapter.setHasMoreDataAndFooter(false,true);
                    mScrollListener.setLoadMore(false);
                }else {
                    mScrollListener.setLoadMore(true);
                }
                addNewData();
            }
        },3000);
    }

    private void addNewData() {
        mLotteryHistoryHeaderAdapter.setDataList(lotteryHistoryEntity.getLotteryResList());
    }

    private void setListener() {
        binding.lotteryHistoryCreateRl.setOnClickListener(this);
        binding.lotteryHistoryTrendChartRl.setOnClickListener(this);
        binding.lotteryHistoryCountingAwardRl.setOnClickListener(this);
    }

    @Override
    protected void reloadData() {
        super.reloadData();
    }

    @Override
    public void setLotteryHistory(LotteryHistoryEntity entity) {

        if(mLotteryHistoryHeaderAdapter == null){
            return;
        }
        mLotteryHistoryHeaderAdapter.setDataList(entity.getLotteryResList());
        mLotteryHistoryHeaderAdapter.setHasMoreData(true);//可加载更多
    }

    @Override
    public void onItemClick(View view, int position) {
        //彩票历史列表 点击事件
        Toast.makeText(this,"item 点击位置"+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lottery_history_create_rl:
                LotteryCreateActivity.startActivity(this, mLotteryId, mLotteryEntity);
                break;
            case R.id.lottery_history_trend_chart_rl:
                Toast.makeText(this,"趋势图暂未开通",Toast.LENGTH_SHORT).show();
                break;
            case R.id.lottery_history_counting_award_rl:
                showToast("算奖工具暂未开通");
                break;
        }
    }
}
