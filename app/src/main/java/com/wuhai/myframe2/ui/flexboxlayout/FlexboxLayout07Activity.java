package com.wuhai.myframe2.ui.flexboxlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.wuhai.myframe2.R;
import com.wuhai.myframe2.adapter.BaseDataAdapter;
import com.wuhai.myframe2.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 wuhai
 * <p>
 * 创建日期 2019/4/3 11:45
 * <p>
 * 描述：FlexboxLayout实现tag
 */
public class FlexboxLayout07Activity extends BaseActivity implements View.OnClickListener, BaseDataAdapter.OnItemClickLitener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private TagAdapter mTagAdapter;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, FlexboxLayout07Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_flexbox_layout07);
        ButterKnife.bind(this);
        parseIntent();
        init();
        setListener();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if (intent != null) {

        }
    }

    @Override
    public void setStatistical() {

    }

    private void init() {
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setAlignItems(AlignItems.STRETCH);
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        recyclerView.setLayoutManager(flexboxLayoutManager);

        List<String> hots = new ArrayList<>();
        hots.add("苹果");
        hots.add("小米");
        hots.add("华为");
        hots.add("OPPO");
        hots.add("诺基亚");
        hots.add("中兴通讯");
        hots.add("上海机场");
        hots.add("联想笔记本电脑");

        mTagAdapter = new TagAdapter();
        mTagAdapter.setOnItemClickLitener(this);
        recyclerView.setAdapter(mTagAdapter);
        mTagAdapter.setData(hots);
    }

    private void setListener() {
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this,"点击了:"+mTagAdapter.getDataItem(position),Toast.LENGTH_SHORT).show();
    }

    class TagAdapter extends BaseDataAdapter<String> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(FlexboxLayout07Activity.this);
            View view = layoutInflater.inflate(R.layout.item_flexbox_layout, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ViewHolder holder1= (ViewHolder) holder;
            holder1.mHotTv.setText(getDataItem(position));
            holder1.mHotTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickLitener != null){
                        mOnItemClickLitener.onItemClick(v, position);
                    }
                }
            });
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView mHotTv;

            public ViewHolder (View view){
                super(view);
                mHotTv = (TextView) view.findViewById(R.id.item_hot_tv);
            }

        }
    }
}
