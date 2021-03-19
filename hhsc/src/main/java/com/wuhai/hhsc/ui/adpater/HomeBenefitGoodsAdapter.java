package com.wuhai.hhsc.ui.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wuhai.hhsc.R;
import com.wuhai.hhsc.app.App;
import com.wuhai.hhsc.model.GoodsBenefitEntity;
import com.wuhai.hhsc.ui.viewholder.BaseViewHolder;
import com.wuhai.hhsc.utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * description:惠筹商品列表适配器
 * data: 2019/12/4
 * author: zhudi
 */
public class HomeBenefitGoodsAdapter extends BaseRecyclerAdapter {
    private Context mContext;
    private List<GoodsBenefitEntity> list;

    public HomeBenefitGoodsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(List<GoodsBenefitEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void updateList(List<GoodsBenefitEntity> list) {
        if (list == null) {
            return;
        }

        if (this.list == null) {
            this.list = new ArrayList<>(1);
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void addList(List<GoodsBenefitEntity> list) {
        this.list.addAll(list);
        notifyItemRangeInserted(this.list.size() - list.size(), list.size());
    }


    public GoodsBenefitEntity getItem(int position) {
        return list == null ? null : list.get(position);
    }

    public List<GoodsBenefitEntity> getList() {
        return list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        return new GoodsHolder(LayoutInflater.from(App.getInstance()).inflate(R.layout.item_list_goods_benefit_home, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        if (getItemCount() > 0) {
            if (holder instanceof GoodsHolder) {
                GoodsHolder goodsHolder = (GoodsHolder) holder;
                GoodsBenefitEntity goodsBenefitEntity = list.get(position);
                if (goodsBenefitEntity != null) {
                    Utils.imageViewDisplayByUrl(mContext,goodsBenefitEntity.getMainImgUrl(), goodsHolder.goodsIcon);
                    goodsHolder.goodsNameView.setText(goodsBenefitEntity.getGoodsName());
                    goodsHolder.goodsPriceView.setText("￥" + goodsBenefitEntity.getDiscountPrice());
                    goodsHolder.goodsIntegralView.setText("积分：" + goodsBenefitEntity.getGiveIntegral());
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class GoodsHolder extends BaseViewHolder {
        ImageView goodsIcon;
        TextView goodsNameView, goodsPriceView, goodsIntegralView;

        public GoodsHolder(View itemView) {
            super(itemView);
            goodsIcon = itemView.findViewById(R.id.iv_goods);
            goodsNameView = itemView.findViewById(R.id.tv_name_goods);
            goodsPriceView = itemView.findViewById(R.id.tv_price_goods);
            goodsIntegralView = itemView.findViewById(R.id.tv_integral_goods);
        }

    }
}
