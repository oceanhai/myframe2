package com.wuhai.lotteryticket.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.databinding.ItemLotteryBinding;
import com.wuhai.lotteryticket.model.bean.LotteryRecord;
import com.wuhai.lotteryticket.ui.adapter.base.BaseDataAdapter2;
import com.wuhai.lotteryticket.ui.holder.LotteryRecordHolder;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/8 20:50
 *
 * 描述：彩票记录  adapter
 */
public class LotteryRecordAdapter extends BaseDataAdapter2<LotteryRecord,LotteryRecordHolder> {

    private LayoutInflater layoutInflater;

    public LotteryRecordAdapter(Context context) {
        super(context);
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public LotteryRecordHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        //DataBinding
        ItemLotteryBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_lottery, parent, false);
        LotteryRecordHolder holder = new LotteryRecordHolder(binding, mContext);

        return holder;
    }

    @Override
    public void onBindItemViewHolder(LotteryRecordHolder holder, int position) {
        holder.setmData(dataList.get(position));
        holder.refreshView();//对viewholder进行操作
    }


}
