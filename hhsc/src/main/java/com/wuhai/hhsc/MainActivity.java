package com.wuhai.hhsc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.wuhai.hhsc.model.HomeBanner;
import com.wuhai.hhsc.utils.ActivityUtils;
import com.wuhai.hhsc.utils.Utils;
import com.wuhai.hhsc.widget.banner.HomeBannerFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private FrameLayout bannerFrameLayout;
    private HomeBannerFragment homeBannerFragment;

    private List<HomeBanner> banners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initData();
    }

    private void initData() {
        banners = new ArrayList<>();
        HomeBanner homeBanner = new HomeBanner();
        homeBanner.setImgUri("https://10.114.10.31:9999/group1/M00/00/0B/CnIKIGAc7BCAQEo3AADQ_hUDzNY679.jpg");
        homeBanner.setRedirect("https://www.baidu.com/index.php?");
        HomeBanner homeBanner1 = new HomeBanner();
        homeBanner1.setImgUri("https://10.114.10.31:9999/group1/M00/00/0B/CnIKIGAc5i6AcTLbAAFBkbsxZT4407.png");
        homeBanner1.setRedirect("https://www.baidu.com/index.php?");
        HomeBanner homeBanner2 = new HomeBanner();
        homeBanner2.setImgUri("https://10.114.10.31:9999/group1/M00/00/0B/CnIKIGAcqWCAT7m7AACo1U14cbM101.jpg");
        homeBanner2.setRedirect("https://www.baidu.com/index.php?");
        banners.add(homeBanner);
        banners.add(homeBanner1);
        banners.add(homeBanner2);
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
    }

    private void init() {
        bannerFrameLayout = findViewById(R.id.banner_fragment);
        //显示轮播
        homeBannerFragment = (HomeBannerFragment) getSupportFragmentManager().findFragmentById(R.id.banner_fragment);
        if (homeBannerFragment == null) {
            homeBannerFragment = new HomeBannerFragment();
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), homeBannerFragment, R.id.banner_fragment);
        bannerFrameLayout.setMinimumHeight(getStateBarHeight(this) + Utils.dp2px(45));

    }

    @Override
    protected void onResume() {
        super.onResume();

        homeBannerFragment.setHeaderViewData(banners);
        bannerFrameLayout.setVisibility(View.VISIBLE);
    }

    public static int getStateBarHeight(Activity a) {
        int result = 0;
        int resourceId = a.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = a.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
