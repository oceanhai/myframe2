package com.wuhai.myframe2.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcTestBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;
import com.wuhai.myframe2.utils.DensityUtils;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：空ac
 */
public class TestActivity extends BaseActivity implements View.OnClickListener {

    private AcTestBinding binding;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TestActivity.class);
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
        if (intent != null) {

        }
    }

    @Override
    public void setStatistical() {

    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.ac_test);

        int i = 1;
        ClassLoader classLoader = getClassLoader();
        if (classLoader != null) {
            Log.i(TAG, "[onCreate] classLoader " + i + " : " + classLoader.toString());
            while (classLoader.getParent() != null) {
                i++;
                classLoader = classLoader.getParent();
                Log.i(TAG, "[onCreate] classLoader " + i + " : " + classLoader.toString());
            }
        }

        getTelephone();

        getDeviceId();

        method01();
    }

    private void method01() {
        //调用
        binding.tv01.setTextSize(TypedValue.COMPLEX_UNIT_DIP,
                DensityUtils.px2dp(this, (float)60));
        binding.tv01.setTextColor(Color.parseColor("#ff0000"));
        SpannableString spannableString = changTVsize("53.92%");
        binding.tv01.setText(spannableString);
    }

    /**
     * android 10（api>=29） 会抛异常SecurityException
     * @return
     */
    private String getDeviceId() {
        String tac = "";
        try {
            final TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            if(manager.getDeviceId() == null || manager.getDeviceId().equals("")) {
                if (Build.VERSION.SDK_INT >= 23) {
                    tac = manager.getDeviceId(0);
                }
            }else{
                tac = manager.getDeviceId();
            }
        }catch (Exception e){
            tac = e.getMessage();
        }
        Log.e(TAG, "deviceId = " + tac);
        return tac;
    }

    private void setListener() {
    }

    /**
     * 今天来说一下Android系统中怎么获取手机号
     * 注意：手机号码不是所有的都能获取。只是有一部分可以拿到。这个是由于移动运营商没有把手机号码的数据写入到sim卡中。
     * 这个就像是一个变量，当移动运营商为它赋值了，它自然就会有值。不赋值自然为空。这就是为什么很多人得不到本机号码的原因。
     */
    public void getTelephone() {
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceid = tm.getDeviceId();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.e(TAG, "没有权限");
            return;
        }
        String tel = tm.getLine1Number();
        String imei = tm.getSimSerialNumber();
        String imsi = tm.getSubscriberId();
        int simState = tm.getSimState();

        Log.e(TAG, "tel="+tel);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        }
    }

    public static SpannableString changTVsize(String value) {
        SpannableString spannableString = new SpannableString(value);
        if (value.contains(".")) {
            spannableString.setSpan(new RelativeSizeSpan(0.6f),
                    value.indexOf("."), value.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }



}
