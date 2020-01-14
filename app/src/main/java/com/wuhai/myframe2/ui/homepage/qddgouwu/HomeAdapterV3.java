package com.wuhai.myframe2.ui.homepage.qddgouwu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.homepage.qddgouwu.view.CategoryCutTag;
import com.wuhai.myframe2.ui.homepage.qddgouwu.view.HomeBanner;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * createby yangzheng
 * date 2017/1/9
 * email yangzhenop@126.com
 */
public class HomeAdapterV3 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private HomeInfoV2 info;
    private Context mContext;

    public HomeAdapterV3(Context context){
        mContext = context;
    }

    public void setData(HomeInfoV2 data){
        this.info = data;
        notifyDataSetChanged();
    }

    public void addNew(ArrayList<SearchProInfo> newData){
        if(info == null)
            return;
        int size = getItemCount();
        if(newData.size() % 2 !=0){
            newData.remove(newData.size()-1);//TODO wuhai 这里又把最后一条数据remove了
        }
        info.addNew(newData);
        notifyItemRangeInserted(size,newData.size());
    }

    private HomeActionListener listener;

    public interface HomeActionListener{
        void onLinkImageClick(String url, String id, String title, String type);
    }

    public void setHomeLinkImageListener(HomeActionListener l){
        listener = l;
    }

    public HomeSpanSizeLookup getSpanSizeLookup(){
        return new HomeSpanSizeLookup();
    }

    public HomeItemDecoration getHomeItemDecoration(){
        return new HomeItemDecoration();
    }

    public class HomeSpanSizeLookup extends GridLayoutManager.SpanSizeLookup{

        @Override
        public int getSpanSize(int position) {
            if(position >= getItemCount()){
                return 12;
            }
            int size = 12;
            switch(getItemViewType(position)){
                case HomeInfoV2.TYPE_BANNER:
                    size = 12;
                    break;
                case HomeInfoV2.TYPE_QUICKENTRY:
                    size = 12;
                    break;
                case HomeInfoV2.TYPE_SUPERIMAGE:
                    size = 12;
                    break;
                case HomeInfoV2.TYPE_BIGIMAGE:
                    size = 12;
                    break;
                case HomeInfoV2.TYPE_SMALLIMAGE:
                    size = 3;
                    break;
                case HomeInfoV2.TYPE_HOTITEM:
                    size = 12;
                    break;
                case HomeInfoV2.TYPE_NEWITEM:
                    size = 6;
                    break;
                case HomeInfoV2.TYPE_NEWITEMTITLE:
                    size = 12;
                    break;
                case HomeInfoV2.TYPE_COMMONENTRANCE:
                    size = 4;
                    break;
                default:
                    break;
             }
            return size;
        }
    }

    private class HomeItemDecoration extends RecyclerView.ItemDecoration {

        private Paint paint = new Paint();

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            View child;
            int position;
            int count = parent.getChildCount();
            for (int i = 0; i < count; i++) {
                child = parent.getChildAt(i);
                position = parent.getChildAdapterPosition(child);
                if(getItemViewType(position) == HomeInfoV2.TYPE_SMALLIMAGE){
                    paint.setStrokeWidth(1);
                    paint.setColor(Color.parseColor("#e6e9ed"));
                    int endSm = info.indexs.get(info.resource.indexOf(HomeInfoV2.SMALLIMAGE));
                    drawDivider(position - endSm + info.smallImage.size()-1, 4, child, info.smallImage.size(), c);
                }
                if(getItemViewType(position) == HomeInfoV2.TYPE_NEWITEM){
                    paint.setStrokeWidth(1);
                    paint.setColor(Color.parseColor("#e6e9ed"));
                    int endNe = info.indexs.get(info.resource.indexOf(HomeInfoV2.NEWITEM));
                    drawDivider(position - endNe + info.newItem.size() - 1, 2, child, info.newItem.size(), c);
                }
                /*if(getItemViewType(position) == HomeInfoV2.TYPE_BIGIMAGE){
                    paint.setStrokeWidth(DimensionUtil.convertDpToPx(mContext,5f));
                    paint.setColor(Color.parseColor("#e6e9ed"));
                    int endHo = info.indexs.get(info.resource.indexOf(HomeInfoV2.BIGIMAGE));
                    if(position - endHo + info.bigImage.size()-1 == 0){
                        c.drawLine(child.getLeft(),child.getTop(),child.getRight(),child.getTop(),paint);
                    }
                }*/
            }
        }

        public void drawDivider(int i, int raw, View child, int count, Canvas c){
            if ((i + 1) % raw == 0) {
                c.drawLine(child.getLeft(), child.getBottom(), child.getRight(), child
                        .getBottom(), paint);
            } else if (i >= count - count % raw) {
                c.drawLine(child.getRight(), child.getTop(), child.getRight(), child
                        .getBottom(), paint);
            } else {
                c.drawLine(child.getLeft(), child.getBottom(), child.getRight(), child
                        .getBottom(), paint);
                c.drawLine(child.getRight(), child.getTop(), child.getRight(), child
                        .getBottom(), paint);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(info != null){
            return info.getItemType(position);
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch(viewType){
            case HomeInfoV2.TYPE_BANNER:
                if(info.banner.size() == 1){
                    holder = new LinkImageHolder(createView(R.layout.one_banner_image_item));
                }else {
                    holder = new BannerViewHolder(new HomeBanner(mContext));
                }
                break;
            case HomeInfoV2.TYPE_QUICKENTRY:
                holder = new QuickEntryHolder(View.inflate(mContext,R.layout.home_item_quike_entry, null));
//                holder = new LinkImageHolder(createView(R.layout.quike_entry_item));
                break;
            case HomeInfoV2.TYPE_SUPERIMAGE:
                holder = new LinkImageHolder(createView(R.layout.super_image_item));
                break;
            case HomeInfoV2.TYPE_BIGIMAGE:
                holder = new LinkImageHolder(createView(R.layout.big_image_item));
                break;
            case HomeInfoV2.TYPE_SMALLIMAGE:
                holder = new LinkImageHolder(createView(R.layout.small_image_item));
                break;
            case HomeInfoV2.TYPE_HOTITEM:
//                holder = new HotViewHolder(createView(R.layout.hot_item));
                holder = new HotViewHolderV2(createView(R.layout.hot_container));
                break;
            case HomeInfoV2.TYPE_NEWITEM:
                holder = new NewViewHolder(createView(R.layout.new_item));
                break;
            case HomeInfoV2.TYPE_NEWITEMTITLE:
                holder = new TitleHolder(createView(R.layout.hot_item_header));
                break;
            case HomeInfoV2.TYPE_COMMONENTRANCE:
                holder = new LinkImageHolder(createView(R.layout.common_entrance_item));
                break;
            default:
                break;
        }
        return holder;
    }

    private View createView(int layout){
        return View.inflate(mContext,layout,null);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch(type){
            case HomeInfoV2.TYPE_BANNER://首页-Banner
                Log.e("HomeAdapterV3", "首页-Banner resouce里的位置:"+info.resource.indexOf(HomeInfoV2.BANNER)+
                        ",position="+position);
                if(holder instanceof BannerViewHolder){
                    final BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
                    bannerViewHolder.item.set(info.banner);
                }else{
                    LinkImageHolder bannerViewHolder = (LinkImageHolder)holder;
                    final LinkImage imageBn = info.banner.get(0);
                    bannerViewHolder.image.setImageURI(Uri.parse(imageBn.image));
                    bannerViewHolder.title.setText(imageBn.title);
                    bannerViewHolder.container.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(listener != null){
                                listener.onLinkImageClick(imageBn.url,imageBn.id,imageBn.title,"首页-Banner");
                            }
                        }
                    });
                }
                break;
            case HomeInfoV2.TYPE_QUICKENTRY://首页-快捷入口
                Log.e("HomeAdapterV3", "首页-快捷入口 resouce里的位置:"+info.resource.indexOf(HomeInfoV2.QUICKENTRY)+
                        ",position="+position);
                QuickEntryHolder quickEntryHolder = (QuickEntryHolder)holder;
                QuickEntryAdapter qeAdapter = new QuickEntryAdapter();
                quickEntryHolder.mContainer.setLayoutManager(new GridLayoutManager(mContext, info.quickEntry.size(), GridLayoutManager.VERTICAL, false));
                quickEntryHolder.mContainer.setAdapter(qeAdapter);
                qeAdapter.setData(info.quickEntry);
                if(info.quickEntryBackground!=null && info.quickEntryBackground.size() > 0){
                    String uri = info.quickEntryBackground.get(0).image;
                    quickEntryHolder.mBackground.setImageURI(Uri.parse(uri));
                    quickEntryHolder.mDivider.setVisibility(View.GONE);
                    quickEntryHolder.mContainer.setBackgroundColor(Color.TRANSPARENT);
                }else{
                    quickEntryHolder.mBackground.setVisibility(View.GONE);
                    quickEntryHolder.mDivider.setVisibility(View.VISIBLE);
                    quickEntryHolder.mContainer.setBackgroundColor(Color.WHITE);
                }
                break;
            case HomeInfoV2.TYPE_SUPERIMAGE://首页-大图推荐 TODO 线上没数据
                LinkImageHolder superHolder = (LinkImageHolder)holder;
                int endSu = info.indexs.get(info.resource.indexOf(HomeInfoV2.SUPERIMAGE));
                Log.e("HomeAdapterV3", "首页-大图推荐 resouce里的位置:"+info.resource.indexOf(HomeInfoV2.SUPERIMAGE)+
                        ",endSu="+endSu+",position="+position);
                final LinkImage imageSu = info.superImage.get(position - endSu + info.superImage.size()-1);
                superHolder.image.setImageURI(Uri.parse(imageSu.image));
                superHolder.title.setText(imageSu.title);
                superHolder.container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(listener != null){
                            listener.onLinkImageClick(imageSu.url,imageSu.id,imageSu.title,"首页-大图推荐");
                        }
                    }
                });
                break;
            case HomeInfoV2.TYPE_COMMONENTRANCE://首页-常用入口
                LinkImageHolder ceHolder = (LinkImageHolder)holder;
                int endCe = info.indexs.get(info.resource.indexOf(HomeInfoV2.COMMONENTRANCE));
                Log.e("HomeAdapterV3", "首页-常用入口 resouce里的位置:"+info.resource.indexOf(HomeInfoV2.COMMONENTRANCE)+
                        ",endCe="+endCe+",position="+position);
                final LinkImage imageCe = info.commonEntrance.get(position - endCe + info.commonEntrance.size()-1);
                ceHolder.image.setImageURI(Uri.parse(imageCe.image));
                ceHolder.container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(listener != null){
                            listener.onLinkImageClick(imageCe.url,imageCe.id,imageCe.title,"首页-常用入口");
                        }
                    }
                });
                break;
            case HomeInfoV2.TYPE_BIGIMAGE://首页-横图推荐
                LinkImageHolder bigHolder = (LinkImageHolder)holder;
                int endBi = info.indexs.get(info.resource.indexOf(HomeInfoV2.BIGIMAGE));
                Log.e("HomeAdapterV3", "首页-横图推荐 resouce里的位置:"+info.resource.indexOf(HomeInfoV2.BIGIMAGE)+
                        ",endBi="+endBi+",position="+position);
                final LinkImage imageBi = info.bigImage.get(position - endBi + info.bigImage.size()-1);
                bigHolder.container.findViewById(R.id.divider).setVisibility(
                        position - endBi + info.bigImage.size()-1 == 0 ? View.VISIBLE : View.GONE);
                bigHolder.image.setImageURI(Uri.parse(imageBi.image));
                bigHolder.title.setText(imageBi.title);
                bigHolder.container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(listener != null){
                            listener.onLinkImageClick(imageBi.url,imageBi.id,imageBi.title,"首页-横图推荐");
                        }
                    }
                });
                break;
            case HomeInfoV2.TYPE_SMALLIMAGE://首页-小图推荐
                LinkImageHolder smallHolder = (LinkImageHolder)holder;
                int endSm = info.indexs.get(info.resource.indexOf(HomeInfoV2.SMALLIMAGE));
                Log.e("HomeAdapterV3", "首页-小图推荐 resouce里的位置:"+info.resource.indexOf(HomeInfoV2.SMALLIMAGE)+
                        ",endSm="+endSm+",position="+position);
                final LinkImage imageSm = info.smallImage.get(position - endSm + info.smallImage.size()-1);
                smallHolder.image.setImageURI(Uri.parse(imageSm.image));
                smallHolder.title.setText(imageSm.title);
                smallHolder.container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(listener != null){
                            listener.onLinkImageClick(imageSm.url,imageSm.id,imageSm.title,"首页-小图推荐");
                        }
                    }
                });
                break;
            case HomeInfoV2.TYPE_HOTITEM://首页-新品上架
                Log.e("HomeAdapterV3", "首页-新品上架 resouce里的位置:"+info.resource.indexOf(HomeInfoV2.HOTITEM)+
                        ",position="+position);
                HotViewHolderV2 hotViewHolder = (HotViewHolderV2)holder;
                switch(info.hotItem.size()){
                    default:
                    case 5:
                        hotViewHolder.image5.setImageURI(Uri.parse(info.hotItem.get(4).icon));
                        hotViewHolder.image5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                listener.onLinkImageClick(info.hotItem.get(4).webviewUrl,info.hotItem.get(4).id, info
                                        .hotItem.get(4).name, "首页-新品上架");
                            }
                        });
                    case 4:
                        hotViewHolder.image4.setImageURI(Uri.parse(info.hotItem.get(3).icon));
                        hotViewHolder.image4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                listener.onLinkImageClick(info.hotItem.get(3).webviewUrl,info.hotItem.get(3).id, info
                                        .hotItem.get(3).name, "首页-新品上架");
                            }
                        });
                    case 3:
                        hotViewHolder.image3.setImageURI(Uri.parse(info.hotItem.get(2).icon));
                        hotViewHolder.image3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                listener.onLinkImageClick(info.hotItem.get(2).webviewUrl, info.hotItem.get(2).id,info
                                        .hotItem.get(2).name, "首页-新品上架");
                            }
                        });
                    case 2:
                        hotViewHolder.image2.setImageURI(Uri.parse(info.hotItem.get(1).icon));
                        hotViewHolder.image2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                listener.onLinkImageClick(info.hotItem.get(1).webviewUrl, info.hotItem.get(1).id,info
                                        .hotItem.get(1).name, "首页-新品上架");
                            }
                        });
                    case 1:
                        hotViewHolder.image1.setImageURI(Uri.parse(info.hotItem.get(0).icon));
                        hotViewHolder.image1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                listener.onLinkImageClick(info.hotItem.get(0).webviewUrl,info.hotItem.get(0).id, info
                                        .hotItem.get(0).name, "首页-新品上架");
                            }
                        });
                    case 0:
                 }
                /*HotViewHolder hotViewHolder = (HotViewHolder)holder;
                int endHo = info.indexs.get(info.resource.indexOf(HomeInfoV2.HOTITEM));
                final SearchProInfo itemHot = info.hotItem.get(position - endHo + info.hotItem.size()-1);
                hotViewHolder.header.setVisibility(position - endHo + info.hotItem.size() - 1 == 0 ? View.VISIBLE : View.GONE);
                hotViewHolder.mFlag.setCut((int) itemHot.cutPrice);
                hotViewHolder.mName.setText(itemHot.name);
                hotViewHolder.mImg.setImageURI(Uri.parse(itemHot.icon));
                hotViewHolder.mMonthly.setText("月供 ¥ " + new BigDecimal(itemHot.monthlyPrincipal)
                        .setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                hotViewHolder.mPrice.setText("¥ " + new BigDecimal(itemHot.price).setScale(2,
                        BigDecimal.ROUND_HALF_UP).toString());
                hotViewHolder.mDescription.setText(itemHot.description);
                hotViewHolder.hotItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(listener != null){
                            listener.onLinkImageClick(itemHot.webviewUrl, itemHot.name, "首页-热卖商品");
                        }
                    }
                });*/
                break;
            case HomeInfoV2.TYPE_NEWITEM://首页-热卖商品
                NewViewHolder newViewHolder = (NewViewHolder)holder;
                int endNe = info.indexs.get(info.resource.indexOf(HomeInfoV2.NEWITEM));
                Log.e("HomeAdapterV3", "首页-热卖商品 resouce里的位置:"+info.resource.indexOf(HomeInfoV2.NEWITEM)+
                        ",endNe="+endNe+",position="+position);
                final SearchProInfo itemNew = info.newItem.get(position - endNe + info.newItem.size()-1);
                //TODO 以前是item里有头部，现在单独抽出作为一种type来设置头部，所以这里gone
                newViewHolder.header.setVisibility(/*position - endNe + info.newItem.size() - 1 == 0 ? View.VISIBLE : */View.GONE);
                newViewHolder.mFlag.setCut((int) itemNew.cutPrice);
                newViewHolder.mName.setText(itemNew.name);
                newViewHolder.mImg.setImageURI(Uri.parse(itemNew.icon));
                newViewHolder.mMonthly.setText("月供 ¥ " + new BigDecimal(itemNew.monthlyPrincipal)
                        .setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                newViewHolder.mPrice.setText("¥ " + new BigDecimal(itemNew.price).setScale(2,
                        BigDecimal.ROUND_HALF_UP).toString());
                newViewHolder.newItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onLinkImageClick(itemNew.webviewUrl, itemNew.id,itemNew.name, "首页-热卖商品");
                        }
                    }
                });
                break;
            case HomeInfoV2.TYPE_NEWITEMTITLE:
                Log.e("HomeAdapterV3", "热卖商品-title resouce里的位置:"+info.resource.indexOf(HomeInfoV2.NEWITEMTITLE)+
                        ",position="+position);
                TitleHolder titleHolder = (TitleHolder)holder;
                titleHolder.mText.setText("热卖商品");
                break;
            default:
                break;
         }

    }

    @Override
    public int getItemCount() {
        if(null == info)
            return 0;
        int size = 0;
        if(info.banner.size() > 0){
            size++;
        }
        if(info.newItem.size() > 0){
            size++;
        }
        if(info.quickEntry.size() > 0){
            size++;
        }
        if(info.hotItem.size() > 0){
            size++;
        }
//        size += info.hotItem.size();
        size += info.bigImage.size();
//        size += info.quickEntry.size();
        size += info.smallImage.size();
        size += info.superImage.size();
        size += info.newItem.size();
        size += info.commonEntrance.size();
        return size;
    }

    public class QuickEntryHolder extends RecyclerView.ViewHolder{

        private RecyclerView mContainer;
        private SimpleDraweeView mBackground;
        private View mDivider;

        public QuickEntryHolder(View itemView) {
            super(itemView);
            mContainer = (RecyclerView) itemView.findViewById(R.id.entry_container);
            mContainer.setNestedScrollingEnabled(false);
            mBackground = (SimpleDraweeView) itemView.findViewById(R.id.background);
            mDivider = itemView.findViewById(R.id.divider);
        }
    }

    public class TitleHolder extends RecyclerView.ViewHolder{

        public TextView mText;

        public TitleHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.text);
        }
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {

        private HomeBanner item;

        public BannerViewHolder(View itemView) {
            super(itemView);
            item = (HomeBanner) itemView;
        }
    }

    public class LinkImageHolder extends RecyclerView.ViewHolder{

        private SimpleDraweeView image;
        private TextView title;
        private View container;

        public LinkImageHolder(View itemView) {
            super(itemView);
            container = itemView;
            image = (SimpleDraweeView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

    class NewViewHolder extends RecyclerView.ViewHolder {

        private View newItem;
        public View header;
        public CategoryCutTag mFlag;
        public SimpleDraweeView mImg;
        public TextView mName;
        public TextView mPrice;
        public TextView mMonthly;
        public TextView mExtra;

        public NewViewHolder(View itemView) {
            super(itemView);
            newItem = itemView;
            header = itemView.findViewById(R.id.header);
            mFlag = (CategoryCutTag) itemView.findViewById(R.id.pro_flag);
            mImg = (SimpleDraweeView) itemView.findViewById(R.id.pro_img);
            mName = (TextView) itemView.findViewById(R.id.pro_name);
            mPrice = (TextView) itemView.findViewById(R.id.pro_price);
            mMonthly = (TextView) itemView.findViewById(R.id.pro_monthly);
            mExtra = (TextView) itemView.findViewById(R.id.pro_name_extra);
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder {

        public View hotItem;
        public View header;
        public CategoryCutTag mFlag;
        public SimpleDraweeView mImg;
        public TextView mName;
        public TextView mPrice;
        public TextView mMonthly;
        public TextView mExtra;
//        public TextView mDescription;

        public HotViewHolder(View itemView) {
            super(itemView);
            hotItem = itemView;
            header = itemView.findViewById(R.id.header);
            mFlag = (CategoryCutTag) itemView.findViewById(R.id.pro_flag);
            mImg = (SimpleDraweeView) itemView.findViewById(R.id.pro_img);
            mName = (TextView) itemView.findViewById(R.id.pro_name);
            mPrice = (TextView) itemView.findViewById(R.id.pro_price);
            mMonthly = (TextView) itemView.findViewById(R.id.pro_monthly);
            mExtra = (TextView) itemView.findViewById(R.id.pro_name_extra);
//            mDescription = (TextView) itemView.findViewById(R.id.pro_description);
        }
    }

    public class QuickEntryAdapter extends RecyclerView.Adapter<QuickEntryAdapter.LinkImageHolder>{

        private ArrayList<LinkImage> images;

        public QuickEntryAdapter(){
            images = new ArrayList<>();
        }

        public void setData(ArrayList<LinkImage> data){
            images = data;
            notifyDataSetChanged();
        }

        @Override
        public LinkImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new LinkImageHolder(View.inflate(mContext,R.layout.quike_entry_item,null));
        }

        @Override
        public void onBindViewHolder(LinkImageHolder holder, int position) {
            final LinkImage image = images.get(position);
            final String url = image.url;
            final String title = image.title;
            final String id = image.id;
            holder.image.setImageURI(Uri.parse(image.image));
            holder.title.setText(image.title);
            if (!TextUtils.isEmpty(image.titleColor)) {
                holder.title.setTextColor(Color.parseColor("#" + image.titleColor));
            }
            holder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onLinkImageClick(url,id,title,"首页-快捷入口");
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return images.size();
        }

        public class LinkImageHolder extends RecyclerView.ViewHolder{

            private SimpleDraweeView image;
            private TextView title;
            private View container;

            public LinkImageHolder(View itemView) {
                super(itemView);
                container = itemView;
                image = (SimpleDraweeView) itemView.findViewById(R.id.image);
                title = (TextView) itemView.findViewById(R.id.title);
            }
        }
    }

    class HotViewHolderV2 extends RecyclerView.ViewHolder{

        SimpleDraweeView image1;
        SimpleDraweeView image2;
        SimpleDraweeView image3;
        SimpleDraweeView image4;
        SimpleDraweeView image5;

        public HotViewHolderV2(View itemView) {
            super(itemView);
            image1 = (SimpleDraweeView) itemView.findViewById(R.id.image_1);
            image2 = (SimpleDraweeView) itemView.findViewById(R.id.image_2);
            image3 = (SimpleDraweeView) itemView.findViewById(R.id.image_3);
            image4 = (SimpleDraweeView) itemView.findViewById(R.id.image_4);
            image5 = (SimpleDraweeView) itemView.findViewById(R.id.image_5);
        }
    }

}