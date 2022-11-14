package com.wuhai.myframe2.ui;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.application.BaseApplication;
import com.wuhai.myframe2.ui.acswitch.AcSwitchMainActivity;
import com.wuhai.myframe2.ui.animation.AnimationActivity;
import com.wuhai.myframe2.ui.asynctask.AsyncTaskActivity;
import com.wuhai.myframe2.ui.autowrap.AutoWrapActivity;
import com.wuhai.myframe2.ui.bitmap.BitmapActivity;
import com.wuhai.myframe2.ui.blutooth.BluetoothActivity;
import com.wuhai.myframe2.ui.broadcast.SendBroadcastActivity;
import com.wuhai.myframe2.ui.bulletin.BulletinActivity;
import com.wuhai.myframe2.ui.contentprovider.ContentProviderClientActivity;
import com.wuhai.myframe2.ui.contentprovider.ContentProviderServerActivity;
import com.wuhai.myframe2.ui.countdownview.CountdownViewActivity;
import com.wuhai.myframe2.ui.customview.CustomViewActivity;
import com.wuhai.myframe2.ui.dagger2.Dagger2Activity;
import com.wuhai.myframe2.ui.db.DBActivity;
import com.wuhai.myframe2.ui.dialog.DatePickerDialogActivity;
import com.wuhai.myframe2.ui.dialog.DialogChooseDateActivity;
import com.wuhai.myframe2.ui.dkplayer.DkplayerActivity;
import com.wuhai.myframe2.ui.eventbus.EnventbusActivity;
import com.wuhai.myframe2.ui.eventtransmit.EventTransmitActivity;
import com.wuhai.myframe2.ui.face.FaceDetectorActivity;
import com.wuhai.myframe2.ui.flexboxlayout.FlexboxLayoutActivity;
import com.wuhai.myframe2.ui.floatview.FloatViewActivity;
import com.wuhai.myframe2.ui.flowlayout.FlowLayoutActivity;
import com.wuhai.myframe2.ui.glide.GlideActivity;
import com.wuhai.myframe2.ui.homepage.HomeActivity;
import com.wuhai.myframe2.ui.hook.HookActivity;
import com.wuhai.myframe2.ui.lifecycle.ThreadStartActivity;
import com.wuhai.myframe2.ui.livedata.LiveDataMainActivity;
import com.wuhai.myframe2.ui.materialdesign.MaterialDesignActivity;
import com.wuhai.myframe2.ui.navigation.NavigationMenuActivity;
import com.wuhai.myframe2.ui.notificaitons.NotificationService;
import com.wuhai.myframe2.ui.okhttp3.OkHttpActivity;
import com.wuhai.myframe2.ui.pactera.PacteraActivity;
import com.wuhai.myframe2.ui.pldroid.PldroidPlayerActivity;
import com.wuhai.myframe2.ui.plugin.PluginActivity;
import com.wuhai.myframe2.ui.popwindow.PopWindowActivity;
import com.wuhai.myframe2.ui.retrofit.RetrofitNetworkRequestActivity;
import com.wuhai.myframe2.ui.retrofit.RetrofitRxJavaRxLifecycleActivity;
import com.wuhai.myframe2.ui.rxbus.RxBusAcceptActivity;
import com.wuhai.myframe2.ui.rxjava.RxJavaActivity;
import com.wuhai.myframe2.ui.service.ServiceActivity;
import com.wuhai.myframe2.ui.slidingfinish.SlidingFinishActivity;
import com.wuhai.myframe2.ui.sound.SoundActivity;
import com.wuhai.myframe2.ui.stickyheaderlistview.ui.StickyHeaderListView;
import com.wuhai.myframe2.ui.study.LoginActivity;
import com.wuhai.myframe2.ui.study.SnackBarActivity;
import com.wuhai.myframe2.ui.theme.ThemeMain2Activity;
import com.wuhai.myframe2.ui.theme.ThemeMain3Activity;
import com.wuhai.myframe2.ui.theme.ThemeMainActivity;
import com.wuhai.myframe2.ui.thread.ThreadActivity;
import com.wuhai.myframe2.ui.timer.CountDownTimeActivity;
import com.wuhai.myframe2.ui.webview.WebViewActivity;
import com.wuhai.myframe2.ui.widget.WidgetActivity;
import com.wuhai.myframe2.ui.xingneng.XingNingMainActivity;
import com.wuhai.myframe2.ui.xywy.InputWidgetActivity;
import com.wuhai.myframe2.utils.PermissionUtils;
import com.wuhai.share.QddShareCallback;
import com.wuhai.share.QddShareHelper;
import com.wuhai.share.QddShareModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";

    @BindView(R.id.btn01)
    TextView btn01;
    @BindView(R.id.btn02)
    TextView btn02;
    @BindView(R.id.btn03)
    TextView btn03;
    @BindView(R.id.btn04)
    TextView btn04;
    @BindView(R.id.btn05)
    TextView btn05;
    @BindView(R.id.btn06)
    TextView btn06;
    @BindView(R.id.btn07)
    TextView btn07;
    @BindView(R.id.btn08)
    TextView btn08;
    @BindView(R.id.btn09)
    TextView btn09;
    @BindView(R.id.btn10)
    TextView btn10;
    @BindView(R.id.btn11)
    TextView btn11;
    @BindView(R.id.btn12)
    TextView btn12;
    @BindView(R.id.btn13)
    TextView btn13;
    @BindView(R.id.btn14)
    TextView btn14;
    @BindView(R.id.btn15)
    TextView btn15;
    @BindView(R.id.btn16)
    TextView btn16;
    @BindView(R.id.btn17)
    TextView btn17;
    @BindView(R.id.btn18)
    TextView btn18;
    @BindView(R.id.btn19)
    TextView btn19;
    @BindView(R.id.btn20)
    TextView btn20;
    @BindView(R.id.btn21)
    TextView btn21;
    @BindView(R.id.btn22)
    TextView btn22;
    @BindView(R.id.btn23)
    TextView btn23;
    @BindView(R.id.btn24)
    TextView btn24;
    @BindView(R.id.btn25)
    TextView btn25;
    @BindView(R.id.btn26)
    TextView btn26;
    @BindView(R.id.btn27)
    TextView btn27;
    @BindView(R.id.btn28)
    TextView btn28;
    @BindView(R.id.btn29)
    TextView btn29;
    @BindView(R.id.btn30)
    TextView btn30;
    @BindView(R.id.btn31)
    TextView btn31;
    @BindView(R.id.btn32)
    TextView btn32;
    @BindView(R.id.btn33)
    TextView btn33;
    @BindView(R.id.btn34)
    TextView btn34;
    @BindView(R.id.btn35)
    TextView btn35;
    @BindView(R.id.btn36)
    TextView btn36;
    @BindView(R.id.btn37)
    TextView btn37;
    @BindView(R.id.btn38)
    TextView btn38;
    @BindView(R.id.btn39)
    TextView btn39;
    @BindView(R.id.btn40)
    TextView btn40;
    @BindView(R.id.btn41)
    TextView btn41;
    @BindView(R.id.btn42)
    TextView btn42;
    @BindView(R.id.btn43)
    TextView btn43;
    @BindView(R.id.btn44)
    TextView btn44;
    @BindView(R.id.btn45)
    TextView btn45;
    @BindView(R.id.btn46)
    TextView btn46;
    @BindView(R.id.btn47)
    TextView btn47;
    @BindView(R.id.btn48)
    TextView btn48;
    @BindView(R.id.btn49)
    TextView btn49;
    @BindView(R.id.btn49_1)
    TextView btn49_1;
    @BindView(R.id.btn50)
    TextView btn50;
    @BindView(R.id.btn51)
    TextView btn51;
    @BindView(R.id.btn52)
    TextView btn52;
    @BindView(R.id.btn53)
    TextView btn53;
    @BindView(R.id.btn54)
    TextView btn54;
    @BindView(R.id.btn55)
    TextView btn55;
    @BindView(R.id.btn56)
    TextView btn56;
    @BindView(R.id.btn57)
    TextView btn57;
    @BindView(R.id.btn58)
    TextView btn58;
    @BindView(R.id.btn59)
    TextView btn59;
    @BindView(R.id.btn60)
    TextView btn60;
    @BindView(R.id.btn61)
    TextView btn61;
    @BindView(R.id.btn62)
    TextView btn62;
    @BindView(R.id.btn63)
    TextView btn63;
    @BindView(R.id.btn64)
    TextView btn64;
    @BindView(R.id.btn65)
    TextView btn65;
    @BindView(R.id.btn66)
    TextView btn66;
    @BindView(R.id.btn67)
    TextView btn67;
    @BindView(R.id.btn68)
    TextView btn68;
    @BindView(R.id.btn69)
    TextView btn69;
    @BindView(R.id.btn70)
    TextView btn70;
    @BindView(R.id.btn71)
    TextView btn71;
    @BindView(R.id.btn72)
    TextView btn72;
    @BindView(R.id.btn73)
    TextView btn73;
    @BindView(R.id.btn74)
    TextView btn74;
    @BindView(R.id.btn75)
    TextView btn75;
    @BindView(R.id.btn76)
    TextView btn76;
    @BindView(R.id.btn77)
    TextView btn77;
    @BindView(R.id.btn78)
    TextView btn78;
    @BindView(R.id.btn79)
    TextView btn79;
    @BindView(R.id.btn80)
    TextView btn80;
    @BindView(R.id.btn02_1)
    TextView btn021;
    @BindView(R.id.btn02_2)
    TextView btn022;
    @BindView(R.id.btn09_1)
    TextView btn091;
    @BindView(R.id.btn58_1)
    TextView btn581;
    @BindView(R.id.btn58_2)
    TextView btn582;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        ButterKnife.bind(this);

        init();

        //分享初始化
        QddShareHelper.initShare(this);

        setListener();
    }

    private void init() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int mMaxMemory = activityManager.getMemoryClass();
        Log.e(TAG, "mMaxMemory = " + mMaxMemory);

        BaseApplication baseApplication1 = new BaseApplication();
        BaseApplication baseApplication2 = new BaseApplication();
        BaseApplication baseApplication3 = new BaseApplication();
        Log.e(TAG, "baseApplication1="+baseApplication1+",baseApplication2="+baseApplication2+
                ",baseApplication3="+baseApplication3);
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
        btn091.setOnClickListener(this);
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
        btn49_1.setOnClickListener(this);
        btn50.setOnClickListener(this);
        btn51.setOnClickListener(this);
        btn52.setOnClickListener(this);
        btn53.setOnClickListener(this);
        btn54.setOnClickListener(this);
        btn55.setOnClickListener(this);
        btn56.setOnClickListener(this);
        btn57.setOnClickListener(this);
        btn58.setOnClickListener(this);
        btn581.setOnClickListener(this);
        btn582.setOnClickListener(this);
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
            case R.id.btn04://进程间ContentProvider通信-子进程(但子进程是可以直接读取数据库)
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
            case R.id.btn08://自定义view(集合)
                CustomViewActivity.startActivity(this);
                break;
            case R.id.btn09://glide框架
                GlideActivity.startActivity(this);
                break;
            case R.id.btn09_1://fresco框架
                FrescoActivity.startActivity(this);
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
                String title = "震惊!4毛5买走了一千元京东购物卡";
                String contentText = "亲测有效，全免费，赶紧来试试手气。";
                String imageUrl = "https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png";
                String url = "https://www.baidu.com/";
                QddShareHelper.showShare(this, new QddShareModel(title, contentText,
                        imageUrl, url), new QddShareCallback() {
                    @Override
                    public void onShare(Platform platform, Platform.ShareParams shareParams) {
                        Log.d("qjj_share", "share platform=" + platform.toString()
                                + ",shareParams=" + shareParams.toString());
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
                Toast.makeText(this, "还没加上去呢", Toast.LENGTH_SHORT).show();
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
            case R.id.btn28://Spinner  xml 省市区联动
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
            case R.id.btn34://CountdownView github 倒计时样式
                CountdownViewActivity.startActivity(this);
                break;
            case R.id.btn35://导航菜单
                NavigationMenuActivity.startActivity(this);
                break;
            case R.id.btn36://材料设计
                MaterialDesignActivity.startActivity(this);
                break;
            case R.id.btn37://热门标签的流式布局FlowLayout (自定义)
                FlowLayoutActivity.startActivity(this);
                break;
            case R.id.btn38://谷歌FlexboxLayout 实现热门标签
                FlexboxLayoutActivity.startActivity(this);
                break;
            case R.id.btn39://横屏生命周期
                HorizontalScreenActivity.startActivity(this);
                break;
            case R.id.btn40://Service
                ServiceActivity.startActivity(this);
                break;
            case R.id.btn41://线程
                ThreadActivity.startActivity(this);
                break;
            case R.id.btn42://Activity切换动画
                AcSwitchMainActivity.startActivity(this);
                break;
            case R.id.btn43://Hook学习
                HookActivity.startActivity(this);
                break;
            case R.id.btn44://Chronometer 计时器
                ChronometerActivity.startActivity(this);
                break;
            case R.id.btn45://FaceDetector人脸识别
                FaceDetectorActivity.startActivity(this);
                break;
            case R.id.btn46://bitmap
                BitmapActivity.startActivity(this);
                break;
            case R.id.btn47://Lottie动画
                LottieActivity.startActivity(this);
                break;
            case R.id.btn48://自定义公告控件
                BulletinActivity.startActivity(this);
                break;
            case R.id.btn49://webview
                WebViewActivity.startActivity(this);
                break;
            case R.id.btn49_1://webview 清缓存
                clearWebViewCache();
                break;
            case R.id.btn50://横竖屏可切换的页面  portrait and landscape
                OrientationActivity.startActivity(this);
                break;
            case R.id.btn51://测试页面跳转和回退，页面间生命周期的执行顺序
                ThreadStartActivity.startActivity(this);
                break;
            case R.id.btn52://xywy TitleBar 演示 并且实现侵入式
                TitleBarActivity.startActivity(this);
                break;
            case R.id.btn53://XXXX
                break;
            case R.id.btn54://SnackBar
                SnackBarActivity.startActivity(this);
                break;
            case R.id.btn55://TextInputLayout
                LoginActivity.startActivity(this);
                break;
            case R.id.btn56://插件
                PluginActivity.startActivity(this);
                break;
            case R.id.btn57://Pactera
                PacteraActivity.startActivity(this);
                break;
            case R.id.btn58://主题研究
                ThemeMainActivity.startActivity(this);
                break;
            case R.id.btn58_1://theme2
                ThemeMain2Activity.startActivity(this);
                break;
            case R.id.btn58_2://theme3
                ThemeMain3Activity.startActivity(this);
                break;
            case R.id.btn59://桌面图标添加数字角标
                BadgeNumberActivity.startActivity(this);
                break;
            case R.id.btn60://test
                TestActivity.startActivity(this);
                break;
            case R.id.btn61://倒计时 可暂停的
                CountDownTimeActivity.startActivity(this);
                break;
            case R.id.btn62://性能优化
                XingNingMainActivity.startActivity(this);
                break;
            case R.id.btn63://应用通知设置
                gotoSet();
                break;
            case R.id.btn64://权限设置
                gotoPermission();
                break;
            case R.id.btn65://DatePickerDialog
                DatePickerDialogActivity.startActivity(this);
                break;
            case R.id.btn66://db
                DBActivity.startActivity(this);
                break;
            case R.id.btn67://LiveData
                LiveDataMainActivity.startActivity(this);
                break;
            case R.id.btn68://Dagger2
                Dagger2Activity.startActivity(this);
                break;
            case R.id.btn69://Dkplayer
                DkplayerActivity.startActivity(this);
                break;
            case R.id.btn70://页面接口返回数据的一种本地缓存策略
                CacheActivity.startActivity(this);
                break;
            case R.id.btn71://七牛播放器 TODO 废弃，这个播放器不好
                PldroidPlayerActivity.startActivity(this);
                break;
            case R.id.btn72://阿里播放器
                AliPlayerActivity.startActivity(this);
                break;
            case R.id.btn73://水印照片
                WaterMarkActivity.startActivity(this);
                break;
            case R.id.btn74://水印照片2
                //在KT的companion object中做的任何声明，在Java中不能直接调用，而是利用【Companion】实体调用出来的，
                //这就相当于new个类，调用实例方法了，而非静态方法。
//                WaterMark2Activity.Companion.startActivity(this);

                //TODO 参考文章 kotlin
                //TODO https://blog.csdn.net/qq_27489007/article/details/121106425
                //正确的做法是在派生类中 + @JvmStatic
                WaterMark2Activity.startActivity(this);
                break;
            case R.id.btn75://一些控件
                WidgetActivity.startActivity(this);
                break;
        }
    }

    /**
     * 设置权限
     */
    private void gotoPermission() {
        String sdk = android.os.Build.VERSION.SDK; // SDK号

        String model = android.os.Build.MODEL; // 手机型号

        String release = android.os.Build.VERSION.RELEASE; // android系统版本号
        String brand = Build.BRAND;//手机厂商
        if (TextUtils.equals(brand.toLowerCase(), "redmi") || TextUtils.equals(brand.toLowerCase(), "xiaomi")) {
            PermissionUtils.gotoMiuiPermission(this);//小米
        } else if (TextUtils.equals(brand.toLowerCase(), "meizu")) {
            PermissionUtils.gotoMeizuPermission(this);
        } else if (TextUtils.equals(brand.toLowerCase(), "huawei") || TextUtils.equals(brand.toLowerCase(), "honor")) {
            PermissionUtils.gotoHuaweiPermission(this);
        } else {
            startActivity(PermissionUtils.getAppDetailSettingIntent(this));
        }
    }

    /**
     * 去设置推送开关
     */
    private void gotoSet() {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= 26) {
            // android 8.0引导
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
        } else if (Build.VERSION.SDK_INT >= 21) {
            // android 5.0-7.0
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", getPackageName());
            intent.putExtra("app_uid", getApplicationInfo().uid);
        } else {
            // 其他
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", getPackageName(), null));
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public final static String ACTION_SIMPLE = "com.android.peter.notificationdemo.ACTION_SIMPLE";
    public final static String ACTION_DELETE = "com.android.peter.notificationdemo.ACTION_DELETE";

    private void notifySimple() {
        NotificationManager mNM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //创建点击通知时发送的广播
        Intent intent = new Intent(this, NotificationService.class);
        intent.setAction(ACTION_SIMPLE);
        PendingIntent pi = PendingIntent.getService(this, 0, intent, 0);
        //创建删除通知时发送的广播
        Intent deleteIntent = new Intent(this, NotificationService.class);
        deleteIntent.setAction(ACTION_DELETE);
        PendingIntent deletePendingIntent = PendingIntent.getService(this, 0, deleteIntent, 0);
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
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                //设置点击通知时的响应事件
                .setContentIntent(pi)
                //设置删除通知时的响应事件
                .setDeleteIntent(deletePendingIntent);
        //发送通知
        mNM.notify(0, nb.build());
    }


    /**
     * 清除WebView缓存 在onDestroy调用这个方法就可以了
     */
    public void clearWebViewCache() {
        //返回null 而且是废弃的方法
//        File file = CacheManager.getCacheFileBaseDir();
//        Log.e(TAG, "clearWebViewCache file 路径="+file.getAbsolutePath());
//        if (file != null && file.exists() && file.isDirectory()) {
//            for (File item : file.listFiles()) {
//                Log.e(TAG, "clearWebViewCache delete 的file ="+item.getAbsolutePath());
//                item.delete();
//            }
//            file.delete();
//        }

        //false false 因为都没有生成对应的db TODO 所以这个也是有问题的
        boolean result1 = deleteDatabase("webview.db");
        boolean result2 = deleteDatabase("webviewCache.db");
        Log.e(TAG, "clearWebViewCache result1="+result1+",result2="+result2);

        WebView webView = new WebView(this);
        webView.clearCache(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
