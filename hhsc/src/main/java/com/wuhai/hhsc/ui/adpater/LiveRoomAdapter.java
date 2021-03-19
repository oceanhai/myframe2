package com.wuhai.hhsc.ui.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.wuhai.hhsc.R;
import com.wuhai.hhsc.app.App;
import com.wuhai.hhsc.model.HomeActivityLiveVO;
import com.wuhai.hhsc.ui.viewholder.BaseViewHolder;
import com.wuhai.hhsc.utils.StringUtils;
import com.wuhai.hhsc.utils.Utils;
import java.util.ArrayList;
import java.util.List;


/**
 * description:直播间列表适配器
 * data: 2019/12/4
 * author: zhudi
 */
public class LiveRoomAdapter extends BaseRecyclerAdapter {
    private Context mContext;
    private List<HomeActivityLiveVO> list = new ArrayList<>(1);

    public LiveRoomAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(List<HomeActivityLiveVO> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void updateList(List<HomeActivityLiveVO> list) {
        if (list == null) {
            return;
        }

        if (this.list == null) {
            this.list = new ArrayList<>(1);
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void addList(List<HomeActivityLiveVO> list) {
        this.list.addAll(list);
        notifyItemRangeInserted(this.list.size() - list.size(), list.size());
    }


    public HomeActivityLiveVO getItem(int position) {
        return list == null ? null : list.get(position);
    }

    public List<HomeActivityLiveVO> getList() {
        return list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        return new LiveHolder(LayoutInflater.from(App.getInstance()).inflate(R.layout.item_live_room, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        if (getItemCount() > 0) {
            if (holder instanceof LiveHolder) {
                LiveHolder liveHolder = (LiveHolder) holder;
                HomeActivityLiveVO homeActivityLiveVO = list.get(position);
                if (homeActivityLiveVO != null) {
                    Utils.cornerImageViewDisplayByUrl(mContext,homeActivityLiveVO.getFrontCover(), liveHolder.liveIcon, 10);
                    if (homeActivityLiveVO.isLiving()) {
                        liveHolder.liveStatusView.setVisibility(View.VISIBLE);
                    } else {
                        liveHolder.liveStatusView.setVisibility(View.GONE);
                    }
                    liveHolder.audienceNumView.setText(homeActivityLiveVO.getOnlineNum() + "人观看");
                    if (!StringUtils.isEmpty(homeActivityLiveVO.getTitle())) {
                        liveHolder.liveNameView.setText(homeActivityLiveVO.getTitle());
                    }
                    int goodsNum = homeActivityLiveVO.getGoodsNum();
                    if (goodsNum > 0) {
                        Utils.cornerImageViewDisplayByUrl(mContext,homeActivityLiveVO.getMainImgUrl(), liveHolder.goodsIcon, 10);
                        if (!StringUtils.isEmpty(homeActivityLiveVO.getGoodsName())) {
                            liveHolder.goodsNameView.setText(homeActivityLiveVO.getGoodsName());
                        }
                        liveHolder.goodsPriceView.setText("￥" + homeActivityLiveVO.getDiscountPrice());

                        liveHolder.goodsNumView.setText(goodsNum + "件商品直播热卖中");
                        liveHolder.goodsView.setVisibility(View.VISIBLE);
                        liveHolder.goodsNumView.setVisibility(View.VISIBLE);
                    } else {
                        liveHolder.goodsView.setVisibility(View.GONE);
                        liveHolder.goodsNumView.setVisibility(View.GONE);
                    }
                    if (position == getItemCount() - 1) {
                        liveHolder.lineView.setVisibility(View.GONE);
                    } else {
                        liveHolder.lineView.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class LiveHolder extends BaseViewHolder {
        ImageView liveIcon, goodsIcon;
        TextView goodsNameView, audienceNumView, goodsNumView, goodsPriceView, liveStatusView, liveNameView;
        RelativeLayout goodsView;
        View lineView;

        public LiveHolder(View itemView) {
            super(itemView);
            goodsView = itemView.findViewById(R.id.rl_goods);
            goodsIcon = itemView.findViewById(R.id.iv_icon_goods);
            liveIcon = itemView.findViewById(R.id.iv_icon_live);
            goodsNameView = itemView.findViewById(R.id.tv_name_goods);
            liveStatusView = itemView.findViewById(R.id.tv_live_staus);
            goodsNumView = itemView.findViewById(R.id.tv_num_goods);
            goodsPriceView = itemView.findViewById(R.id.tv_price_goods);
            audienceNumView = itemView.findViewById(R.id.tv_num_audience);
            liveNameView = itemView.findViewById(R.id.tv_name_live);
            lineView = itemView.findViewById(R.id.view_line_bottom);
        }

    }
}
