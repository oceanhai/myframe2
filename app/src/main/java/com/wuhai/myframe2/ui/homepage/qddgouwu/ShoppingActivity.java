package com.wuhai.myframe2.ui.homepage.qddgouwu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity;
import com.wuhai.myframe2.utils.CommonUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者 wuhai
 * <p>
 * 创建日期 2019/4/3 11:45
 * <p>
 * 描述：使用特殊的(自定义)布局管理器
 * 实际应用案例
 *  钱到分期 购物页
 */
public class ShoppingActivity extends BaseActivity {

    @BindView(R.id.scroll_to_top)
    ImageView mScrollToTop;
    @BindView(R.id.pull_to_refresh_layout)
    ScrollableRefreshView mHomeLoadLayout;
    @BindView(R.id.refreshRecycleView)
    ScrollableRecycleView mHomeRecycleView;

    @BindView(R.id.credit_tip_container)
    FrameLayout mCreditContainer;
    @BindView(R.id.cancel)
    TextView mCreditCancel;
    @BindView(R.id.ok)
    TextView mCreditOk;


    private HomeAdapterV3 mAdapter3;

    private Unbinder mUnbinder;
    private int newPage = 1;
    private boolean hasMore = true;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ShoppingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_shopping);
        ButterKnife.bind(this);
        parseIntent();
        init();
        setListener();
        initData();
    }

    private void initData() {
        String dataStr = CommonUtils.getFromAssets("qiandaodao/getHomeInfoV2", this);
        JsonObject object = new JsonParser().parse(dataStr).getAsJsonObject();

        HomeInfoV2 homeInfo = new HomeInfoV2();
        homeInfo.set(object.getAsJsonObject("homeInfo"));

        if (mAdapter3 == null) {
            return;
        }
        newPage = 1;
        hasMore = true;
        mAdapter3.setData(homeInfo);
        //上拉加载完成 TODO？
//        mHomeLoadLayout.loadMoreComplete(true);
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if (intent != null) {

        }
    }

    @Override
    public void setStatistical() {

    }

    private void init() {
        mAdapter3 = new HomeAdapterV3(this);

//        mCreditContainer.setVisibility(UserService.getInstance().isLogin && UserService.getInstance().mUserInfo.limit.limitLevel == 1 ? View.GONE : View.VISIBLE);
        //看看再说
        mCreditCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCreditContainer.setVisibility(View.GONE);
            }
        });
        //激活额度
        mCreditOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!UserService.getInstance().isLogin){//未登录
//                    startActivity(IntentFactory.createRegisterIntent(getContext()));
//                }else if(UserService.getInstance().mUserInfo.limit.limitLevel == 0){//激活额度
//                    startActivity(new Intent(getContext(), CreditActivityV5.class));
//                }else{//以外
//                    mCreditContainer.setVisibility(View.GONE);
//                }
            }
        });

        GridLayoutManager layoutManager = new GridLayoutManager(this, 12,
                GridLayoutManager.VERTICAL,false){
            @Override
            public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
                super.smoothScrollToPosition(recyclerView, state, position);
            }
        };
        mAdapter3 = new HomeAdapterV3(getContext());
        layoutManager.setSpanSizeLookup(mAdapter3.getSpanSizeLookup());
        mHomeRecycleView.setLayoutManager(layoutManager);
        mHomeRecycleView.addItemDecoration(mAdapter3.getHomeItemDecoration());

        RecyclerAdapterWithHF recyclerAdapterWithHF = new RecyclerAdapterWithHF((RecyclerView.Adapter) mAdapter3);
        mHomeRecycleView.setAdapter(recyclerAdapterWithHF);
        mHomeLoadLayout.setFooterView(new LoadMoreViewFooter(true));
        mHomeLoadLayout.setPtrHandler(new com.chanven.lib.cptr.PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(com.chanven.lib.cptr.PtrFrameLayout frame, View content, View header) {
                return false;
            }

            @Override
            public void onRefreshBegin(com.chanven.lib.cptr.PtrFrameLayout frame) {
            }
        });
        mHomeLoadLayout.setLoadMoreEnable(true);
        mHomeLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                //上拉加载
//                APIServiceHome.getNewItem(++newPage, new APICallBack<ProInfoPager>() {
//                    @Override
//                    public void success(final ProInfoPager result) {
//                        hasMore = result.pager.hasNextPage;
//                        if(hasMore){
//                            mHomeLoadLayout.loadMoreComplete(true);
//                        }else {
//                            mHomeLoadLayout.loadMoreComplete(false);
//                        }
//                        newPage = result.pager.page;
//                        addNewData(result.list);
//                    }
//
//                    @Override
//                    public void error(String errorMsg) {
//                        newPage--;
//                        mHomeLoadLayout.loadMoreComplete(true);
//                    }
//
//                    @Override
//                    public void networkFailure(String networkFailureMsg) {
//                        newPage--;
//                        mHomeLoadLayout.loadMoreComplete(true);
//                    }
//                });
            }
        });


        mAdapter3.setHomeLinkImageListener(new HomeAdapterV3.HomeActionListener() {
            @Override
            public void onLinkImageClick(String url, String id,String title, String type) {
                //TODO 点击跳转逻辑
                Toast.makeText(ShoppingActivity.this,type,Toast.LENGTH_LONG).show();
//                if (QiandaodaoUri.isQiandaodaoUri(url)) {
//                    if (getActivity() != null) {
//                        ((MainActivity) getActivity()).configQiandaodaoUri(url,type);
//                    }
//                    return;
//                }
//                if (url.startsWith("http")) {
//                    Intent intent = new Intent(mContext, CategoryWebActivity.class);
//                    intent.putExtra(CategoryWebActivity.EXTRA_URL, url);
//                    intent.putExtra(CategoryWebActivity.EXTRA_SOURCE, type);
//                    mContext.startActivity(intent);
//                } else if (url.startsWith("app")) {
//                    String[] params = url.split("[.]");
//                    final int categoryId = Integer.valueOf(params[params.length - 1]);
//                    ((CategoryFragment) ((MainActivity) getActivity()).getFragment(2))
//                            .setCategoryId(categoryId, type);
//                    ((MainActivity) getActivity()).setSelectState(2, false);
//                }
            }
        });

        mHomeRecycleView.setOnRecycleViewScrollListener(new ScrollableRecycleView.OnRecycleViewScrollListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                View child = mHomeRecycleView.getLayoutManager().getChildAt(0);
                mScrollToTop.setVisibility(mHomeRecycleView.getLayoutManager().getItemViewType(child) == HomeInfoV2.TYPE_NEWITEM ? View.VISIBLE : View.GONE);
            }
        });

        mScrollToTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHomeRecycleView.scrollToPosition(0);
            }
        });
    }

    private void setListener() {

    }

    private void addNewData(ArrayList<SearchProInfo> list) {
        if(list.size()>0 && mAdapter3 != null){
            mAdapter3.addNew(list);
        }
    }


}
