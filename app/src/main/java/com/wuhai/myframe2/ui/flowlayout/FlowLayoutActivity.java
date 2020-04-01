package com.wuhai.myframe2.ui.flowlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity;
import com.wuhai.myframe2.ui.flowlayout.view.FlowLayout;
import com.wuhai.myframe2.ui.flowlayout.view.TagAdapter;
import com.wuhai.myframe2.ui.flowlayout.view.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 wuhai
 * <p>
 * 创建日期 2019/4/3 11:45
 * <p>
 * 描述：热门标签的流式布局FlowLayout
 */
public class FlowLayoutActivity extends BaseActivity {

    @BindView(R.id.hot_list)
    TagFlowLayout hotList;
    @BindView(R.id.ll_hot_search_container)
    LinearLayout llHotSearchContainer;

    private LayoutInflater mInflater;
    private TagAdapter tagAdapter;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, FlowLayoutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_flow_layout);
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

        List<String> hots = new ArrayList<>();
        hots.add("苹果");
        hots.add("小米");
        hots.add("华为");
        hots.add("OPPO");
        hots.add("诺基亚");
        hots.add("中兴通讯");
        hots.add("上海机场");
        hots.add("联想笔记本电脑");

        mInflater = LayoutInflater.from(getContext());
        tagAdapter = new TagAdapter<String>(hots) {
            @Override
            public View getView(FlowLayout parent, int position, String tag) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_hot_tag_view,
                        hotList, false);
                tv.setText(tag);
                return tv;
            }
        };
        hotList.setAdapter(tagAdapter);
        hotList.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(FlowLayoutActivity.this, hots.get(position), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void setListener() {

    }
}
