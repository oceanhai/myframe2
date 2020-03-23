package com.wuhai.myframe2.ui.navigation.navigationmenu3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationMenu3Activity extends BaseActivity2 {


    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, NavigationMenu3Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_navigation_menu3);
        ButterKnife.bind(this);

        List<String> titles = new ArrayList<>();
        titles.add("美少女战士");
        titles.add("海贼王");
        titles.add("火影忍者");
        titles.add("死神");
        titles.add("七龙珠");
        titles.add("魔兽世界");

        viewPager.setAdapter(new ListPagerAdapter(getSupportFragmentManager(), titles));
        tabLayout.setupWithViewPager(viewPager);

    }
}
