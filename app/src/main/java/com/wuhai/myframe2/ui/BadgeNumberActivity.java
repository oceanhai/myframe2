package com.wuhai.myframe2.ui;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcBadgeNumberBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：桌面图标添加数字角标
 *
 * 本ac参考
 * BadgeForAppIcon		为桌面图标添加数字角标
 * 			https://github.com/whytot/BadgeForAppIcon
 * 			https://blog.csdn.net/weixin_42976236/article/details/81704627
 *
 * 还有一个github的关注的人数更高些
 * ShortcutBadger		为桌面图标添加数字角标
 * 			https://github.com/leolin310148/ShortcutBadger
 *
 */
public class BadgeNumberActivity extends BaseActivity implements View.OnClickListener {

    private AcBadgeNumberBinding binding;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, BadgeNumberActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ac_null);
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
        binding = DataBindingUtil.setContentView(this, R.layout.ac_badge_number);
    }

    private void setListener() {
        binding.huawei.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.huawei:
                try {
                    setHUAWEIIconBadgeNum(1);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, e.getMessage());
                }
                break;
        }
    }



    /**
     * 谷歌
     * TODO 未验证
     * @param count
     * @throws Exception
     */
    private void setGoogleIconBadgeNum(int count) throws Exception {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
            intent.putExtra("badge_count", count);
            intent.putExtra("badge_count_package_name", getPackageName());
            intent.putExtra("badge_count_class_name", getLaunchIntentForPackage()); // com.test. badge.MainActivity is your apk main activity

            sendBroadcast(intent);
        }else{
            Log.e(TAG, "Android8.0以下不支持");
        }

    }

    /**
     * 三星
     * TODO 未验证
     * @param count
     * @throws Exception
     */
    private void setSAMSUNGIconBadgeNum(int count) throws Exception {
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", count);
        intent.putExtra("badge_count_package_name", getPackageName());
        intent.putExtra("badge_count_class_name", getLaunchIntentForPackage());
        sendBroadcast(intent);
    }

    /**
     * 小米
     * 必须发送notification
     * 只支持MIUI6-10（网上有针对6以下的砖，我没法验证，所以这里就不贴出来了）
     * 当APP处于前台时，数字会自动清空（因此，APP必须处于后台时才可以设置成功）
     * TODO 未验证
     * @param count
     * @param notification
     * @return
     * @throws Exception
     */
    private Notification setXIAOMIIconBadgeNum(int count, Notification notification) throws Exception {
        Field field = notification.getClass().getDeclaredField("extraNotification");
        Object extraNotification = field.get(notification);
        Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
        method.invoke(extraNotification, count);
        return notification;
    }

    /**
     * 我的是华为手机验证成功
     * @param count
     * @throws Exception
     */
    private void setHUAWEIIconBadgeNum(int count) throws Exception {
        String packageName = getPackageName();
        String launcherPackageName = getLaunchIntentForPackage();
        Log.e(TAG, "packageName="+packageName+",launcherPackageName="+launcherPackageName);
        Bundle bunlde = new Bundle();
        bunlde.putString("package", packageName);
        bunlde.putString("class", launcherPackageName);
        bunlde.putInt("badgenumber", count);
        getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, bunlde);
    }

    public String getLaunchIntentForPackage() {
        return getPackageManager().getLaunchIntentForPackage(getPackageName()).getComponent().getClassName();
    }

    /**
     * 获取当前launcher包名的方法
     * 用来判断是华为 小米 oppo
     * @return
     */
    public String getLauncherPackageName() {
        //获取ApplicationContext
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        final ResolveInfo res = getPackageManager().resolveActivity(intent, 0);
        if (res.activityInfo == null) {
            // should not happen. A home is always installed.
            return null;
        }
        if (res.activityInfo.packageName.equals("android")) {
            return null;
        } else {
            return res.activityInfo.packageName;
        }
    }
}
