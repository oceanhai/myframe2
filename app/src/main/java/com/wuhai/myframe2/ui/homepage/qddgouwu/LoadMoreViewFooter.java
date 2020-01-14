package com.wuhai.myframe2.ui.homepage.qddgouwu;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chanven.lib.cptr.loadmore.ILoadMoreViewFactory;
import com.wuhai.myframe2.BuildConfig;
import com.wuhai.myframe2.R;

/**
 * Created by Zhenggy on 2017/1/9.
 */
public class LoadMoreViewFooter implements ILoadMoreViewFactory {
    private String loading = "商品加载中";
    private String fail = "加载失败，点击重试";
    private String finish = "V"+ BuildConfig.VERSION_NAME;
    private boolean bottomMargin = false;

    /**
     * 是否需要垫高
     * 这是因为钱到分期 底部导航和fr 是叠层关系，所以之前需要这个垫高
     * @param enable
     */
    public LoadMoreViewFooter(boolean enable){
        bottomMargin = enable;
    }

    public LoadMoreViewFooter(){}

    public LoadMoreViewFooter(String loading, String fail, String finish){
        this.loading = loading;
        this.fail = fail;
        this.finish = finish;
    }

    @Override
    public ILoadMoreViewFactory.ILoadMoreView madeLoadMoreView() {
        return new LoadMoreHelper();
    }

    private class LoadMoreHelper implements ILoadMoreViewFactory.ILoadMoreView {

        protected View footerView;
        protected TextView footerTv;
//        protected ProgressBar footerImageView;
        protected ImageView footerImageView;

        protected View.OnClickListener onClickRefreshListener;

        @Override
        public void init(ILoadMoreViewFactory.FootViewAdder footViewHolder, View.OnClickListener onClickRefreshListener) {
            footerView = footViewHolder.addFootView(R.layout.loadmore_footer);
            footerTv = (TextView) footerView.findViewById(R.id.loadmore_footer_tv);
            footerView.findViewById(R.id.space).setVisibility(bottomMargin ? View.VISIBLE : View.GONE);
//            footerImageView = (ProgressBar) footerView.findViewById(R.id.loadmore_footer_progressbar);
            footerImageView = (ImageView) footerView.findViewById(R.id.load_more_iv);
            this.onClickRefreshListener = onClickRefreshListener;
            showLoading();
        }

        @Override
        public void showNormal() {
            footerTv.setVisibility(View.VISIBLE);
            footerImageView.setVisibility(View.VISIBLE);
            footerImageView.setBackgroundResource(R.drawable.anim_loading_more);
            AnimationDrawable an = (AnimationDrawable) footerImageView.getBackground();
            an.start();
            footerTv.setText(loading);
        }

        @Override
        public void showLoading() {
            footerTv.setVisibility(View.VISIBLE);
            footerImageView.setVisibility(View.VISIBLE);
            footerImageView.setBackgroundResource(R.drawable.anim_loading_more);
            AnimationDrawable an = (AnimationDrawable) footerImageView.getBackground();
            an.start();
            footerTv.setText(loading);
            footerView.setOnClickListener(null);
        }

        @Override
        public void showFail(Exception exception) {
            footerTv.setVisibility(View.VISIBLE);
            footerImageView.setVisibility(View.GONE);
            footerTv.setText(fail);
            footerView.setOnClickListener(onClickRefreshListener);
        }

        @Override
        public void showNomore() {
            footerTv.setVisibility(View.VISIBLE);
            footerImageView.setVisibility(View.GONE);
            footerTv.setText(finish);
            footerView.setOnClickListener(null);
        }

        @Override
        public void setFooterVisibility(boolean isVisible) {
            footerView.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
    }
}
