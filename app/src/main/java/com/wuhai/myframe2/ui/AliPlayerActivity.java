package com.wuhai.myframe2.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.aliyun.player.source.UrlSource;
import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcAliPlayerBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;
import com.wuhai.myframe2.widget.alipalyer.AliyunRenderView;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：空ac
 */
public class AliPlayerActivity extends BaseActivity {

    private AcAliPlayerBinding binding;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AliPlayerActivity.class);
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
        binding = DataBindingUtil.setContentView(this, R.layout.ac_ali_player);

        //设置渲染View的类型，可选 SurfaceType.TEXTURE_VIEW 和 SurfaceType.SURFACE_VIEW
        binding.aliyunRenderView.setSurfaceType(AliyunRenderView.SurfaceType.SURFACE_VIEW);

        //设置监听
//        binding.aliyunRenderView.setXXXListener;

        //设置播放源，setDataSource为重载方法，还可以设置 sts，playAuth 等数据源
        //"http://img.xiongzhangh.com/xuan.mp4"
        //"http://img.hbszgxpt.com/video1.mp4"
        UrlSource urlSource = new UrlSource();
        urlSource.setUri("http://img.hbszgxpt.com/video1.mp4");
        binding.aliyunRenderView.setDataSource(urlSource);

        //播放相关
        binding.aliyunRenderView.prepare();
        binding.aliyunRenderView.start();
//        binding.aliyunRenderView.pause();
//        binding.aliyunRenderView.stop();
//        binding.aliyunRenderView.release();
    }

    private void setListener() {

    }
}
