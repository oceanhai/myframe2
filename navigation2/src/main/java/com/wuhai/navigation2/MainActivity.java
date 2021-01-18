package com.wuhai.navigation2;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pactera.k4.bottomnavigation.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 wh
 * 
 * 创建日期 2021/1/18 10:45
 * 
 * 描述：
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "navigation2";

    private BottomNavigationViewEx mBnve;
    private ViewPager mVp;
    private Button mChange;

    private VpAdapter adapter;

    // collections
    private SparseIntArray items;// used for change ViewPager selected item
    private List<Fragment> fragments;// used for ViewPager adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initEvent();
    }

    /**
     * change BottomNavigationViewEx style
     */
    private void initView() {

        mBnve = (BottomNavigationViewEx)findViewById(R.id.bnve);
        /**
         * TODO 坑，应该是要enableShiftingMode false，但为啥示例WithViewPagerActivity 设置enableItemShiftingMode true
         * 效果跟enableShiftingMode false是一样的呢？ 是因为3个的原因吗，变成4个就效果不一样了？
         */
//        mBnve.enableItemShiftingMode(true);
        mBnve.enableShiftingMode(false);
        mBnve.enableAnimation(false);
        mVp = findViewById(R.id.vp);
        mChange = findViewById(R.id.change);

        mChange.setOnClickListener(this);
    }

    /**
     * create fragments
     */
    private void initData() {
        fragments = new ArrayList<>();
        items = new SparseIntArray();

        // create music fragment and add it
        BaseFragment musicFragment = new BaseFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.music));
        musicFragment.setArguments(bundle);

        // create backup fragment and add it
        BaseFragment backupFragment = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.backup));
        backupFragment.setArguments(bundle);

        // create friends fragment and add it
        BaseFragment friendsFragment = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.friends));
        friendsFragment.setArguments(bundle);

        // add to fragments for adapter
        fragments.add(musicFragment);
        fragments.add(backupFragment);
        fragments.add(friendsFragment);

        // add to items for change ViewPager item
        items.put(R.id.i_music, 0);
        items.put(R.id.i_backup, 1);
        items.put(R.id.i_friends, 2);

        // set adapter
        adapter = new VpAdapter(getSupportFragmentManager(), fragments);
        mVp.setAdapter(adapter);

    }

    /**
     * set listeners
     */
    private void initEvent() {
        // set listener to change the current item of view pager when click bottom nav item
        mBnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                // you can write as above.
                // I recommend this method. You can change the item order or counts without update code here.
                int position = items.get(item.getItemId());
                if (previousPosition != position) {
                    // only set item when item changed
                    previousPosition = position;
                    Log.i(TAG, "-----bnve-------- previous item:" + mBnve.getCurrentItem() + " current item:" + position + " ------------------");
                    mVp.setCurrentItem(position);
                }
                return true;
            }
        });

        // set listener to change the current checked item of bottom nav when scroll view pager
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "-----ViewPager-------- previous item:" + mBnve.getCurrentItem() + " current item:" + position + " ------------------");
                mBnve.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change:
                changeData();
                break;
        }
    }

    /**
     * 改边数据
     */
    private void changeData() {
        fragments.clear();
        items.clear();
        adapter = null;

        // create music fragment and add it
        BaseFragment musicFragment = new BaseFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.music));
        musicFragment.setArguments(bundle);

        // create backup fragment and add it
        BaseFragment backupFragment = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.backup));
        backupFragment.setArguments(bundle);

        // create friends fragment and add it
        BaseFragment friendsFragment = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.friends));
        friendsFragment.setArguments(bundle);

        BaseFragment fragment4 = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", "我的");
        fragment4.setArguments(bundle);

        // add to fragments for adapter
        fragments.add(musicFragment);
        fragments.add(backupFragment);
        fragments.add(friendsFragment);
        fragments.add(fragment4);

        // add to items for change ViewPager item TODO key要和下面add增加的itemId一致，而value才是最终要的值
        items.put(0, 0);
        items.put(1, 1);
        items.put(2, 2);
        items.put(3, 3);

        // set adapter
        adapter = new VpAdapter(getSupportFragmentManager(), fragments);
        mVp.setAdapter(adapter);

        mBnve.getMenu().clear();
//        mBnve.enableItemShiftingMode(true);
//        mBnve.enableAnimation(false);
        //getDrawable(R.drawable.tab_home_normal)) 需要api 21
        //getResources().getDrawable(R.drawable.tab_home_normal)
        mBnve.getMenu().add(0,0,0,"首页").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        mBnve.getMenu().getItem(0).
                setIcon(createDrawableSelector(getResources().getDrawable(R.drawable.tab_home_normal),
                        getResources().getDrawable(R.drawable.tab_home_selected)));
        mBnve.getMenu().add(0,1,1,"办事").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        mBnve.getMenu().getItem(1).
                setIcon(createDrawableSelector(getResources().getDrawable(R.drawable.tab_office_normal),
                        getResources().getDrawable(R.drawable.tab_office_selected)));
        mBnve.getMenu().add(0,2,2,"资讯").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        mBnve.getMenu().getItem(2).
                setIcon(createDrawableSelector(getResources().getDrawable(R.drawable.tab_news_normal),
                        getResources().getDrawable(R.drawable.tab_news_selected)));
        mBnve.getMenu().add(0,3,3,"我的").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        mBnve.getMenu().getItem(3).
                setIcon(createDrawableSelector(getResources().getDrawable(R.drawable.tab_mine_normal),
                        getResources().getDrawable(R.drawable.tab_mine_selected)));

    }

    // 构建Drawable选择器  TODO 不是getMenu().add所要的
    private StateListDrawable createDrawableSelector(Drawable checked, Drawable unchecked) {
        StateListDrawable stateList = new StateListDrawable();
        int state_selected = android.R.attr.state_selected;
        stateList.addState(new int[]{state_selected}, checked);
        stateList.addState(new int[]{-state_selected}, unchecked);
        return stateList;
    }

    // 构建颜色选择器
    private ColorStateList createColorSelector(int checkedColor, int uncheckedColor) {

        return new ColorStateList(
                new int[][]{new int[]{android.R.attr.state_selected},
                        new int[]{-android.R.attr.state_selected}},
                new int[]{checkedColor, uncheckedColor});
    }

    // 将文件转换成Drawable
    // pathName就是图片存放的绝对路径
    private Drawable getDrawableByFile(String pathName) {
        return Drawable.createFromPath(pathName);
    }

    /**
     * view pager adapter
     */
    private static class VpAdapter extends FragmentPagerAdapter {
//    private static class VpAdapter extends FragmentStatePagerAdaper {
        private List<Fragment> data;

        public VpAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }

//        @Override
//        public long getItemId(int position) {
//            return data.get(position).hashCode();
//        }

        /**
         * 重写getItemPosition()方法，通知刷新位置变化了
         * @param object
         * @return
         */
        @Override
        public int getItemPosition(Object object) {
            // TODO Auto-generated method stub
            return PagerAdapter.POSITION_NONE;
        }
    }
}
