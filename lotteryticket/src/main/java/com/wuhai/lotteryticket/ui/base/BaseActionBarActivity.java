package com.wuhai.lotteryticket.ui.base;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.application.BaseApplication;


/**
 * 带titlebar 、加载失败、数据空 布局的  基类Activity
 */
public abstract class BaseActionBarActivity extends BaseActivity {

    private RelativeLayout mBaseRootView;

    //titlebar
    private View mActionBarArea;
    private TextView mActionBarTitleTxt;
    private TextView mActionLeftTxt;
    private TextView mActionRightTxt;

    private FrameLayout mContentContainerView;
    private FrameLayout mLoadingContainerView;
    private ImageView mLoadingIndicatorImageView;
    private TextView mLoadingFailedTextView;
    private TextView mLoadingEmptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(R.layout.ac_base_action_bar);

        mBaseRootView = (RelativeLayout) findViewById(R.id.base_root_view);

        mActionBarArea = findViewById(R.id.base_action_bar_area);
        mActionLeftTxt = (TextView) findViewById(R.id.base_action_left_txt);
        mActionRightTxt = (TextView) findViewById(R.id.base_action_right_txt);
        mActionBarTitleTxt = (TextView) findViewById(R.id.base_action_bar_title_txt);

        mContentContainerView = (FrameLayout) findViewById(R.id.base_content_container);
        mLoadingContainerView = (FrameLayout) findViewById(R.id.base_loading_container_layout);
        mLoadingIndicatorImageView = (ImageView) findViewById(
                R.id.base_loading_indicator_image_view);
        mLoadingFailedTextView = (TextView) findViewById(R.id.base_loading_failed_text_view);
        mLoadingEmptyTextView = (TextView) findViewById(R.id.base_loading_empty_text_view);

