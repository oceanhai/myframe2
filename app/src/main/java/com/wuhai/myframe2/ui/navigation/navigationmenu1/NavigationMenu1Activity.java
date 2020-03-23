package com.wuhai.myframe2.ui.navigation.navigationmenu1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationMenu1Activity extends BaseActivity2 implements View.OnClickListener {

    @BindView(R.id.fast_Interrogation)
    TextView fastInterrogation;
    @BindView(R.id.book_Interrogation)
    TextView bookInterrogation;
    @BindView(R.id.ll_item_container)
    LinearLayout llItemContainer;
    @BindView(R.id.details_scrollview)
    ScrollerTabView detailsScrollview;
    @BindView(R.id.details_viewpager)
    ViewPager detailsViewpager;
    @BindView(R.id.tv3)
    TextView tv3;

    private List<Fragment> list = new ArrayList<>();

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, NavigationMenu1Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_navigation_menu1);
        ButterKnife.bind(this);

        initView();
        initListener();
    }

    private void initView() {

        fastInterrogation.setTextColor(Color.parseColor("#32b4c2"));
        detailsScrollview.setTabNum(2);
        detailsScrollview.setSelectedColor(Color.parseColor("#32b4c2"), Color.parseColor("#32b4c2"));

        list.add(new Fragment1());
        list.add(new Fragment2());
//        list.add(new Fragment3());
        detailsViewpager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        detailsViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int i, final float v, final int i1) {
                detailsScrollview.setOffset(i, v);

            }

            @Override
            public void onPageSelected(final int i) {
                if (i == 0) {
//                    fastInterrogation.setTextColor(getResources().getColor(R.color.app_base_color));
//                    bookInterrogation.setTextColor(getResources().getColor(R.color.c_333));
                    fastInterrogation.setTextColor(Color.parseColor("#32b4c2"));
                    bookInterrogation.setTextColor(Color.parseColor("#333333"));
                    tv3.setTextColor(Color.parseColor("#333333"));
                } else if (i == 1) {
                    fastInterrogation.setTextColor(Color.parseColor("#333333"));
                    bookInterrogation.setTextColor(Color.parseColor("#32b4c2"));
                    tv3.setTextColor(Color.parseColor("#333333"));
                } else if (i == 2) {
                    fastInterrogation.setTextColor(Color.parseColor("#333333"));
                    bookInterrogation.setTextColor(Color.parseColor("#333333"));
                    tv3.setTextColor(Color.parseColor("#32b4c2"));
                }
            }

            @Override
            public void onPageScrollStateChanged(final int i) {

            }
        });
    }

    private void initListener() {
        fastInterrogation.setOnClickListener(this);
        bookInterrogation.setOnClickListener(this);
        tv3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fast_Interrogation:
                detailsViewpager.setCurrentItem(0);//平滑，感觉有两个导航菜单的时候用还凑活
//                detailsViewpager.setCurrentItem(0, false);//不平滑 ※一旦不平滑 detailsScrollview.setOffset(i, v)就不起作用了，感觉超过两个导航菜单就不完美了
                detailsScrollview.setCurrentNum(0);
                break;
            case R.id.book_Interrogation:
                detailsViewpager.setCurrentItem(1);
//                detailsViewpager.setCurrentItem(1, false);
                detailsScrollview.setCurrentNum(1);
                break;
            case R.id.tv3:
                detailsViewpager.setCurrentItem(2);
//                detailsViewpager.setCurrentItem(2, false);
                detailsScrollview.setCurrentNum(2);
                break;
        }

    }

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
