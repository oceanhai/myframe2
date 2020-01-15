package com.wuhai.myframe2.ui.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wuhai.myframe2.R;
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
 * 描述：使用特殊的(自定义)布局管理器
 * 参考文章 https://blog.csdn.net/liaoinstan/article/details/52671101
 *        方案二：使用特殊的(自定义)布局管理器
 *
 *        分割线   参考文章    https://www.jianshu.com/p/b46a4ff7c10a
 *
 */
public class HomePageActivity1 extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    private HomePageAdapter1 mHomePageAdapter1;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, HomePageActivity1.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_home_page1);
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
        List<HomePageData1> list = new ArrayList<>();
        list.add(new HomePageData1(1,"type1"));
        list.add(new HomePageData1(2,"type2_head"));
        for (int x=0;x<4;x++){
            list.add(new HomePageData1(3,"type2"));
        }
        list.add(new HomePageData1(4,"type3_head"));
        for (int x=0;x<9;x++){
            list.add(new HomePageData1(5,"type3"));
        }


        GridLayoutManager layoutManager = new GridLayoutManager(this, 6,
                GridLayoutManager.VERTICAL,false){
            @Override
            public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
                super.smoothScrollToPosition(recyclerView, state, position);
            }
        };
        mHomePageAdapter1 = new HomePageAdapter1(this, list);
        layoutManager.setSpanSizeLookup(mHomePageAdapter1.getSpanSizeLookup());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mHomePageAdapter1);
    }

    private void setListener() {

    }
}
