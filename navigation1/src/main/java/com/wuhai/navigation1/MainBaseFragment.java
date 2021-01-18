package com.wuhai.navigation1;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * Created by flyeek on 3/7/16.
 */
public abstract class MainBaseFragment extends BaseSupportFragment {

    FrameLayout mFragmentContainerView;
    View mLoadingContainerView;
    ImageView mLoadingIndicatorImageView;
    TextView mLoadingFailedTextView;
    LinearLayout mLoadingIndicator;

    private boolean mIsDataLoaded;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsDataLoaded = false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup containerView = (ViewGroup) inflater.inflate(R.layout.main_fragment, container, false);
        mFragmentContainerView = (FrameLayout) containerView.findViewById(R.id.main_fragment_container_layout);
        mLoadingContainerView = containerView.findViewById(R.id.main_loading_container_layout);
        mLoadingIndicatorImageView = (ImageView) containerView.findViewById(R.id.main_loading_indicator_image_view);
        mLoadingFailedTextView = (TextView) containerView.findViewById(R.id.main_loading_failed_text_view);
        mLoadingIndicator = (LinearLayout) containerView.findViewById(R.id.main_loading_indicator_view);

        mFragmentContainerView.addView(createView(inflater, containerView));

        mLoadingFailedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadData();
            }
        });
        return containerView;
    }

    protected abstract View createView(LayoutInflater inflater, ViewGroup container);

    public boolean isDataLoaded() {
        return mIsDataLoaded;
    }

    public void reloadData() {
    }

    public void onPageVisible() {
        mIsDataLoaded = true;
    }

    public void onPageVisibleFully() {
    }

    public void showLoading() {
        mLoadingFailedTextView.setVisibility(View.GONE);
        mLoadingIndicator.setVisibility(View.VISIBLE);
        mLoadingIndicatorImageView.setVisibility(View.VISIBLE);
        mLoadingContainerView.setVisibility(View.VISIBLE);
        mFragmentContainerView.setVisibility(View.GONE);
        ((AnimationDrawable) mLoadingIndicatorImageView.getDrawable()).start();
    }

    public void hideLoading(boolean success) {
        hideLoading(success, "");
    }

    public void hideLoading(boolean success, String failedMessage) {
        AnimationDrawable indicatorDrawable = (AnimationDrawable) mLoadingIndicatorImageView.getDrawable();
        if (indicatorDrawable.isRunning()) {
            indicatorDrawable.stop();
        }

        if (success) {
            mFragmentContainerView.setVisibility(View.VISIBLE);

            mLoadingContainerView.setVisibility(View.GONE);
            mLoadingIndicator.setVisibility(View.GONE);
            mLoadingIndicatorImageView.setVisibility(View.GONE);
            mLoadingFailedTextView.setVisibility(View.GONE);
        } else {
            mFragmentContainerView.setVisibility(View.GONE);

            mLoadingContainerView.setVisibility(View.VISIBLE);
            mLoadingIndicator.setVisibility(View.VISIBLE);
            mLoadingIndicatorImageView.setVisibility(View.GONE);
            mLoadingFailedTextView.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(failedMessage)) {
                mLoadingFailedTextView.setText(failedMessage);
            }
        }
    }

    public boolean back(){
        return false;
    }
}
