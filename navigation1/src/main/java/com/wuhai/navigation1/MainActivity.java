package com.wuhai.navigation1;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.wuhai.navigation1.utils.BankLog;

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
