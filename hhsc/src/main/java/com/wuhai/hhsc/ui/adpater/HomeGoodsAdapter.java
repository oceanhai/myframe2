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
import com.wuhai.hhsc.model.GoodsListInfo;
import com.wuhai.hhsc.ui.viewholder.BaseViewHolder;
import com.wuhai.hhsc.utils.Utils;
import java.util.ArrayList;
import java.util.List;


/**
 * description:商品列表适配器
 * data: 2019/12/4
 * author: zhudi
 */
public class HomeGoodsAdapter extends BaseRecyclerAdapter {
    private Context mContext;
    private List<GoodsListInfo> list;
    private CallBackListener.OnClickListener onClickListener;

    public HomeGoodsAdapter(Context mContext, CallBackListener.OnClickListener onClickListener) {
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    public void setList(List<GoodsListInfo> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void updateList(List<GoodsListInfo> list) {
        if (list == null) {
            return;
        }

        if (this.list == null) {
            this.list = new ArrayList<>(1);
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void addList(List<GoodsListInfo> goods) {
        this.list.addAll(goods);
        notifyItemRangeInserted(this.list.size() - goods.size(), goods.size());
    }


    public GoodsListInfo getItem(int position) {
        return list == null ? null : list.get(position);
    }

    public List<GoodsListInfo> getList() {
        return list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        return new GoodsHolder(LayoutInflater.from(App.getInstance()).inflate(R.layout.item_list_goods_home, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        if (getItemCount() > 0) {
            if (holder instanceof GoodsHolder) {
                GoodsHolder goodsHolder = (GoodsHolder) holder;
                GoodsListInfo goodsListInfo = list.get(position);
                if (goodsListInfo != null) {
                    Utils.imageViewDisplayByUrl(mContext, goodsListInfo.getMainImgUrl(), goodsHolder.goodsIcon);
                    goodsHolder.goodsNameView.setText(goodsListInfo.getGoodsName());
                    goodsHolder.goodsPriceView.setText("￥" + goodsListInfo.getDiscountPrice());
                    goodsHolder.goodsIntegralView.setText("积分：" + goodsListInfo.getGiveIntegral());
                    goodsHolder.findSameView.setOnClickListener(new NoDoubleListener() {
                        @Override
                        protected void onNoDoubleClick(View v) {
                            onClickListener.onClick(position);
                        }
                    });
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
        TextView goodsNameView, goodsPriceView, goodsIntegralView, findSameView;

        public GoodsHolder(View itemView) {
            super(itemView);
            goodsIcon = itemView.findViewById(R.id.iv_goods);
            goodsNameView = itemView.findViewById(R.id.tv_name_goods);
            goodsPriceView = itemView.findViewById(R.id.tv_price_goods);
            goodsIntegralView = itemView.findViewById(R.id.tv_integral_goods);
            findSameView = itemView.findViewById(R.id.tv_find_same);
        }

    }
}
