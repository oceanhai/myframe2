package com.wuhai.hhsc;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wuhai.hhsc.model.GoodsBenefitEntity;
import com.wuhai.hhsc.model.GoodsListInfo;
import com.wuhai.hhsc.model.HomeActivityLiveVO;
import com.wuhai.hhsc.model.HomeArea;
import com.wuhai.hhsc.model.HomeAreaBg;
import com.wuhai.hhsc.model.HomeBanner;
import com.wuhai.hhsc.ui.adpater.CallBackListener;
import com.wuhai.hhsc.ui.adpater.HomeBenefitGoodsAdapter;
import com.wuhai.hhsc.ui.adpater.HomeGoodsAdapter;
import com.wuhai.hhsc.ui.adpater.HomeItemAdapter;
import com.wuhai.hhsc.ui.adpater.ItemListener;
import com.wuhai.hhsc.ui.adpater.LiveRoomAdapter;
import com.wuhai.hhsc.utils.ActivityUtils;
import com.wuhai.hhsc.utils.Utils;
import com.wuhai.hhsc.widget.GridLayoutSpacesItemDecoration;
import com.wuhai.hhsc.widget.banner.HomeBannerFragment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private FrameLayout bannerFrameLayout;
    private HomeBannerFragment homeBannerFragment;

    //分区
    private ImageView itemBgView;
    private RecyclerView itemRvView;
    private HomeItemAdapter homeItemAdapter;
    private RecyclerView liveRvView;
    private LiveRoomAdapter liveRoomAdapter;
    private RoundedImageView goodsBenefitRvTopView, goodsBenefitWarmUpRvTopView;
    private RecyclerView goodsBenefitRvView, goodsBenefitWarmUpRvView;
    private HomeBenefitGoodsAdapter homeBenefitGoodsAdapter, homeBenefitGoodsWarmUpAdapter;
    private HomeGoodsAdapter homeGoodsAdapter;

    private SmartRefreshLayout refreshLayout;

    //轮播图
    private List<HomeBanner> banners;
    //
    private HomeAreaBg homeAreaBg;
    //
    private List<HomeArea> homeAreas;
    //直播
    private List<HomeActivityLiveVO> activityLiveList;
    //惠筹
    private List<GoodsBenefitEntity> warmUpList;
    //推荐商品
    private List<GoodsListInfo> goodsListInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initData();
    }

    private void initData() {
        //轮播图
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

        //
//        homeAreaBg = new HomeAreaBg();
//        homeAreaBg.setImgUri("https://10.114.10.31:9999/group1/M00/00/0C/CnIKIGBRdGeAOMdZAABloaQyjqg061.jpg");

        //
        homeAreas = new ArrayList<>();
        for (int x=0;x<8;x++){
            HomeArea homeArea = new HomeArea();
            homeArea.setImgName("附近");
            homeArea.setImgUri("https://10.114.10.31:9999/group1/M00/00/0C/CnIKIGBLIRiADPkTAAAET3i0sRI378.png");
            homeArea.setRedirect("https://www.baidu.com/index.php?");
            homeAreas.add(homeArea);
        }

        //直播
        activityLiveList = new ArrayList<>();
        for (int x=0;x<2;x++){
            HomeActivityLiveVO homeActivityLiveVO = new HomeActivityLiveVO();
            homeActivityLiveVO.setDiscountPrice("100.00");
            homeActivityLiveVO.setFrontCover("https://10.114.10.31:9999/group1/M00/00/0B/CnIKIGAcqWCAT7m7AACo1U14cbM101.jpg");
            homeActivityLiveVO.setGoodsName("卖破烂了");
            homeActivityLiveVO.setGoodsNum(100);
            if(x==0){
                homeActivityLiveVO.setLiving(true);
            }else{
                homeActivityLiveVO.setLiving(false);
            }
            homeActivityLiveVO.setMainImgUrl("https://10.114.10.31:9999/group1/M00/00/0C/CnIKIGBLIRiADPkTAAAET3i0sRI378.png");
            homeActivityLiveVO.setOnlineNum(99);
            homeActivityLiveVO.setTitle("我是标题");

            activityLiveList.add(homeActivityLiveVO);
        }

        //惠筹
        warmUpList = new ArrayList<>();
        for (int x=0;x<6;x++){
            GoodsBenefitEntity entity = new GoodsBenefitEntity();
            entity.setDiscountPrice(new BigDecimal("123"));
            entity.setGiveIntegral(new BigDecimal("10"));
            entity.setGoodsName("商品");
            entity.setMainImgUrl("https://10.114.10.31:9999/group1/M00/00/0C/CnIKIGBLIRiADPkTAAAET3i0sRI378.png");

            warmUpList.add(entity);
        }

        //推荐商品
        goodsListInfos = new ArrayList<>();
        for (int x=0;x<10;x++){
            GoodsListInfo good = new GoodsListInfo();
            good.setGoodsName("苹果100");
            good.setDiscountPrice(new BigDecimal("100"));
            good.setGiveIntegral(new BigDecimal("1"));
            good.setMainImgUrl("https://10.114.10.31:9999/group1/M00/00/0C/CnIKIGBLIRiADPkTAAAET3i0sRI378.png");

            goodsListInfos.add(good);
        }
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

        //分区
        itemBgView = findViewById(R.id.iv_item_bg);
        itemRvView = findViewById(R.id.rv_home_item);
        homeItemAdapter = new HomeItemAdapter(this);
        homeItemAdapter.setOnItemListener(new ItemListener() {
            @Override
            public void onItemClick(View view, int position) {
                HomeArea homeArea = homeItemAdapter.getList().get(position);
                String gotoUrl = homeArea.getRedirect();
            }
        });
        itemRvView.setAdapter(homeItemAdapter);
        GridLayoutSpacesItemDecoration gridLayoutSpacesItemDecoration = new GridLayoutSpacesItemDecoration(Utils.dp2px(16), 4, true);
        itemRvView.addItemDecoration(gridLayoutSpacesItemDecoration);
        //直播
        liveRvView = findViewById(R.id.rv_live_room);
        liveRoomAdapter = new LiveRoomAdapter(this);
        liveRoomAdapter.setOnItemListener(new ItemListener() {
            @Override
            public void onItemClick(View view, int position) {
//                if (Utils.isLoginStatus()) {
//                    liveClickPosition = position;
//                    HomeActivityLiveVO homeActivityLiveVO = liveRoomAdapter.getItem(position);
//                    if (homeActivityLiveVO != null) {
//                        getLivePresenter().comeIn(homeActivityLiveVO.getLiveId());
//                    }
//                } else {
//                    ActivityUtils.startWebActivity(getActivity(), null);
//                }
            }
        });
        liveRvView.setAdapter(liveRoomAdapter);
        //惠筹
        goodsBenefitRvTopView = findViewById(R.id.iv_top_goods_benefit);
        goodsBenefitRvView = findViewById(R.id.rv_goods_benefit);
        homeBenefitGoodsAdapter = new HomeBenefitGoodsAdapter(this);
        homeBenefitGoodsAdapter.setOnItemListener(new ItemListener() {
            @Override
            public void onItemClick(View view, int position) {
                GoodsBenefitEntity goodsBenefitEntity = homeBenefitGoodsAdapter.getItem(position);
                if (goodsBenefitEntity != null) {
//                    ActivityUtils.startWebActivity(getActivity(), InterfaceUrl.getPageUrlBenefitGoodsDetail(goodsBenefitEntity.getSpuId(), 2) + "&home=1");
                }
            }
        });
        goodsBenefitRvView.setAdapter(homeBenefitGoodsAdapter);
        gridLayoutSpacesItemDecoration = new GridLayoutSpacesItemDecoration(Utils.dp2px(5), 3, false);
        goodsBenefitRvView.addItemDecoration(gridLayoutSpacesItemDecoration);
        //惠筹预热
        goodsBenefitWarmUpRvTopView = findViewById(R.id.iv_top_goods_benefit_warmup);
        goodsBenefitWarmUpRvView = findViewById(R.id.rv_goods_benefit_warmup);
        homeBenefitGoodsWarmUpAdapter = new HomeBenefitGoodsAdapter(this);
        goodsBenefitWarmUpRvView.setAdapter(homeBenefitGoodsWarmUpAdapter);
        //推荐商品
        RecyclerView goodsView = findViewById(R.id.rv_goods);
        homeGoodsAdapter = new HomeGoodsAdapter(this, new CallBackListener.OnClickListener() {
            @Override
            public void onClick(int position) {
                //找相似
                GoodsListInfo goodsListInfo = homeGoodsAdapter.getItem(position);
                if (goodsListInfo != null) {
//                    ActivityUtils.startWebActivity(getActivity(), InterfaceUrl.getPageUrlGoodsFindSame(goodsListInfo.getCategoryOne()) + "&home=1");
                }
            }
        });
        homeGoodsAdapter.setOnItemListener(new ItemListener() {
            @Override
            public void onItemClick(View view, int position) {
                GoodsListInfo goodsListInfo = homeGoodsAdapter.getItem(position);
                if (goodsListInfo != null) {
//                    String gotoUrl = null;
//                    //惠筹
//                    if (goodsListInfo.getGoodsType() == 2) {
//                        gotoUrl = InterfaceUrl.getPageUrlBenefitGoodsDetail(goodsListInfo.getSpuId(), 2);
//                    } else if (goodsListInfo.getGoodsType() == 3) {//会员日
//                        gotoUrl = InterfaceUrl.getPageUrlVipdayGoodsDetail(goodsListInfo.getSpuId(), 3);
//                    } else {
//                        gotoUrl = InterfaceUrl.getPageUrlGoodsDetail(goodsListInfo.getSpuId());
//                    }
//                    ActivityUtils.startWebActivity(getActivity(), gotoUrl + "&home=1");
                }
            }
        });
        goodsView.setAdapter(homeGoodsAdapter);
        gridLayoutSpacesItemDecoration = new GridLayoutSpacesItemDecoration(Utils.dp2px(5), 2, false);
        goodsView.addItemDecoration(gridLayoutSpacesItemDecoration);

        //下拉刷新 上拉加载框架
        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableNestedScroll(false);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (refreshLayout != null) {
                            refreshLayout.finishLoadMore();
                        }
                        Toast.makeText(MainActivity.this,"加载更多结束",Toast.LENGTH_LONG).show();
                        List<GoodsListInfo> newList = new ArrayList<>();
                        for (int x=0;x<10;x++){
                            GoodsListInfo good = new GoodsListInfo();
                            good.setGoodsName("华为");
                            good.setDiscountPrice(new BigDecimal("100"));
                            good.setGiveIntegral(new BigDecimal("1"));
                            good.setMainImgUrl("https://10.114.10.31:9999/group1/M00/00/0C/CnIKIGBLIRiADPkTAAAET3i0sRI378.png");

                            newList.add(good);
                        }

                        homeGoodsAdapter.addList(newList);
                    }
                },4000);
            }

            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (refreshLayout != null) {
                            refreshLayout.finishRefresh();
                        }
                        Toast.makeText(MainActivity.this,"刷新结束",Toast.LENGTH_LONG).show();

                        homeGoodsAdapter.setList(goodsListInfos);
                    }
                },4000);


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        homeBannerFragment.setHeaderViewData(banners);
        bannerFrameLayout.setVisibility(View.VISIBLE);

        if (homeAreaBg != null) {
            Utils.imageViewDisplayByUrl(this, homeAreaBg.getImgUri(), itemBgView);
            itemBgView.setVisibility(View.VISIBLE);

            FrameLayout.LayoutParams bgLayoutParams = (FrameLayout.LayoutParams) itemBgView.getLayoutParams();
//            if (itemRvView.getHeight() != 0) {
//                bgLayoutParams.height = itemRvView.getHeight();
//            } else {
//                bgLayoutParams.height = Utils.dp2px(155);
//            }
            itemBgView.setLayoutParams(bgLayoutParams);
        } else {
            itemBgView.setVisibility(View.GONE);
        }

        homeItemAdapter.setList(homeAreas);
        itemRvView.setVisibility(View.VISIBLE);

        liveRoomAdapter.setList(activityLiveList);
        liveRvView.setVisibility(View.VISIBLE);

        homeBenefitGoodsAdapter.setList(warmUpList);
        goodsBenefitRvView.setVisibility(View.VISIBLE);

        homeGoodsAdapter.setList(goodsListInfos);
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
