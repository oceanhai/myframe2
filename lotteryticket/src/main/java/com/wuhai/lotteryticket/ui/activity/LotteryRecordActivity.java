package com.wuhai.lotteryticket.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.diycoder.library.listener.ScrollListener;
import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.databinding.AcLotteryRecordBinding;
import com.wuhai.lotteryticket.model.LotteryRecordModelImpl;
import com.wuhai.lotteryticket.model.bean.LotteryRecord;
import com.wuhai.lotteryticket.ui.adapter.LotteryRecordAdapter;
import com.wuhai.lotteryticket.ui.base.NewLoadingBaseActivity;
import com.wuhai.lotteryticket.utils.LogProxy;

import java.util.List;


/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：彩票记录
 */
public class LotteryRecordActivity extends NewLoadingBaseActivity {

    //Binding
    private AcLotteryRecordBinding binding;

    //impl
    private LotteryRecordModelImpl mLotteryRecordModelImpl;

    // adapter
    private LotteryRecordAdapter mLotteryRecordAdapter;
    private ScrollListener mScrollListener;
    private int mCurPage = 1;//当前页数

    /**
     *
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LotteryRecordActivity.class);
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
        List<LotteryRecord> list = mLotteryRecordModelImpl.queryLotteryRecord(mCurPage,10);
        if(list.size()>0){
            mLotteryRecordAdapter.setData(list);
        }else{
            binding.lotteryRv.setVisibility(View.GONE);
        }
    }


    private void initView() {
        View contentView = View.inflate(this, R.layout.ac_lottery_record, null);
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
        setTitle("彩票记录");
        mLotteryRecordModelImpl = new LotteryRecordModelImpl();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.lotteryRv.setLayoutManager(linearLayoutManager);

        mLotteryRecordAdapter = new LotteryRecordAdapter(this);
//        mLotteryRecordAdapter.setOnItemClickLitener(this);
        mScrollListener = new ScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore() {
                loadMore();
            }
        };
        binding.lotteryRv.addOnScrollListener(mScrollListener);
        binding.lotteryRv.setAdapter(mLotteryRecordAdapter);
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        //TODO 我擦 还真得延迟下 ，否则 setLoadMore(false) 置成false不成功 (lll￢ω￢)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mCurPage++;
                List<LotteryRecord> list = mLotteryRecordModelImpl.queryLotteryRecord(mCurPage,10);
                LogProxy.e(TAG, "loadMore  size = "+list.size()+",mCurPage="+mCurPage);
                if(list.size()>0 && list.size()<10){
                    mLotteryRecordAdapter.addAll(list);

                    mLotteryRecordAdapter.setHasMoreDataAndFooter(false,true);
                    mScrollListener.setLoadMore(false);
                }else if(list.size()==10){
                    mLotteryRecordAdapter.addAll(list);

                    mScrollListener.setLoadMore(true);
                }else{
                    mLotteryRecordAdapter.setHasMoreDataAndFooter(false,true);
                    mScrollListener.setLoadMore(false);
                }
            }
        },3000);


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
