package com.wuhai.myframe2.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcCacheBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;
import com.wuhai.myframe2.utils.GsonUtils;
import com.wuhai.myframe2.utils.cache.AssetsDataUtil;
import com.wuhai.myframe2.utils.cache.CacheUtils;
import com.wuhai.myframe2.utils.cache.bean.IndexAllBean;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：页面接口返回数据的一种本地缓存策略
 */
public class CacheActivity extends BaseActivity implements View.OnClickListener {

    private AcCacheBinding binding;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, CacheActivity.class);
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
        binding = DataBindingUtil.setContentView(this, R.layout.ac_cache);
    }

    private void setListener() {
        binding.btn01.setOnClickListener(this);
        binding.btn02.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                //装载默认数据
                IndexAllBean indexAllBean = CacheUtils.getHomePageData(this);
                if (null == indexAllBean) {
                    //装载默认数据
                    String json = AssetsDataUtil.getFromAssets("home_default_data.json", this);
                    indexAllBean = GsonUtils.getInstance().fromJson(json, IndexAllBean.class);
                    //缓存默认数据到本地
                    CacheUtils.cacheHomePageData(this, indexAllBean);
                    Log.d(TAG, "缓存数据获取为空 设置默认数据");
                } else {
                    Log.d(TAG, "缓存数据获取不为空");
                    //TODO 修改了ServiceListBean 增加字段name1
//                    Log.d(TAG, "修改了ServiceListBean 增加字段name1="+indexAllBean.bannerList.get(0).name1);
                }
                break;
            case R.id.btn02:
                //装载默认数据
                String json = AssetsDataUtil.getFromAssets("home_default_data.json", this);
                indexAllBean = GsonUtils.getInstance().fromJson(json, IndexAllBean.class);
                //缓存默认数据到本地
                CacheUtils.cacheHomePageData(this, indexAllBean);
                Log.d(TAG, "缓存数据成功");
                break;
        }
    }
}
