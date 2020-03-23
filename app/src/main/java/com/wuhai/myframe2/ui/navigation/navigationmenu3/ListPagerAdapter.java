package com.wuhai.myframe2.ui.navigation.navigationmenu3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wuhai.myframe2.ui.navigation.navigationmenu1.Fragment1;
import com.wuhai.myframe2.ui.navigation.navigationmenu1.Fragment2;
import com.wuhai.myframe2.ui.navigation.navigationmenu1.Fragment3;

import java.util.List;

/**
 * List主页适配器
 * Created by dueeeke on 2018/1/3.
 */
public class ListPagerAdapter extends FragmentPagerAdapter {

    private List<String> mTitles;
    private SparseArrayCompat<Fragment> mFragments = new SparseArrayCompat<>();

    public ListPagerAdapter(FragmentManager fm, List<String> titles) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mTitles = titles;
    }

    @Override
    @NonNull
    public Fragment getItem(int position) {
        Fragment fragment = mFragments.get(position);
        if (fragment == null) {
            switch (position) {
                default:
                case 0:
                    fragment = new Fragment1();
                    break;
                case 1:
                    fragment = new Fragment2();
                    break;
                case 2:
                    fragment = new Fragment3();
                    break;
                case 3:
                    fragment = new Fragment1();
                    break;
                case 4:
                    fragment = new Fragment2();
                    break;
                case 5:
                    fragment = new Fragment3();
                    break;
            }
            mFragments.put(position, fragment);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
