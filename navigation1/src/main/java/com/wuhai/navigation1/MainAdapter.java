package com.wuhai.navigation1;

import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by flyeek on 3/2/16.
 */
public class MainAdapter extends FragmentPagerAdapter implements NavigationTabLayout.OnTabSelectedListener {

    public static final int PAGE_COUNT = 4;

    private List<MainBaseFragment> mFragments;
    private String[] mTitleArray;
    private int[][] mIconResStateArray;

    public MainAdapter(FragmentManager fragmentManager, String[] titles, int[] iconResNormals,
                       int[] iconResSelecteds) {
        super(fragmentManager);

        // Init fragments.
        mFragments = new ArrayList<>(PAGE_COUNT);
        for (int i = 0; i < PAGE_COUNT; i++) {
            mFragments.add(null);
        }

        // Init tabs.
        mTitleArray = titles;
        mIconResStateArray = new int[PAGE_COUNT][2];
        for (int i = 0; i < PAGE_COUNT; i++) {
            mIconResStateArray[i][0] = iconResNormals[i];
            mIconResStateArray[i][1] = iconResSelecteds[i];
        }
    }

    public void setResource(String[] titles, int[] iconResNormals, int[] iconResSelecteds){
        mTitleArray = titles;
        mIconResStateArray = new int[PAGE_COUNT][2];
        for (int i = 0; i < PAGE_COUNT; i++) {
            mIconResStateArray[i][0] = iconResNormals[i];
            mIconResStateArray[i][1] = iconResSelecteds[i];
        }
    }

    public MainBaseFragment getFragment(int position) {
        return mFragments.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        mFragments.set(position, (MainBaseFragment) fragment);
        return fragment;
    }

    @Override
    public MainBaseFragment getItem(int position) {
        MainBaseFragment fragment = null;
        switch (position) {
            case 0:
                fragment = WebviewFragment.newInstance("https://www.baidu.com/", "#ff00ff");
                break;
            case 1:
                fragment = WebviewFragment.newInstance("bbbbbbbbbbbbbbbb", "#ffff00");
                break;
            case 2:
                fragment = WebviewFragment.newInstance("cccccccccccccccccc", "#00ffff");
                break;
            case 3:
                fragment = WebviewFragment.newInstance("dddddddddddddddddddd", "#ffffff");
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public int[] onIconSelected(int position) {
        return mIconResStateArray[position];
    }

    @Override
    public String onTextSelected(int position) {
        return mTitleArray[position];
    }
}
