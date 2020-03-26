package com.wuhai.myframe2.ui.materialdesign.yxt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wuhai.myframe2.R;
import com.wuhai.myframe2.utils.CommonUtils;
import com.wuhai.myframe2.utils.GsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 品牌页
 * 材料设计
 * @author wuhai
 * @time 2016/11/16 14:15
 */
public class RetailSalerBrandPageActivity extends FragmentActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private List<PlasticCategoryModel> categoryModelList;
    private PlasticCategoryAdapter plasticCategoryAdapter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RetailSalerBrandPageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_material_retail_saler_brand_page);
        ButterKnife.bind(this);
        init();
        initData();
    }

    private void init() {
        plasticCategoryAdapter = new PlasticCategoryAdapter(this.getSupportFragmentManager());
        viewPager.setAdapter(plasticCategoryAdapter);
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabsFromPagerAdapter(plasticCategoryAdapter);
    }

    private void initData() {
        BrandData data = null;
        String json = CommonUtils.getFromAssets("homepagejson1.json", this);
        data = GsonUtils.getInstance().fromJson(json, BrandData.class);

        setData(data);
    }

    private void setData(BrandData data) {
        categoryModelList = data.getData().getCategoryList();

        initCategory();
    }

    private void initCategory() {
        plasticCategoryAdapter.setDatas(categoryModelList);
    }
}
