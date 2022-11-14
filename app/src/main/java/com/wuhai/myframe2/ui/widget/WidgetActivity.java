package com.wuhai.myframe2.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity2;
import com.wuhai.myframe2.widget.calendar.CalendarActivity;

import butterknife.ButterKnife;

/**
 * wddget ac
 */
public class WidgetActivity extends BaseActivity2 implements View.OnClickListener {

    public static final String TAG = "rxjava";


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, WidgetActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_widget);
        ButterKnife.bind(this);

        findViewById(R.id.Switcher).setOnClickListener(this);
        findViewById(R.id.Calendar).setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Switcher://Android ViewSwitcher主要应用场景之一：比如在一个布局文件中，根据业务需求，需要在两个View间切换，在任意一个时刻，只能显示一个View。
                ViewSwitcherActivity.startActivity(this);
                break;
            case R.id.Calendar://日历
                CalendarActivity.startActivity(this);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
