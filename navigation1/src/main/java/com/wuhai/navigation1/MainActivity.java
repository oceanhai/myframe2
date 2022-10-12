package com.wuhai.navigation1;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.wuhai.navigation1.utils.BankLog;
import com.wuhai.navigation1.utils.StatusBarUtils;

/**
 * TODO
 * 抽取自钱到到下导航
 * 我擦 把basewebview释放开就崩溃  坑
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mContentViewPager;
    private NavigationTabLayout mTabLayout;
    private MainAdapter mAdapter;

    private boolean mIsBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //沉浸式
        StatusBarUtils.setRootViewFitsSystemWindows(this, false);
        StatusBarUtils.setTransparentForWindow(this);

        setContentView(R.layout.activity_main);

        mContentViewPager = findViewById(R.id.main_content_view_pager);
        mTabLayout = findViewById(R.id.main_tab_layout);

        initData();
        initViews();
    }

    private void initViews() {
        // Set up ViewPager.
        mContentViewPager.setOffscreenPageLimit(MainAdapter.PAGE_COUNT - 1);
        mContentViewPager.setPageTransformer(true, new SimplePageTransformer());
        mContentViewPager.setAdapter(mAdapter);

        // Set up tabs.
        mTabLayout.setViewPager(mContentViewPager);
        mTabLayout.setOnTabItemClickListener(new NavigationTabLayout.OnTabItemClickListener() {
            @Override
            public boolean handleTabItemClick(int position) {
                BankLog.e("position="+position);
                switch (position){
                    case 0:
                        //设置状态栏纯色 不加半透明效果 字体为白色
                        StatusBarUtils.setColorDartMode(MainActivity.this,
                                ContextCompat.getColor(MainActivity.this, R.color.transparent));
                        break;
                    case 1:
                    case 2:
                    case 3:
                        //设置状态栏纯色 不加半透明效果 字体为黑色
                        StatusBarUtils.setColorLightMode(MainActivity.this,
                                ContextCompat.getColor(MainActivity.this, R.color.transparent));
                        break;
                }
                return false;
            }
        });
    }

    private void initData() {
        final String[] tabTitles = getResources().getStringArray(R.array.main_tab_login_titles);
        final int[] tabIconResNormals = new int[]{R.drawable.main_tab_loan_normal,
                R.drawable.main_tab_home_shop_normal,
                R.drawable.main_icon_loan_supermarket_normal,
                 R.drawable.main_tab_profile_normal};
        final int[] tabIconResSelects = new int[]{ R.drawable.main_tab_loan_selected,
                R.drawable.main_tab_home_shop_selected,
                R.drawable.main_icon_loan_supermarket_selected,
                R.drawable.main_tab_profile_selected};
        mAdapter = new MainAdapter(this.getSupportFragmentManager(), tabTitles,
                tabIconResNormals, tabIconResSelects);

        mIsBackPressed = false;
    }

    private class SimplePageTransformer implements ViewPager.PageTransformer {
        @Override
        public void transformPage(View page, float position) {
            page.setAlpha(1);
        }
    }
}
