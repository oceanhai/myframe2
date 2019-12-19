package com.wuhai.myframe2.ui.broadcast;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：发送广播  TODO 继续
 */
public class SendBroadcastActivity extends BaseActivity {

    public static final String TAG = "SendBroadcastActivity";

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SendBroadcastActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_send_broadcast);
        parseIntent();
        init();
        setListener();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if(intent != null){

        }
    }

    @Override
    public void setStatistical() {

    }

    private void init() {

    }

    private void setListener() {

    }

    /**
     * 发送无序广播(发给其他app)  moudle的broadcastreceiverdemo这个app
     * @param v
     */
    public void sendNoOrder(View v) {
        //获取 Intent 对象
        Intent intent = new Intent();
        //使用隐式意图,为intent添加指定的广播事件类型
        intent.setAction("abc");
        intent.putExtra("data", "我是广播给你一些数据");
        //该方式适用：给其他应用的广播接收者发送消息（指定应用的包名、指定类的全类名）
        intent.setComponent(new ComponentName("com.wuhai.broadcastreceiverdemo",
                "com.wuhai.broadcastreceiverdemo.MyReveiver"));
//        intent.setClassName("com.wuhai.broadcastreceiverdemo",
//                "com.wuhai.broadcastreceiverdemo.MyReveiver");
        // 发送无序广播
        sendBroadcast(intent);

        Toast.makeText(this,"我被点击了",Toast.LENGTH_LONG).show();
    }


    /**
     * 发送无序广播(发给自己)     com.wuhai.myframe2.ui.broadcast.MyReveiver这个广播接收者
     * @param v
     */
    public void sendNoOrder2(View v) {
        //获取 Intent 对象
        Intent intent = new Intent();
        //使用隐式意图,为intent添加指定的广播事件类型
        intent.setAction("abc");
        intent.putExtra("data", "我是广播给你一些数据");
        //该方式适用：给其他应用的广播接收者发送消息（this、指定类的全类名）
        intent.setClassName(this,
                "com.wuhai.myframe2.ui.broadcast.MyReveiver");
        // 发送无序广播
        sendBroadcast(intent);

    }

    public void sendOrdered(View v) {
        Intent intent = new Intent();
        intent.setAction("abc");
        //发送有序广播
        sendOrderedBroadcast(intent, null);
    }
}
