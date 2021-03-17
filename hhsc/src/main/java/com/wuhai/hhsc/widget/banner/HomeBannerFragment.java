package com.wuhai.hhsc.widget.banner;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.wuhai.hhsc.R;
import com.wuhai.hhsc.app.App;
import com.wuhai.hhsc.model.HomeBanner;
import com.wuhai.hhsc.ui.fragment.BaseFragment;
import com.wuhai.hhsc.utils.PlatformInfoUtils;
import com.wuhai.hhsc.utils.Utils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;



/**
 * description:首页广告轮播
 * data: 2019-12-18
 * author: zhudi
 */
public class HomeBannerFragment extends BaseFragment {
    private View rootView;
    private Activity mActivity;
    public CustomViewPager viewPager;
    private int previousPosition = 0;
    private AdvertisementHandler handler = new AdvertisementHandler(new WeakReference<BaseFragment>(this));

    public AdvertisementHandler getHandler() {
        return handler;
    }

    private RelativeLayout advertisementLayout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.home_ad_header_layout, container, false);
        }
        //因为共用一个Fragment视图，所以当前这个视图已被加载到Activity中，必须先清除后再加入Activity
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }

    @Override
    protected void onVisible(boolean isInit) {
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initView() {
        advertisementLayout = rootView.findViewById(R.id.rl_home_header);
    }

    public void setHeaderViewData(List<HomeBanner> list) {
        try {
            List<HomeBanner> arrayList = new ArrayList<>(1);
            arrayList.clear();
            final LinearLayout llPointGroup;
            if (list != null && list.size() > 0) {
                arrayList.addAll(list);
            } else {
                return;
            }
            List<View> viewList = new ArrayList<>(1);

            viewPager = rootView.findViewById(R.id.vp_home_header);
            llPointGroup = rootView.findViewById(R.id.ll_point_group);
            llPointGroup.removeAllViews();
            View advertisementView;
            String imgUrl = "";
            for (int i = 0; i < list.size(); i++) {
                advertisementView = LayoutInflater.from(App.getInstance()).inflate(R.layout.advertisement_item_layout, null);
                ImageView advertisementImage = advertisementView.findViewById(R.id.iv_advertisement);
                final HomeBanner homeBanner = arrayList.get(i);
                imgUrl = homeBanner.getImgUri();
                Utils.imageViewDisplayByUrl(getContext(),imgUrl, advertisementImage);
                viewList.add(advertisementView);
                advertisementView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        int type = homeBanner.getType();
                        String gotoUrl = homeBanner.getRedirect();
//                        ActivityUtils.startWebActivity(getActivity(), gotoUrl);
                        Utils.showToastLongTime(gotoUrl);

                    }
                });
                if (list.size() == 1) {
                    break;
                }
                //每循环一次需要向LinearLayout中添加一个点的view对象
                View v = new View(App.getInstance());
                v.setBackgroundResource(R.drawable.selector_home_ad_point);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Utils.dp2px(7), Utils.dp2px(7));
                params.leftMargin = 8;
                params.rightMargin = 8;
                v.setLayoutParams(params);
                v.setEnabled(false);
                llPointGroup.addView(v);
            }

            HomeAdvertisementTabAdapter homeAdvertisementTabAdapter = new HomeAdvertisementTabAdapter(viewList);
            viewPager.setAdapter(homeAdvertisementTabAdapter);
            viewPager.setIsCanScroll(false);
            viewPager.setCurrentItem(0);
            if (list.size() > 1) {
                llPointGroup.getChildAt(previousPosition).setEnabled(true);
                //开始轮播效果
                handler.sendEmptyMessageDelayed(AdvertisementHandler.MSG_UPDATE_IMAGE, AdvertisementHandler.MSG_DELAY);
            }

            final List<HomeBanner> finalArrayList = arrayList;
            viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int arg1) {
                    int position = arg1 % finalArrayList.size();
                    // 把当前选中的点给切换了, 还有描述信息也切换
                    llPointGroup.getChildAt(previousPosition).setEnabled(false);
                    llPointGroup.getChildAt(position).setEnabled(true);
                    // 把当前的索引赋值给前一个索引变量, 方便下一次再切换.
                    previousPosition = position;
                    handler.sendMessage(Message.obtain(handler, AdvertisementHandler.MSG_PAGE_CHANGED, arg1, 0));
                }

                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {
                }

                //覆写该方法实现轮播效果的暂停和恢复
                @Override
                public void onPageScrollStateChanged(int arg0) {
                    // 当页面的状态改变时将调用此方法
                    //arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。
                    Log.d("onPageScrollStateChange", arg0 + "");
                    switch (arg0) {
                        case ViewPager.SCROLL_STATE_DRAGGING:
                            // 正在拖动页面时执行此处
                            handler.sendEmptyMessage(AdvertisementHandler.MSG_KEEP_SILENT);
                            break;
                        case ViewPager.SCROLL_STATE_IDLE:
                            // 未拖动页面时执行此处
                            handler.sendEmptyMessageDelayed(AdvertisementHandler.MSG_UPDATE_IMAGE, AdvertisementHandler.MSG_DELAY);
                            break;
                        default:
                            break;
                    }
                }

            });


            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) advertisementLayout.getLayoutParams();
            params.height = PlatformInfoUtils.getWidthOrHeight(mActivity)[0] * 48 / 75;
            advertisementLayout.setLayoutParams(params);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setHeight(int height) {
        ViewGroup.LayoutParams layoutParams = rootView.getLayoutParams();
        layoutParams.height = height;
        rootView.setLayoutParams(layoutParams);
    }


    @Override
    public void onDestroy() {
        if (handler != null) {
            handler.clearData();
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
        if (viewPager != null) {
            viewPager.removeAllViews();
            viewPager = null;
        }
        super.onDestroy();
    }


}
