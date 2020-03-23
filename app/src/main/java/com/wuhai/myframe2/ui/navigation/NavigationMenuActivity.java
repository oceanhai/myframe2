package com.wuhai.myframe2.ui.navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity2;
import com.wuhai.myframe2.ui.navigation.navigationmenu1.NavigationMenu1Activity;
import com.wuhai.myframe2.ui.navigation.navigationmenu2.NavigationMenu2Activity;
import com.wuhai.myframe2.ui.navigation.navigationmenu3.NavigationMenu3Activity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 导航菜单
 *
 * @author wuhai
 * create at 2016/4/25 10:26
 */
public class NavigationMenuActivity extends BaseActivity2 implements View.OnClickListener {

    @BindView(R.id.btn01)
    TextView btn01;
    @BindView(R.id.btn02)
    TextView btn02;
    @BindView(R.id.btn03)
    TextView btn03;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, NavigationMenuActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_navigation_menu);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01://寻医问药使用的自定义控件ScrollerTabView
                NavigationMenu1Activity.startActivity(this);
                break;
            case R.id.btn02://药品指南自定义SegmentedRadioGroup
                NavigationMenu2Activity.startActivity(this);
                break;
            case R.id.btn03://谷歌TabLayout+ViewPager
                NavigationMenu3Activity.startActivity(this);
                break;
        }
    }

}
