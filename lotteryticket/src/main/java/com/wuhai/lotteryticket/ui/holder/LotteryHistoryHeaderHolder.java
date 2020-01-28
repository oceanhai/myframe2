package com.wuhai.lotteryticket.ui.holder;

import androidx.databinding.ViewDataBinding;

import com.wuhai.lotteryticket.config.Constants;
import com.wuhai.lotteryticket.databinding.ItemLotteryHistoryHeaderBinding;
import com.wuhai.lotteryticket.model.bean.LotteryHistory;
import com.wuhai.lotteryticket.ui.holder.base.BaseDataBindingHolder;


/**
 * 作者 wuhai
 *
 * 创建日期 2018/8/1 17:06
 *
 * 描述：
 */

public class LotteryHistoryHeaderHolder extends BaseDataBindingHolder<LotteryHistory> {

    private ItemLotteryHistoryHeaderBinding binding;
    private String lottery_id;

    public LotteryHistoryHeaderHolder(ViewDataBinding binding, String lottery_id){
        super(binding);
        this.binding = (ItemLotteryHistoryHeaderBinding) binding;
        this.lottery_id = lottery_id;
    }

    @Override
    public void refreshView() {

        if (Constants.JUHE_LOTTERY_ID_SSQ.equals(lottery_id)){
            binding.itemLotteryTimeTv.setText("每周二、四、日 21:15开奖");
        }else {
            binding.itemLotteryTimeTv.setText("每周一、三、六 20:30开奖");
        }

    }

    public ItemLotteryHistoryHeaderBinding getBinding() {
        return binding;
    }
}
