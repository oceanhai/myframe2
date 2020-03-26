package com.wuhai.myframe2.ui.materialdesign.yxt;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wuhai.myframe2.ui.navigation.navigationmenu1.Fragment2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 整形分类adapter
 */
public class PlasticCategoryAdapter extends FragmentPagerAdapter {

    private List<PlasticCategoryModel> mDatas;
    private List<Fragment2> mGoods;
//    private PlasticCategoryDataResult mDefaultCategoryDefault;

    public PlasticCategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setDatas(List<PlasticCategoryModel> list) {
        mDatas = list;
        if (mDatas != null) {
            mGoods = new ArrayList<Fragment2>();
            Iterator<PlasticCategoryModel> it = mDatas.iterator();
            while (it.hasNext()) {
                PlasticCategoryModel model = it.next();//TODO 这个当初想着在fragment里请求数据，目前估计是一次性取得数据
                mGoods.add(new Fragment2());
            }
        }
        notifyDataSetChanged();
    }
//
//    public void setDefaultCategoryDefault(PlasticCategoryDataResult data) {
//        mDefaultCategoryDefault = data;
//    }

    @Override
    public Fragment getItem(int position) {
        return mGoods.get(position);
    }

    @Override
    public int getCount() {
        return (mDatas != null) ? mDatas.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDatas.get(position).getName();
    }
}
