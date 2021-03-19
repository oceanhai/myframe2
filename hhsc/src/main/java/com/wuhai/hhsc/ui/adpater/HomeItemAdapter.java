package com.wuhai.hhsc.ui.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wuhai.hhsc.R;
import com.wuhai.hhsc.app.App;
import com.wuhai.hhsc.model.HomeArea;
import com.wuhai.hhsc.ui.viewholder.BaseViewHolder;
import com.wuhai.hhsc.utils.StringUtils;
import com.wuhai.hhsc.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * description:首页分类item
 * data: 2019/12/4
 * <p>
 * author: zhudi
 */
public class HomeItemAdapter extends BaseRecyclerAdapter {
    private Context mContext;
    private List<HomeArea> list;

    public HomeItemAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(List<HomeArea> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void updateList(List<HomeArea> list) {
        if (list == null) {
            return;
        }

        if (this.list == null) {
            this.list = new ArrayList<>(1);
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void addList(List<HomeArea> list) {
        this.list.addAll(list);
        notifyItemRangeInserted(this.list.size() - list.size(), list.size());
    }


    public HomeArea getItem(int position) {
        return list == null ? null : list.get(position);
    }

    public List<HomeArea> getList() {
        return list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        return new ItemHolder(LayoutInflater.from(App.getInstance()).inflate(R.layout.home_item_layout, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        if (getItemCount() > 0) {
            if (holder instanceof ItemHolder) {
                ItemHolder itemHolder = (ItemHolder) holder;
                HomeArea homeArea = list.get(position);
                if (homeArea != null) {
                    Utils.imageViewDisplayByUrl(mContext,homeArea.getImgUri(), itemHolder.itemIcon);
                    if (!StringUtils.isEmpty(homeArea.getImgName())) {
                        itemHolder.itemNameView.setText(homeArea.getImgName());
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class ItemHolder extends BaseViewHolder {
        CircleImageView itemIcon;
        TextView itemNameView;

        public ItemHolder(View itemView) {
            super(itemView);
            itemIcon = itemView.findViewById(R.id.iv_item);
            itemNameView = itemView.findViewById(R.id.tv_item);
        }

    }

}
