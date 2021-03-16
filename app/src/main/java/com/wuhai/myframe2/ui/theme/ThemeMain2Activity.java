package com.wuhai.myframe2.ui.theme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcThemeMain2Binding;
import com.wuhai.myframe2.ui.base.BaseActivity;
import com.wuhai.myframe2.ui.theme.utils.StatusBarUtil;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.util.LogUtils;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：ThemeMain
 */
public class ThemeMain2Activity extends BaseActivity {

    private AcThemeMain2Binding binding;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ThemeMain2Activity.class);
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
        binding = DataBindingUtil.setContentView(this, R.layout.ac_theme_main2);

        /**
         * 方案1
         */
        //设置状态栏透明
        StatusBarUtil.setImmersiveStatusBar(this,true);
        //topview适配  TODO 1固定titlebar颜色 这里设置view并设置对应颜色  2如果沉浸式，这里注释掉
//        View topView = findViewById(R.id.top_view);
//        DeviceInfoUtil.setTopTitleStyle(this, topView);//

        //自定义的图片适配器，也可以使用默认的BannerImageAdapter
        ImageAdapter adapter = new ImageAdapter(DataBean.getTestData2());

        binding.banner.setAdapter(adapter)
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(this))//设置指示器
                .setOnBannerListener((data, position) -> {
                    Snackbar.make(binding.banner, ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                    LogUtils.d("position：" + position);
                });

    }

    private void setListener() {

    }

}
