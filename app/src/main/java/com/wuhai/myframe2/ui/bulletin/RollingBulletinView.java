package com.wuhai.myframe2.ui.bulletin;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.wuhai.myframe2.R;


/**
 * Created by wuhai on 2018/7/26.
 * 上滚动 公告  自定义view
 */

public class RollingBulletinView extends LinearLayout {

    private Context mContext;
    private ViewFlipper viewFlipper;
    private View marqueeTextView;
    private String[] textArrays;
    private OnPositionClickListener mClickListener;

    public RollingBulletinView(Context context) {
        super(context);
        mContext = context;
        initBasicView();
    }


    public RollingBulletinView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initBasicView();
    }

    public void setTextArraysAndClickListener(String[] textArrays, OnPositionClickListener mClickListener) {
        this.textArrays = textArrays;
        this.mClickListener = mClickListener;
        initMarqueeTextView(textArrays, mClickListener);
    }

    public void initBasicView() {
        marqueeTextView = LayoutInflater.from(mContext).inflate(R.layout.view_rolling_bulletin, null);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(marqueeTextView, layoutParams);
        viewFlipper = (ViewFlipper) marqueeTextView.findViewById(R.id.viewFlipper);
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in_bottom));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_out_top));
        viewFlipper.setFlipInterval(3000);//默认值3000
        viewFlipper.startFlipping();
    }

    public void initMarqueeTextView(String[] textArrays, final OnPositionClickListener mClickListener) {
        if (textArrays.length == 0) {
            return;
        }

        int i = 0;
        viewFlipper.removeAllViews();
        while (i < textArrays.length) {
            TextView textView = new TextView(mContext);
            textView.setText(textArrays[i]);
            textView.setTextColor(Color.parseColor("#ffffff"));
            textView.setSingleLine();
            textView.setEllipsize(TextUtils.TruncateAt.END);
            final int finalI = i;
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onClick(finalI);
                }
            });
            LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            viewFlipper.addView(textView, lp);
            i++;
        }
    }

    public void releaseResources() {
        if (marqueeTextView != null) {
            if (viewFlipper != null) {
                viewFlipper.stopFlipping();
                viewFlipper.removeAllViews();
                viewFlipper = null;
            }
            marqueeTextView = null;
        }
    }

    public interface OnPositionClickListener {
        void onClick(int postion);
    }

}
