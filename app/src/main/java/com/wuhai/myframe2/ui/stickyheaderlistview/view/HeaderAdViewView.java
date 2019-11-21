package com.wuhai.myframe2.ui.stickyheaderlistview.view;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.viewpager.widget.ViewPager;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.stickyheaderlistview.adapter.HeaderAdAdapter;
import com.wuhai.myframe2.ui.stickyheaderlistview.manager.ImageManager;
import com.wuhai.myframe2.ui.stickyheaderlistview.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
* 轮播图 view
* @author wuhai
* create at 2016/5/24 13:51
*/
public class HeaderAdViewView extends HeaderViewInterface<List<String>> {

    @BindView(R.id.vp_ad)
    ViewPager vpAd;
    @BindView(R.id.ll_index_container)
    LinearLayout llIndexContainer;

    private static final int TYPE_CHANGE_AD = 0;
    private Thread mThread;
    private List<ImageView> ivList;
    private boolean isStopThread = false;
    private ImageManager mImageManager;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == TYPE_CHANGE_AD) {
                vpAd.setCurrentItem(vpAd.getCurrentItem() + 1);
            }
        }
    };

    public HeaderAdViewView(Activity context) {
        super(context);
        ivList = new ArrayList<>();
        mImageManager = new ImageManager(context);
    }

    @Override
    protected void getView(List<String> list, ListView listView) {
        View view = mInflate.inflate(R.layout.header_ad_layout, listView, false);
        ButterKnife.bind(this, view);

        dealWithTheView(list);
        listView.addHeaderView(view);
    }

    /**
     * 根据传入list 处理轮播图
     * @param list
     */
    private void dealWithTheView(List<String> list) {
        ivList.clear();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ivList.add(createImageView(list.get(i)));
        }

        HeaderAdAdapter photoAdapter = new HeaderAdAdapter(mContext, ivList);
        vpAd.setAdapter(photoAdapter);

        //轮播图 底部点点
        addIndicatorImageViews(size);
        // 为ViewPager设置监听器
        setViewPagerChangeListener(size);
        // 启动循环广告的线程
        startADRotate();
    }

    // 创建要显示的ImageView
    private ImageView createImageView(String url) {
        ImageView imageView = new ImageView(mContext);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageManager.loadUrlImage(url, imageView);
        return imageView;
    }

    // 添加指示图标
    private void addIndicatorImageViews(int size) {
        llIndexContainer.removeAllViews();
        for (int i = 0; i < size; i++) {
            ImageView iv = new ImageView(mContext);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5));
            if (i != 0) {
                lp.leftMargin = DensityUtil.dip2px(mContext, 7);
            }
            iv.setLayoutParams(lp);
            iv.setBackgroundResource(R.drawable.xml_round_orange_grey_sel);
            iv.setEnabled(false);
            if (i == 0) {
                iv.setEnabled(true);
            }
            llIndexContainer.addView(iv);
        }
    }

    // 为ViewPager设置监听器
    private void setViewPagerChangeListener(final int size) {
        vpAd.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (ivList != null && ivList.size() > 0) {
                    int newPosition = position % size;
                    for (int i = 0; i < size; i++) {
                        llIndexContainer.getChildAt(i).setEnabled(false);
                        if (i == newPosition) {
                            llIndexContainer.getChildAt(i).setEnabled(true);
                        }
                    }
                }
            }

            @Override
            public void onPageScrolled(int position, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    // 启动循环广告的线程
    public void startADRotate() {
        isStopThread = false;

        // 一个广告的时候不用转
        if (ivList == null || ivList.size() <= 1) {
            return;
        }
//        if (mThread == null) {
            mThread = new Thread(new Runnable() {

                @Override
                public void run() {
                    // 当没离开该页面时一直转
                    while (!isStopThread) {
                        // 每隔5秒转一次
                        SystemClock.sleep(5000);
                        // 在主线程更新界面
                        mHandler.sendEmptyMessage(TYPE_CHANGE_AD);
                    }
                }
            });
            mThread.start();
//        }
    }

    // 停止循环广告的线程，清空消息队列
    public void stopADRotate() {
        isStopThread = true;
        if (mHandler != null && mHandler.hasMessages(TYPE_CHANGE_AD)) {
            mHandler.removeMessages(TYPE_CHANGE_AD);
        }
    }

    /**
     * 是否停止循环
     * @return
     */
    public boolean isStopThread(){
        return isStopThread;
    }
}