        mActionLeftTxt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onLeftActionClicked();
            }
        });

        mActionRightTxt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mActionRightTxt.getText())
                        && mActionRightTxt.getCompoundDrawables()[2] == null) {
                    return;
                }

                onRightActionClicked();
            }
        });

        mLoadingContainerView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLoadingFailedTextView.getVisibility() == View.VISIBLE) {
                    reloadData();
                }
            }
        });

    }

    /**
     * 根布局
     * @return
     */
    public ViewGroup getRootView() {
        return mBaseRootView;
    }

    /**
     * 隐藏 titlebar
     * @param visible
     */
    public void setActionBarVisibility(boolean visible) {
        mActionBarArea.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置 titlebar 颜色
     * @param colorString
     */
    public void setActionBarBackground(String colorString) {
        mActionBarArea.setBackgroundColor(Color.parseColor(colorString));
    }

    /**
     * get title
     * @return
     */
    public String getActionTitle() {
        return mActionBarTitleTxt.getText().toString();
    }

    @Override
    public void setTitle(CharSequence title) {
        mActionBarTitleTxt.setText(title);
    }

    @Override
    public void setTitle(int titleResId) {
        mActionBarTitleTxt.setText(getString(titleResId));
    }

    /**
     * set left icon visibility
     * @param enable
     */
    public void setActionLeftEnable(boolean enable) {
        if (!enable) {
            mActionLeftTxt.setVisibility(View.GONE);
        }
    }

    /**
     * set left text(string)
     * @param actionText
     */
    public void setActionLeftText(CharSequence actionText) {
        mActionLeftTxt.setText(actionText);
    }

    /**
     * set left text(id)
     * @param actionResId
     */
    public void setActionLeftText(int actionResId) {
        mActionLeftTxt.setText(getString(actionResId));
    }

    /**
     * set left icon
     * @param iconResId
     */
    public void setActionLeftIcon(int iconResId) {
        mActionLeftTxt
                .setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(iconResId),
                        null, null, null);
    }

    /**
     * set right text(string)
     * @param actionText
     */
    public void setActionRightText(CharSequence actionText) {
        mActionRightTxt.setText(actionText);
    }

    /**
     * set right text(id)
     * @param actionResId
     */
    public void setActionRightText(int actionResId) {
        mActionRightTxt.setText(getString(actionResId));
    }

    /**
     * set right icon
     * @param iconResId
     */
    public void setActionRightIcon(int iconResId) {
        Drawable drawable = null;
        if (iconResId > 0) {
            drawable = getResources().getDrawable(iconResId);
        }
        mActionRightTxt.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
    }

    /**
     * set 实质布局(id)
     * @param layoutResID
     */
    @Override
    public void setContentView(int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        mContentContainerView.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContentContainerView.addView(view, params);
    }

    /**
     * showLoading
     */
    public void showLoading() {
        mContentContainerView.setVisibility(View.GONE);
        mLoadingContainerView.setVisibility(View.VISIBLE);
        mLoadingIndicatorImageView.setVisibility(View.VISIBLE);
        mLoadingFailedTextView.setVisibility(View.GONE);
        mLoadingEmptyTextView.setVisibility(View.GONE);

        ((AnimationDrawable) mLoadingIndicatorImageView.getDrawable()).start();
    }

    /**
     * dimssLoading
     */
    public void dimssLoading() {
        AnimationDrawable indicatorDrawable = (AnimationDrawable) mLoadingIndicatorImageView
                .getDrawable();
        if (indicatorDrawable.isRunning()) {
            indicatorDrawable.stop();
        }
        mLoadingIndicatorImageView.setVisibility(View.GONE);
    }

    /**
     * 显示 实际布局数据
     */
    public void showSuccessLayout(){
        mContentContainerView.setVisibility(View.VISIBLE);
        mLoadingContainerView.setVisibility(View.GONE);
    }

    /**
     * failed msg
     * @param message
     */
    public void showFailedMessage(String message) {
        mContentContainerView.setVisibility(View.GONE);
        mLoadingContainerView.setVisibility(View.VISIBLE);
        mLoadingIndicatorImageView.setVisibility(View.GONE);
        mLoadingFailedTextView.setVisibility(View.VISIBLE);
        mLoadingEmptyTextView.setVisibility(View.GONE);

        if (!TextUtils.isEmpty(message)) {
            mLoadingFailedTextView.setText(message);
        }
    }

    /**
     * empty msg
     * @param message
     */
    public void showEmptyMessage(String message) {
        mContentContainerView.setVisibility(View.GONE);
        mLoadingContainerView.setVisibility(View.VISIBLE);
        mLoadingIndicatorImageView.setVisibility(View.GONE);
        mLoadingFailedTextView.setVisibility(View.GONE);
        mLoadingEmptyTextView.setVisibility(View.VISIBLE);

        if (!TextUtils.isEmpty(message)) {
            mLoadingEmptyTextView.setText(message);
        }
    }

    /**
     * empty msg
     * @param emptyImage
     * @param message
     */
    public void showEmptyMessage(Drawable emptyImage,  String message) {
        mContentContainerView.setVisibility(View.GONE);
        mLoadingContainerView.setVisibility(View.VISIBLE);
        mLoadingIndicatorImageView.setVisibility(View.GONE);
        mLoadingFailedTextView.setVisibility(View.GONE);
        mLoadingEmptyTextView.setVisibility(View.VISIBLE);

        if (!TextUtils.isEmpty(message)) {
            emptyImage.setBounds(0, 0, emptyImage.getMinimumWidth(), emptyImage.getMinimumHeight());
            mLoadingEmptyTextView.setCompoundDrawables(null, emptyImage, null, null);
            mLoadingEmptyTextView.setText(message);
        }
    }

    /**
     * empty msg
     * @param emptyImage
     * @param textColor
     * @param textSize
     * @param message
     */
    public void showEmptyMessage(Drawable emptyImage, int textColor, float textSize,
            String message) {
        mContentContainerView.setVisibility(View.GONE);
        mLoadingContainerView.setVisibility(View.VISIBLE);
        mLoadingIndicatorImageView.setVisibility(View.GONE);
        mLoadingFailedTextView.setVisibility(View.GONE);
        mLoadingEmptyTextView.setVisibility(View.VISIBLE);

        if (!TextUtils.isEmpty(message)) {
            emptyImage.setBounds(0, 0, emptyImage.getMinimumWidth(), emptyImage.getMinimumHeight());
            mLoadingEmptyTextView.setCompoundDrawables(null, emptyImage, null, null);
            mLoadingEmptyTextView.setTextColor(textColor);
            mLoadingEmptyTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            mLoadingEmptyTextView.setText(message);
        }
    }

    /**
     * Toast
     * @param msg
     */
    public void showToast(String msg){
        Toast.makeText(BaseApplication.getInstance(),
                msg,Toast.LENGTH_LONG).show();
    }

    /**
     * titlebar left 点击事件
     */
    protected void onLeftActionClicked() {
        finish();
    }

    /**
     * titlebar right 点击事件
     */
    protected void onRightActionClicked() {
    }

    /**
     * 重新加载
     */
    protected void reloadData() {

    }

}
