package com.wuhai.myframe2.ui.slidingfinish;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity2;

/**
 * 侧滑退出ac
 */
public class SlidingFinishActivity extends BaseActivity2 implements View.OnClickListener {

    public static void startActivity(Context context){
        Intent intent = new Intent(context,SlidingFinishActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_slide_finish);

        Button mButtonNormal = (Button) findViewById(R.id.normal_activity);
        mButtonNormal.setOnClickListener(this);

        Button mButtonAbs = (Button) findViewById(R.id.absListview_activity);
        mButtonAbs.setOnClickListener(this);

        Button mButtonScroll = (Button) findViewById(R.id.scrollview_activity);
        mButtonScroll.setOnClickListener(this);

        Button mButtonViewPager = (Button) findViewById(R.id.viewpager_activity);
        mButtonViewPager.setOnClickListener(this);

        Button mToutiaoAc = (Button) findViewById(R.id.toutiao_activity);
        mToutiaoAc.setOnClickListener(this);
        Button mButtonAbs2 = (Button) findViewById(R.id.absListview_activity2);
        mButtonAbs2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent mIntent = null;
        switch (v.getId()) {
            case R.id.normal_activity:
                mIntent = new Intent(SlidingFinishActivity.this, NormalActivity.class);
                startActivity(mIntent);
                break;
            case R.id.absListview_activity:
                mIntent = new Intent(SlidingFinishActivity.this, AbsActivity.class);
                startActivity(mIntent);
                break;
            case R.id.scrollview_activity:
                mIntent = new Intent(SlidingFinishActivity.this, ScrollActivity.class);
                startActivity(mIntent);
                break;
            case R.id.viewpager_activity:
                mIntent = new Intent(SlidingFinishActivity.this, ViewPagerActivity.class);
                startActivity(mIntent);
                break;
            case R.id.toutiao_activity:
                mIntent = new Intent(SlidingFinishActivity.this, NormalActivity2.class);
                startActivity(mIntent);
                overridePendingTransition(R.anim.swipeback_base_slide_up_in,
                        R.anim.swipeback_base_slide_remain);
                break;
            case R.id.absListview_activity2:
                mIntent = new Intent(SlidingFinishActivity.this, AbsActivity2.class);
                startActivity(mIntent);
                overridePendingTransition(R.anim.swipeback_base_slide_up_in,
                        R.anim.swipeback_base_slide_remain);
                break;
        }

    }
}
