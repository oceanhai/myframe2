package com.wuhai.myframe2.ui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.animation.AnimationActivity;
import com.wuhai.myframe2.ui.asynctask.AsyncTaskActivity;
import com.wuhai.myframe2.ui.autowrap.AutoWrapActivity;
import com.wuhai.myframe2.ui.blutooth.BluetoothActivity;
import com.wuhai.myframe2.ui.broadcast.SendBroadcastActivity;
import com.wuhai.myframe2.ui.contentprovider.ContentProviderClientActivity;
import com.wuhai.myframe2.ui.contentprovider.ContentProviderServerActivity;
import com.wuhai.myframe2.ui.countdownview.CountdownViewActivity;
import com.wuhai.myframe2.ui.customview.CustomViewActivity;
import com.wuhai.myframe2.ui.eventbus.EnventbusActivity;
import com.wuhai.myframe2.ui.eventtransmit.EventTransmitActivity;
import com.wuhai.myframe2.ui.floatview.FloatViewActivity;
import com.wuhai.myframe2.ui.glide.GlideActivity;
import com.wuhai.myframe2.ui.homepage.HomeActivity;
import com.wuhai.myframe2.ui.notificaitons.NotificationService;
import com.wuhai.myframe2.ui.okhttp3.OkHttpActivity;
import com.wuhai.myframe2.ui.popwindow.PopWindowActivity;
import com.wuhai.myframe2.ui.retrofit.RetrofitNetworkRequestActivity;
import com.wuhai.myframe2.ui.retrofit.RetrofitRxJavaRxLifecycleActivity;
import com.wuhai.myframe2.ui.rxbus.RxBusAcceptActivity;
import com.wuhai.myframe2.ui.rxjava.RxJavaActivity;
import com.wuhai.myframe2.ui.slidingfinish.SlidingFinishActivity;
import com.wuhai.myframe2.ui.sound.SoundActivity;
import com.wuhai.myframe2.ui.stickyheaderlistview.ui.StickyHeaderListView;
import com.wuhai.myframe2.ui.xywy.InputWidgetActivity;
import com.wuhai.share.QddShareCallback;
import com.wuhai.share.QddShareHelper;
import com.wuhai.share.QddShareModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.btn02)
    Button btn02;
    @BindView(R.id.btn03)
    Button btn03;
    @BindView(R.id.btn04)
    Button btn04;
    @BindView(R.id.btn05)
    Button btn05;
    @BindView(R.id.btn06)
    Button btn06;
    @BindView(R.id.btn07)
    Button btn07;
    @BindView(R.id.btn08)
    Button btn08;
    @BindView(R.id.btn09)
    Button btn09;
    @BindView(R.id.btn10)
    Button btn10;
    @BindView(R.id.btn11)
    Button btn11;
    @BindView(R.id.btn12)
    Button btn12;
    @BindView(R.id.btn13)
    Button btn13;
    @BindView(R.id.btn14)
    Button btn14;
    @BindView(R.id.btn15)
    Button btn15;
    @BindView(R.id.btn16)
    Button btn16;
    @BindView(R.id.btn17)
    Button btn17;
    @BindView(R.id.btn18)
    Button btn18;
    @BindView(R.id.btn19)
    Button btn19;
    @BindView(R.id.btn20)
    Button btn20;
    @BindView(R.id.btn21)
    Button btn21;
    @BindView(R.id.btn22)
    Button btn22;
    @BindView(R.id.btn23)
    Button btn23;
    @BindView(R.id.btn24)
    Button btn24;
    @BindView(R.id.btn25)
    Button btn25;
    @BindView(R.id.btn26)
    Button btn26;
    @BindView(R.id.btn27)
    Button btn27;
    @BindView(R.id.btn28)
    Button btn28;
    @BindView(R.id.btn29)
    Button btn29;
    @BindView(R.id.btn30)
    Button btn30;
    @BindView(R.id.btn31)
    Button btn31;
    @BindView(R.id.btn32)
    Button btn32;
    @BindView(R.id.btn33)
    Button btn33;
    @BindView(R.id.btn34)
    Button btn34;
    @BindView(R.id.btn35)
    Button btn35;
    @BindView(R.id.btn36)
    Button btn36;
    @BindView(R.id.btn37)
    Button btn37;
    @BindView(R.id.btn38)
    Button btn38;
    @BindView(R.id.btn39)
    Button btn39;
    @BindView(R.id.btn40)
    Button btn40;
    @BindView(R.id.btn41)
    Button btn41;
    @BindView(R.id.btn42)
    Button btn42;
    @BindView(R.id.btn43)
    Button btn43;
    @BindView(R.id.btn44)
    Button btn44;
    @BindView(R.id.btn45)
    Button btn45;
    @BindView(R.id.btn46)
    Button btn46;
    @BindView(R.id.btn47)
    Button btn47;
    @BindView(R.id.btn48)
    Button btn48;
    @BindView(R.id.btn49)
    Button btn49;
    @BindView(R.id.btn50)
    Button btn50;
    @BindView(R.id.btn51)
    Button btn51;
    @BindView(R.id.btn52)
    Button btn52;
    @BindView(R.id.btn53)
    Button btn53;
    @BindView(R.id.btn54)
    Button btn54;
    @BindView(R.id.btn55)
    Button btn55;
    @BindView(R.id.btn56)
    Button btn56;
    @BindView(R.id.btn57)
    Button btn57;
    @BindView(R.id.btn58)
    Button btn58;
    @BindView(R.id.btn59)
    Button btn59;
    @BindView(R.id.btn60)
    Button btn60;
    @BindView(R.id.btn61)
    Button btn61;
    @BindView(R.id.btn62)
    Button btn62;
    @BindView(R.id.btn63)
    Button btn63;
    @BindView(R.id.btn64)
    Button btn64;
    @BindView(R.id.btn65)
    Button btn65;
    @BindView(R.id.btn66)
    Button btn66;
    @BindView(R.id.btn67)
    Button btn67;
    @BindView(R.id.btn68)
    Button btn68;
    @BindView(R.id.btn69)
    Button btn69;
    @BindView(R.id.btn70)
    Button btn70;
    @BindView(R.id.btn71)
    Button btn71;
    @BindView(R.id.btn72)
    Button btn72;
    @BindView(R.id.btn73)
    Button btn73;
    @BindView(R.id.btn74)
    Button btn74;
    @BindView(R.id.btn75)
    Button btn75;
    @BindView(R.id.btn76)
    Button btn76;
    @BindView(R.id.btn77)
    Button btn77;
    @BindView(R.id.btn78)
    Button btn78;
    @BindView(R.id.btn79)
    Button btn79;
    @BindView(R.id.btn80)
    Button btn80;
    @BindView(R.id.btn02_1)
    Button btn021;
    @BindView(R.id.btn02_2)
    Button btn022;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        ButterKnife.bind(this);

        //分享初始化
        QddShareHelper.initShare(this);

        setListener();
    }

    private void setListener() {
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn021.setOnClickListener(this);
        btn022.setOnClickListener(this);
        btn03.setOnClickListener(this);
        btn04.setOnClickListener(this);
        btn05.setOnClickListener(this);
        btn06.setOnClickListener(this);
        btn07.setOnClickListener(this);
        btn08.setOnClickListener(this);
        btn09.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);
        btn13.setOnClickListener(this);
        btn14.setOnClickListener(this);
        btn15.setOnClickListener(this);
        btn16.setOnClickListener(this);
        btn17.setOnClickListener(this);
        btn18.setOnClickListener(this);
        btn19.setOnClickListener(this);
        btn20.setOnClickListener(this);
        btn21.setOnClickListener(this);
        btn22.setOnClickListener(this);
        btn23.setOnClickListener(this);
        btn24.setOnClickListener(this);
        btn25.setOnClickListener(this);
        btn26.setOnClickListener(this);
        btn27.setOnClickListener(this);
        btn28.setOnClickListener(this);
        btn29.setOnClickListener(this);
        btn30.setOnClickListener(this);
        btn31.setOnClickListener(this);
        btn32.setOnClickListener(this);
        btn33.setOnClickListener(this);
        btn34.setOnClickListener(this);
        btn35.setOnClickListener(this);
        btn36.setOnClickListener(this);
        btn37.setOnClickListener(this);
        btn38.setOnClickListener(this);
        btn39.setOnClickListener(this);
        btn40.setOnClickListener(this);
        btn41.setOnClickListener(this);
        btn42.setOnClickListener(this);
        btn43.setOnClickListener(this);
        btn44.setOnClickListener(this);
        btn45.setOnClickListener(this);
        btn46.setOnClickListener(this);
        btn47.setOnClickListener(this);
        btn48.setOnClickListener(this);
        btn49.setOnClickListener(this);
        btn50.setOnClickListener(this);
        btn51.setOnClickListener(this);
        btn52.setOnClickListener(this);
        btn53.setOnClickListener(this);
        btn54.setOnClickListener(this);
        btn55.setOnClickListener(this);
        btn56.setOnClickListener(this);
        btn57.setOnClickListener(this);
        btn58.setOnClickListener(this);
        btn59.setOnClickListener(this);
        btn60.setOnClickListener(this);
        btn61.setOnClickListener(this);
        btn62.setOnClickListener(this);
        btn63.setOnClickListener(this);
        btn64.setOnClickListener(this);
        btn65.setOnClickListener(this);
        btn66.setOnClickListener(this);
        btn67.setOnClickListener(this);
        btn68.setOnClickListener(this);
        btn69.setOnClickListener(this);
        btn70.setOnClickListener(this);
        btn71.setOnClickListener(this);
        btn72.setOnClickListener(this);
        btn73.setOnClickListener(this);
        btn74.setOnClickListener(this);
        btn75.setOnClickListener(this);
        btn76.setOnClickListener(this);
        btn77.setOnClickListener(this);
        btn78.setOnClickListener(this);
        btn79.setOnClickListener(this);
        btn80.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01://ConstraintLayout约束布局
                ConstraintLayoutActivity.startActivity(this);
                break;
            case R.id.btn02://Retrofit网络请求框架
                RetrofitNetworkRequestActivity.startActivity(this);
                break;
            case R.id.btn02_1://Retrofit+RxJava+RxLifecycle
                RetrofitRxJavaRxLifecycleActivity.startActivity(this);
                break;
            case R.id.btn02_2://okhttp3
                OkHttpActivity.startActivity(this);
                break;
            case R.id.btn03://进程间ContentProvider通信-数据准备 db插入数据
                ContentProviderServerActivity.startActivity(this);
                break;
            case R.id.btn04://进程间ContentProvider通信-子进程（但子进程是可以直接读取数据库）
                ContentProviderClientActivity.startActivity(this);
                break;
            case R.id.btn05://Intent+bundle实现跨进程通讯：接收方
                Toast.makeText(this, "请去myclient点击btn02", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn06://rxjava
                RxJavaActivity.startActivity(this);
                break;
            case R.id.btn07://MVVM
                MvvmActivity.startActivity(this);
                break;
            case R.id.btn08://自定义view（集合）
                CustomViewActivity.startActivity(this);
                break;
            case R.id.btn09://glide框架
                GlideActivity.startActivity(this);
                break;
            case R.id.btn10://StickyHeaderListView
                StickyHeaderListView.startActivity(this);
                break;
            case R.id.btn11://声音
                SoundActivity.startActivity(this);
                break;
            case R.id.btn12://RxBus
                RxBusAcceptActivity.startActivity(this);
                break;
            case R.id.btn13://enventbus发布/订阅的事件
                EnventbusActivity.startActivity(this);
                break;
            case R.id.btn14://TAKE_PICTURE(拍照 测试myhydemo为啥崩溃)
                TakePictureActivity.startActivity(this);
                break;
            case R.id.btn15://slidableActivity(右滑退出Activity)
                SlidableActivity.startActivity(this);
                break;
            case R.id.btn16://原myframe的侧滑销毁activity
                SlidingFinishActivity.startActivity(this);
                break;
            case R.id.btn17://现场保护
                SaveInstanceStateActivity.startActivity(this);
                break;
            case R.id.btn18://蓝牙开发
                BluetoothActivity.startActivity(this);
                break;
            case R.id.btn19://发送广播
                SendBroadcastActivity.startActivity(this);
                break;
            case R.id.btn20://动画
                AnimationActivity.startActivity(this);
                break;
            case R.id.btn21://shareSDK 分享
                //在onCreat()方法中调用分享初始化方法。
                String title = "震惊！4毛5买走了一千元京东购物卡";
                String contentText = "亲测有效，全免费，赶紧来试试手气。";
                String imageUrl = "http://static.qujingjia.com/upload/lanhai_qjj/image/qjj.png";
                String url = "https://www.baidu.com/";
                QddShareHelper.showShare(this, new QddShareModel(title, contentText,
                        imageUrl, url), new QddShareCallback() {
                    @Override
                    public void onShare(Platform platform, Platform.ShareParams shareParams) {
                        Log.d("qjj_share", "share platform="+platform.toString()
                                +",shareParams="+shareParams.toString());
                    }

                    @Override
                    public void onComplete(String platformName) {
                        Log.d("qjj_share", "share onComplete");
                    }

                    @Override
                    public void onError(String platformName) {
                        Log.d("qjj_share", "share onError");
                    }

                    @Override
                    public void onCancel(String platformName) {
                        Log.d("qjj_share", "share onCancel");
                    }
                });

                break;
            case R.id.btn22://简单通知
                notifySimple();
                break;
            case R.id.btn23://XUpdate轻量级升级框架
                Toast.makeText(this,"还没加上去呢",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn24://日期选择 联动
                DialogChooseDateActivity.startActivity(this);
                break;
            case R.id.btn25://地址选择 联动
                AddressChooseActivity.startActivity(this);
                break;
            case R.id.btn26://首页复杂布局实现方案
                HomeActivity.startActivity(this);
                break;
            case R.id.btn27://浮动item
                FloatViewActivity.startActivity(this);
                break;
            case R.id.btn28://Spinner
                SpinnerActivity.startActivity(this);
                break;
            case R.id.btn29://popwindow
                PopWindowActivity.startActivity(this);
                break;
            case R.id.btn30://可根据选项自动换行的单选/多选/排他选
                AutoWrapActivity.startActivity(this);
                break;
            case R.id.btn31://事件传递机制
                EventTransmitActivity.startActivity(this);
                break;
            case R.id.btn32://asynctask 测试
                AsyncTaskActivity.startActivity(this);
                break;
            case R.id.btn33://寻医问药 登录控件+网络请求
                InputWidgetActivity.startActivity(this);
                break;
            case R.id.btn34://CountdownView
                CountdownViewActivity.startActivity(this);
                break;
        }
    }

    public final static String ACTION_SIMPLE = "com.android.peter.notificationdemo.ACTION_SIMPLE";
    public final static String ACTION_DELETE = "com.android.peter.notificationdemo.ACTION_DELETE";
    private void notifySimple() {
        NotificationManager mNM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //创建点击通知时发送的广播
        Intent intent = new Intent(this, NotificationService.class);
        intent.setAction(ACTION_SIMPLE);
        PendingIntent pi = PendingIntent.getService(this,0,intent,0);
        //创建删除通知时发送的广播
        Intent deleteIntent = new Intent(this,NotificationService.class);
        deleteIntent.setAction(ACTION_DELETE);
        PendingIntent deletePendingIntent = PendingIntent.getService(this,0,deleteIntent,0);
        //创建通知
        Notification.Builder nb = new Notification.Builder(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel("channel_01", "我是渠道名字",
                    NotificationManager.IMPORTANCE_LOW);
            mNM.createNotificationChannel(mChannel);
            nb.setChannelId("channel_01");
        }
        nb
        //设置通知左侧的小图标
        .setSmallIcon(R.mipmap.ic_launcher)
        //设置通知标题
        .setContentTitle("通知title")
        //设置通知内容
        .setContentText("通知content")
        //设置点击通知后自动删除通知
        .setAutoCancel(true)
        //设置显示通知时间
        .setShowWhen(true)
        //设置通知右侧的大图标
        .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_launcher))
        //设置点击通知时的响应事件
        .setContentIntent(pi)
        //设置删除通知时的响应事件
        .setDeleteIntent(deletePendingIntent);
        //发送通知
        mNM.notify(0,nb.build());
    }
}
