package com.wuhai.myframe2.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 wh
 * <p>
 * 创建日期 2019/11/20 17:47
 * <p>
 * 描述：fresco
 */
public class FrescoActivity extends BaseActivity {

    @BindView(R.id.gif_sdv)
    SimpleDraweeView gifSdv;

    //809x1000
    private String url = "https://b-ssl.duitang.com/uploads/item/201208/30/20120830173930_PBfJE.jpeg";
    //1024x768
    private String url2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574317078503&di=568c2051ddf3f4b81dae4f094c0667dc&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fblog%2F201312%2F04%2F20131204184148_hhXUT.jpeg";
    //550x611
    private String url3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574318416026&di=3a55a90f0119c6e7db08c32ba1da79eb&imgtype=0&src=http%3A%2F%2Fi1.sinaimg.cn%2Fent%2Fd%2F2008-06-04%2FU105P28T3D2048907F326DT20080604225106.jpg";
    private String url4;
//    private String url_gif = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591179176330&di=f1ba185206fce2a4670cfac246a11799&imgtype=0&src=http%3A%2F%2Fhiphotos.baidu.com%2Ffeed%2Fpic%2Fitem%2F79f0f736afc3793166ed31a5e0c4b74542a911b0.jpg";
    private String url_gif = "http://img.huofar.com/data/jiankangrenwu/shizi.gif";

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, FrescoActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void setStatistical() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_fresco);
        ButterKnife.bind(this);


        showIv01();
    }

    //加载gif不要用高版本的Fresco
    private void showIv01() {
        DraweeController draweeController =
                Fresco.newDraweeControllerBuilder()
                        .setUri(url_gif)
                        .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
                        .build();

        gifSdv.setController(draweeController);

    }

}
