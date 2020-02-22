package com.wuhai.myframe2.ui.eventtransmit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 事件传递机制
 * View分发机制
 * ※跟myframe 的TouchEventActivity 一样
 */
public class EventTransmitActivity extends BaseActivity2 {

    public static final String TAG = "EventTransmitActivity";
    @BindView(R.id.myview01)
    MyView myview01;
    @BindView(R.id.mybutton)
    MyButton mybutton;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, EventTransmitActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_event_transmit);
        ButterKnife.bind(this);

//        myview01.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.v(TAG, " onTouch==" + event.getAction());
//                return false;
//            }
//        });

//        myview01.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                L.v(TAG, "myviwe01点击了");
//                ToastUtils.showShort(TouchEventActivity.this,"myviwe01点击了");
//            }
//        });
    }

    //分发
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.v(TAG, " dispatchTouchEvent==" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }


    //处理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.v(TAG, " onTouchEvent==" + event.getAction());
        return super.onTouchEvent(event);
    }

    /**
     * 好像布局实现点击 不能实现呢 好坑 不知道为啥
     *
     * @param view
     */
    public void myviw01(View view) {
        Log.v(TAG, "myviwe01点击了");
        Toast.makeText(this, "myviwe01点击了",Toast.LENGTH_SHORT).show();
    }

    public void myviw02(View view) {
        Log.v(TAG, "myviwe02点击了");
    }
}
