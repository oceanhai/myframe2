package com.wuhai.lotteryticket.ui.holder;

import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.wuhai.lotteryticket.databinding.ItemLotteryHistoryBinding;
import com.wuhai.lotteryticket.model.bean.LotteryHistory;
import com.wuhai.lotteryticket.ui.holder.base.BaseDataBindingHolder;
import com.wuhai.lotteryticket.utils.DateUtil;
import com.wuhai.lotteryticket.utils.MatcherUtils;
import com.wuhai.lotteryticket.utils.MonetaryUnitUtil;


/**
 * 作者 wuhai
 *
 * 创建日期 2018/8/1 17:06
 *
 * 描述：
 */

public class LotteryHistoryHolder extends BaseDataBindingHolder<LotteryHistory> {

    private ItemLotteryHistoryBinding binding;

    public LotteryHistoryHolder(ViewDataBinding binding){
        super(binding);
        this.binding = (ItemLotteryHistoryBinding) binding;
    }

    @Override
    public void refreshView() {
        binding.itemLotteryNoAndDateTv.setText(mData.getLottery_no()+"期  "+mData.getLottery_date().substring(5)+
                "  ("+ DateUtil.getCustomStr(mData.getLottery_date())+")");

        String lottery_pool_amount = mData.getLottery_pool_amount().replace(",","");
        if (!MatcherUtils.isAllNumber(lottery_pool_amount)){
            binding.itemLotteryPoolAmountTv.setVisibility(View.GONE);
        }else {
            binding.itemLotteryPoolAmountTv.setVisibility(View.VISIBLE);
            binding.itemLotteryPoolAmountTv.setText("奖池 "+ MonetaryUnitUtil.
                    formatNum(lottery_pool_amount,false));
        }

        binding.itemLotteryView.setResource(mData.getLottery_res());
    }

    public void hideLine(){
        binding.itemLine.setVisibility(View.GONE);
    }
}
