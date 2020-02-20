package com.wuhai.lotteryticket.ui.holder;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.ViewDataBinding;

import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.databinding.ItemLotteryBinding;
import com.wuhai.lotteryticket.model.bean.Lottery;
import com.wuhai.lotteryticket.ui.holder.base.BaseDataBindingHolder;
import com.wuhai.lotteryticket.widget.tagflowlayout.FlowLayout;
import com.wuhai.lotteryticket.widget.tagflowlayout.TagAdapter;

import java.util.Arrays;
import java.util.List;


/**
 * 作者 wuhai
 *
 * 创建日期 2018/8/1 17:06
 *
 * 描述：
 */

public class LotteryCreateHolder extends BaseDataBindingHolder<Lottery> {

    public static final String TAG = "LotteryCreateHolder";

    private ItemLotteryBinding binding;
    private LayoutInflater mInflater;

    private TagAdapter redAdapter;
    private TagAdapter blueAdapter;

    public LotteryCreateHolder(ViewDataBinding binding, Context context) {
        super(binding,context);
        this.binding = (ItemLotteryBinding) binding;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public void refreshView() {
        binding.betNumTv.setText(mData.getLottery_bet_num()+" 注");
        binding.moneyTv.setText("￥"+mData.getLottery_bet_money()+" 元");
        if(TextUtils.isEmpty(mData.getLottery_no())){
            binding.lotteryNoTv.setVisibility(View.GONE);
        }else{
            binding.lotteryNoTv.setVisibility(View.VISIBLE);
            binding.lotteryNoTv.setText(mData.getLottery_no()+"期");
        }
        if(mData.getLottery_red_ball_count()>6 || mData.getLottery_blue_ball_count()>1){
            binding.lotteryMultipleTv.setVisibility(View.VISIBLE);
        }else{
            binding.lotteryMultipleTv.setVisibility(View.GONE);
        }

        final List<String> redBalls = Arrays.asList(mData.getLottery_red_ball().split(","));
        final List<String> blueBalls = Arrays.asList(mData.getLottery_blue_ball().split(","));

        redAdapter = new TagAdapter<String>(redBalls) {
            @Override
            public View getView(FlowLayout parent, int position, String tag) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_red_ball_view,
                        binding.redBallList, false);
                tv.setText(tag);
                return tv;
            }
        };
        binding.redBallList.setAdapter(redAdapter);
        //TODO 注释掉 不然父布局长按被拦截
//        binding.redBallList.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
//            @Override
//            public boolean onTagClick(View view, int position, FlowLayout parent) {
//                LogProxy.e(TAG, redBalls.get(position));
//                return true;
//            }
//        });

        blueAdapter = new TagAdapter<String>(blueBalls) {
            @Override
            public View getView(FlowLayout parent, int position, String tag) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_blue_ball_view,
                        binding.blueBallList, false);
                tv.setText(tag);
                return tv;
            }
        };
        binding.blueBallList.setAdapter(blueAdapter);
        //TODO 注释掉 不然父布局长按被拦截
//        binding.blueBallList.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
//            @Override
//            public boolean onTagClick(View view, int position, FlowLayout parent) {
//                LogProxy.e(TAG, blueBalls.get(position));
//                return true;
//            }
//        });

        binding.selectCb.setChecked(mData.isSelected());
    }
}
