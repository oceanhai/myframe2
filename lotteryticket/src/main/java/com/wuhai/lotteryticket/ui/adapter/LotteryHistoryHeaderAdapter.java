package com.wuhai.lotteryticket.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.diycoder.library.adapter.HeaderAdapter;
import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.databinding.ItemLotteryHistoryBinding;
import com.wuhai.lotteryticket.databinding.ItemLotteryHistoryHeaderBinding;
import com.wuhai.lotteryticket.model.bean.LotteryHistory;
import com.wuhai.lotteryticket.ui.holder.LotteryHistoryHeaderHolder;
import com.wuhai.lotteryticket.ui.holder.LotteryHistoryHolder;


/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/8 20:50
 *
 * 描述：
 */
public class LotteryHistoryHeaderAdapter extends HeaderAdapter<LotteryHistory, LotteryHistoryHeaderHolder, LotteryHistoryHolder> {

    private String lottery_id;

    /**
     * 回调点击监听
     */
    public interface OnItemClickLitener{
        void onItemClick(View view, int position);
    }

    public OnItemClickLitener mOnItemClickLitener;

    public LotteryHistoryHeaderAdapter(Context context, String lottery_id) {
        super(context);
        this.lottery_id = lottery_id;
    }

    /**
     * 设置点击监听
     * @param mOnItemClickLitener
     */
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public LotteryHistoryHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        //以往方式
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
//        View view = layoutInflater.inflate(R.layout.item_lottery_history, parent, false);
//        LocationNamesHolder holder = new LocationNamesHolder(view);

        //DataBinding
        ItemLotteryHistoryBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_lottery_history, parent, false);
        LotteryHistoryHolder holder = new LotteryHistoryHolder(binding);

        return holder;
    }

    @Override
    public void onBindItemViewHolder(final LotteryHistoryHolder holder, final int position) {

        holder.setmData(getItemData(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickLitener!=null) {
                    mOnItemClickLitener.onItemClick(holder.itemView,position);
                }
            }
        });
        holder.refreshView();//对viewholder进行操作
//        if(position == dataList.size()-1){
//            holder.hideLine();
//        }
    }

    @Override
    public LotteryHistoryHeaderHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        //DataBinding
        ItemLotteryHistoryHeaderBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_lottery_history_header, parent, false);
        LotteryHistoryHeaderHolder holder = new LotteryHistoryHeaderHolder(binding, lottery_id);

        return holder;
    }

    @Override
    public void onBindHeaderViewHolder(LotteryHistoryHeaderHolder holder, int position) {
        ItemLotteryHistoryHeaderBinding binding = holder.getBinding();
        binding.itemLotteryHintTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"开奖提醒点击",Toast.LENGTH_SHORT).show();
            }
        });
        binding.itemLotteryRuleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"开奖规则点击",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
