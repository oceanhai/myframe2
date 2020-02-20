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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
            mData.add(0, data);//插入到列表头
            notifyDataSetChanged();
        }
    }

    /**
     * 删除
     * @param data
     */
    public void deleteData(Lottery data){
        if(mData != null){
            mData.remove(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 获得要排除的红球和蓝球集合
     * @return
     */
    public Map<String, Set<String>> getFactorSet(){

        if(mData == null){
            return null;
        }

        Map<String,Set<String>> map = new HashMap<>();
        Set<String> mRedNumSet = new TreeSet<>();
        Set<String> mBlueNumSet = new TreeSet<>();
        for(int x=0;x<mData.size();x++){
            Lottery lottery = mData.get(x);
            if(lottery.isSelected()){
                String[] redBalls = lottery.getLottery_red_ball().split(",");
                String[] blueBalls = lottery.getLottery_blue_ball().split(",");
                for(int y= 0;y<redBalls.length;y++){
                    mRedNumSet.add(redBalls[y]);
                }
                for(int y= 0;y<blueBalls.length;y++){
                    mBlueNumSet.add(blueBalls[y]);
                }
            }
        }

        if(!mRedNumSet.isEmpty() && (!mBlueNumSet.isEmpty())){
            map.put("red", mRedNumSet);
            map.put("blue", mBlueNumSet);
            return map;
        }

        return null;
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
        holder1.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mOnItemLongClickListener != null){
                    mOnItemLongClickListener.onItemLongClick(holder1.itemView,position);
                }
                return true;
            }
        });
        holder1.refreshView();//对viewholder进行操作
    }


}
