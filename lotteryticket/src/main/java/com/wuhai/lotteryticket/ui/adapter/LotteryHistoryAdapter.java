package com.wuhai.lotteryticket.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.databinding.ItemLotteryHistoryBinding;
import com.wuhai.lotteryticket.model.bean.LotteryHistory;
import com.wuhai.lotteryticket.ui.adapter.base.BaseDataAdapter;
import com.wuhai.lotteryticket.ui.holder.LotteryHistoryHolder;


/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/8 20:50
 *
 * 描述：
 */
public class LotteryHistoryAdapter extends BaseDataAdapter<LotteryHistory> {

    public LotteryHistoryAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final LotteryHistoryHolder holder1= (LotteryHistoryHolder) holder;

        holder1.setmData(mData.get(position));
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickLitener!=null) {
                    mOnItemClickLitener.onItemClick(holder1.itemView,position);
                }
            }
        });
        holder1.refreshView();//对viewholder进行操作
        if(position == mData.size()-1){
            holder1.hideLine();
        }
    }


}
