package com.wuhai.myframe2.ui.homepage.qddgouwu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.homepage.qddgouwu.model.LinkImage;
import com.wuhai.myframe2.ui.homepage.qddgouwu.view.cyclebanner.BannerPagerAdapter;
import com.wuhai.myframe2.ui.homepage.qddgouwu.view.cyclebanner.CircleFlowIndicator;
import com.wuhai.myframe2.ui.homepage.qddgouwu.view.cyclebanner.SimpleLineIndicator;
import com.wuhai.myframe2.ui.homepage.qddgouwu.view.cyclebanner.ViewFlow;

import java.util.ArrayList;

/**
 * createby yangzheng
 * date 2017/1/3
 * email yangzhenop@126.com
 */
public class HomeBanner extends FrameLayout {
    private static final long FLOW_TIME_SPAN = 4500L;
    private Context mContext;
    private ViewFlow bannerViewflow;
    private CircleFlowIndicator bannerViewflowIndicator;
    private SimpleLineIndicator mIndicator;

    public HomeBanner(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public HomeBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        inflate(mContext, R.layout.home_adapter_item_banner, this);
        bannerViewflow = (ViewFlow) findViewById(R.id.home_adapter_item_banner_viewflow);
        bannerViewflowIndicator = (CircleFlowIndicator) findViewById(R.id.home_adapter_item_banner_viewflow_indicator);
        mIndicator = (SimpleLineIndicator) findViewById(R.id.indicator);

        bannerViewflow.setOnViewSwitchListener(new ViewFlow.ViewSwitchListener() {
            @Override
            public void onSwitched(View view, int position) {
                mIndicator.setPosition(position);
            }
        });
    }
    
    public void set(ArrayList<LinkImage> bannerModels){
        final int bannerCount = bannerModels.size();
        ArrayList<String> imageUrlList = new ArrayList<>();
        ArrayList<String> linkUrlList = new ArrayList<>();
        ArrayList<String> titleList = new ArrayList<>();
        for (int i = 0; i < bannerCount; i++) {
            imageUrlList.add(bannerModels.get(i).image);
            linkUrlList.add(bannerModels.get(i).url);
            titleList.add(bannerModels.get(i).title);
        }
        bannerViewflow.setAdapter(new BannerPagerAdapter(mContext,
                imageUrlList, linkUrlList, titleList).setmIsInfiniteLoop(true));
        bannerViewflow.setmSideBuffer(imageUrlList.size());
        bannerViewflow.setFlowIndicator(bannerViewflowIndicator);
        bannerViewflow.setTimeSpan(FLOW_TIME_SPAN);
        bannerViewflow.setSelection(0);
        bannerViewflow.startAutoFlowTimer();
        mIndicator.setNumber(bannerCount);
    }
    
}