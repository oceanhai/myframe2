package com.wuhai.lotteryticket.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.databinding.ItemLotteryBinding;
import com.wuhai.lotteryticket.model.bean.Lottery;
import com.wuhai.lotteryticket.ui.adapter.base.BaseDataAdapter;
import com.wuhai.lotteryticket.ui.holder.LotteryCreateHolder;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/8 20:50
 *
 * 描述：生成彩票列表  adapter
 */
public class LotteryCreateAdapter extends BaseDataAdapter<Lottery> {

    private LayoutInflater layoutInflater;

    public LotteryCreateAdapter(Context context) {
        super(context);
        layoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * 添加数据 单个数据
     * @param data
     */
    public void addData(Lottery data){
        if(mData != null){
            mData.set(0, data);//插入到列表头
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //DataBinding
        ItemLotteryBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_lottery, parent, false);
        LotteryCreateHolder holder = new LotteryCreateHolder(binding, mContext);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final LotteryCreateHolder holder1= (LotteryCreateHolder) holder;

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
    }


}
