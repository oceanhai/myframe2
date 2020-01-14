package com.wuhai.myframe2.ui.homepage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wuhai.myframe2.R;

import java.util.List;


/**
 * 作者 wh
 *
 * 创建日期 2020/1/13 14:52
 *
 * 描述：
 */
public class HomePageAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_SLIDER = 1;
    public static final int TYPE_TYPE2_HEAD = 2;
    public static final int TYPE_TYPE2 = 3;
    public static final int TYPE_TYPE3_HEAD = 4;
    public static final int TYPE_TYPE3 = 5;

    private Context mContext;
    private List<HomePageData1> list;

    public HomePageAdapter1(Context context, List<HomePageData1> list){
        mContext = context;
        this.list = list;
    }

    private HomeActionListener listener;

    public interface HomeActionListener{
        void onLinkImageClick(String url, String id, String title, String type);
    }

    public void setHomeLinkImageListener(HomeActionListener l){
        listener = l;
    }

    /**
     * GridLayoutManager setSpanSizeLookup
     * 获取适配器中每个项占用的跨距数
     * @return
     */
    public HomeSpanSizeLookup getSpanSizeLookup(){
        return new HomeSpanSizeLookup();
    }

    /**
     *  分割线
     */
//    public HomeItemDecoration getHomeItemDecoration(){
//        return new HomeItemDecoration();
//    }

    public class HomeSpanSizeLookup extends GridLayoutManager.SpanSizeLookup{

        @Override
        public int getSpanSize(int position) {
            if(position >= getItemCount()){
                return 6;
            }
            int size;
            switch (getItemViewType(position)){
                case TYPE_SLIDER:
                case TYPE_TYPE2_HEAD:
                case TYPE_TYPE3_HEAD:
                    size = 6;
                    break;
                case TYPE_TYPE2:
                    size = 3;
                    break;
                case TYPE_TYPE3:
                    size = 2;
                    break;
                default:
                    size = 6;
                    break;
            }
            return size;
        }
    }

//    private class HomeItemDecoration extends RecyclerView.ItemDecoration {
//
//        private Paint paint = new Paint();
//
//        @Override
//        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
//            View child;
//            int position;
//            int count = parent.getChildCount();
//            for (int i = 0; i < count; i++) {
//                child = parent.getChildAt(i);
//                position = parent.getChildAdapterPosition(child);
//                if(getItemViewType(position) == HomeInfoV2.TYPE_SMALLIMAGE){
//                    paint.setStrokeWidth(1);
//                    paint.setColor(Color.parseColor("#e6e9ed"));
//                    int endSm = info.indexs.get(info.resource.indexOf(HomeInfoV2.SMALLIMAGE));
//                    drawDivider(position - endSm + info.smallImage.size()-1, 4, child, info.smallImage.size(), c);
//                }
//                if(getItemViewType(position) == HomeInfoV2.TYPE_NEWITEM){
//                    paint.setStrokeWidth(1);
//                    paint.setColor(Color.parseColor("#e6e9ed"));
//                    int endNe = info.indexs.get(info.resource.indexOf(HomeInfoV2.NEWITEM));
//                    drawDivider(position - endNe + info.newItem.size() - 1, 2, child, info.newItem.size(), c);
//                }
//                /*if(getItemViewType(position) == HomeInfoV2.TYPE_BIGIMAGE){
//                    paint.setStrokeWidth(DimensionUtil.convertDpToPx(mContext,5f));
//                    paint.setColor(Color.parseColor("#e6e9ed"));
//                    int endHo = info.indexs.get(info.resource.indexOf(HomeInfoV2.BIGIMAGE));
//                    if(position - endHo + info.bigImage.size()-1 == 0){
//                        c.drawLine(child.getLeft(),child.getTop(),child.getRight(),child.getTop(),paint);
//                    }
//                }*/
//            }
//        }
//
//        public void drawDivider(int i, int raw, View child, int count, Canvas c){
//            if ((i + 1) % raw == 0) {
//                c.drawLine(child.getLeft(), child.getBottom(), child.getRight(), child
//                        .getBottom(), paint);
//            } else if (i >= count - count % raw) {
//                c.drawLine(child.getRight(), child.getTop(), child.getRight(), child
//                        .getBottom(), paint);
//            } else {
//                c.drawLine(child.getLeft(), child.getBottom(), child.getRight(), child
//                        .getBottom(), paint);
//                c.drawLine(child.getRight(), child.getTop(), child.getRight(), child
//                        .getBottom(), paint);
//            }
//        }
//    }

    @Override
    public int getItemViewType(int position) {
        if(list != null){
            return list.get(position).getType();
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch(viewType){
            case TYPE_SLIDER:
                holder = new TitleHolder(createView(R.layout.item_home_page1));
                break;
            case TYPE_TYPE2_HEAD:
                holder = new TitleHolder(createView(R.layout.item_home_page1));
                break;
            case TYPE_TYPE2:
                holder = new TitleHolder(createView(R.layout.item_home_page1));
                break;
            case TYPE_TYPE3_HEAD:
                holder = new TitleHolder(createView(R.layout.item_home_page1));
                break;
            case TYPE_TYPE3:
                holder = new TitleHolder(createView(R.layout.item_home_page1));
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
            case TYPE_SLIDER:
                TitleHolder holder1 = (TitleHolder)holder;
                holder1.mText.setText("TYPE_SLIDER");
                break;
            case TYPE_TYPE2_HEAD:
                TitleHolder holder2 = (TitleHolder)holder;
                holder2.mText.setText("TYPE_TYPE2_HEAD");
                break;
            case TYPE_TYPE2:
                TitleHolder holder3 = (TitleHolder)holder;
                holder3.mText.setText("TYPE_TYPE2");
                break;
            case TYPE_TYPE3_HEAD:
                TitleHolder holder4 = (TitleHolder)holder;
                holder4.mText.setText("TYPE_TYPE3_HEAD");
                break;
            case TYPE_TYPE3:
                TitleHolder holder5 = (TitleHolder)holder;
                holder5.mText.setText("TYPE_TYPE3");
                break;
            default:
                break;
         }

    }

    @Override
    public int getItemCount() {
        if(null == list)
            return 0;
        return list.size();
    }

    public class TitleHolder extends RecyclerView.ViewHolder{

        public TextView mText;

        public TitleHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.text);
        }
    }

}