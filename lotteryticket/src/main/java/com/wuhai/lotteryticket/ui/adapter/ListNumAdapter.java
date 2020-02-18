package com.wuhai.lotteryticket.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.ui.adapter.base.BaseDataAdapter;


/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/8 20:50
 *
 * 描述：红球数和蓝球数  下拉选择列表
 */
public class ListNumAdapter extends BaseDataAdapter<String> {

    public ListNumAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_list_num, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1= (ViewHolder) holder;
        holder1.mNumTv.setText(getDataItem(position));
        holder1.mNumTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickLitener != null){
                    mOnItemClickLitener.onItemClick(v, position);
                }
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mNumTv;

        public ViewHolder (View view){
            super(view);
            mNumTv = (TextView) view.findViewById(R.id.item_num_tv);
        }

    }

}
