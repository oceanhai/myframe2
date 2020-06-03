package com.wuhai.myframe2.ui.glide;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity;
import com.wuhai.retrofit.utils.LogUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 wh
 * <p>
 * 创建日期 2019/11/20 17:47
 * <p>
 * 描述：glide
 */
public class GlideActivity extends BaseActivity {


    @BindView(R.id.image01)
    ImageView image01;
    @BindView(R.id.image02)
    ImageView image02;
    @BindView(R.id.image03)
    ImageView image03;
    @BindView(R.id.image04)
    ImageView image04;
    @BindView(R.id.image05)
    ImageView image05;
    @BindView(R.id.image06)
    ImageView image06;
    @BindView(R.id.image07)
    ImageView image07;
    @BindView(R.id.image08)
    ImageView image08;
    @BindView(R.id.image09)
    ImageView image09;
    @BindView(R.id.image10)
    ImageView image10;
    @BindView(R.id.image11)
    ImageView image11;
    @BindView(R.id.image12)
    ImageView image12;
    @BindView(R.id.image13)
    ImageView image13;
    @BindView(R.id.image14)
    ImageView image14;
    @BindView(R.id.image15)
    ImageView image15;
    @BindView(R.id.image16)
    ImageView image16;
    @BindView(R.id.image17)
    ImageView image17;
    @BindView(R.id.image18)
    ImageView image18;
    @BindView(R.id.image19)
    ImageView image19;
    @BindView(R.id.image20)
    ImageView image20;
    @BindView(R.id.image21)
    ImageView image21;
    @BindView(R.id.image22)
    ImageView image22;
    @BindView(R.id.image23)
    ImageView image23;
    @BindView(R.id.image24)
    ImageView image24;
    @BindView(R.id.image25)
    ImageView image25;
    @BindView(R.id.image26)
    ImageView image26;
    @BindView(R.id.image27)
    ImageView image27;
    @BindView(R.id.image28)
    ImageView image28;
    @BindView(R.id.image29)
    ImageView image29;
    @BindView(R.id.image30)
    ImageView image30;


    //809x1000
    private String url = "https://b-ssl.duitang.com/uploads/item/201208/30/20120830173930_PBfJE.jpeg";
    //1024x768
    private String url2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574317078503&di=568c2051ddf3f4b81dae4f094c0667dc&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fblog%2F201312%2F04%2F20131204184148_hhXUT.jpeg";
    //550x611
    private String url3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574318416026&di=3a55a90f0119c6e7db08c32ba1da79eb&imgtype=0&src=http%3A%2F%2Fi1.sinaimg.cn%2Fent%2Fd%2F2008-06-04%2FU105P28T3D2048907F326DT20080604225106.jpg";
    private String url4;
    private String url_gif = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591179176330&di=f1ba185206fce2a4670cfac246a11799&imgtype=0&src=http%3A%2F%2Fhiphotos.baidu.com%2Ffeed%2Fpic%2Fitem%2F79f0f736afc3793166ed31a5e0c4b74542a911b0.jpg";

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, GlideActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void setStatistical() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_glide);
        ButterKnife.bind(this);


        showIv01();
        showIv02();
        showIv03();
        showIv04();
        showIv05();
//        showIv06();//没啥卵用
        showIv07();
        showIv08();
        showIv09();//※效果可以
    }

    private void showIv09() {
        /**
         * View淡入
         */
        Glide.with(this)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image21);

        /**
         * 圆
         */
        Glide.with(this)
                .load(url)
                .circleCrop()
                .into(image22);

    }

    private void showIv08() {
        /**
         * 禁用图形变换功能
         * 这个方法时全局的，导致其他地方的图片也不可进行图形变换了
         */
//        Glide.with(this)
//                .load(url)
//                .dontTransform()
//                .into(image17);

        /**
         * 简单的图形变换
         * centerCrop()方法 按照原始的长宽比充满全屏
         * fitCenter() 方法 对原图的中心区域进行裁剪对图片进行相关设置
         */
        Glide.with(this)
                .load(url3)
                .centerCrop()
                .into(image18);
        Glide.with(this)
                .load(url)
                .fitCenter()
                .into(image19);

        /**
         * override() 方法与 centerCrop() 方法配合使用
         */
        Glide.with(this)
                .load(url2)
                .override(500, 500)
                .centerCrop()
                .into(image20);

    }

    private void showIv07() {
        Glide.with(this)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Toast.makeText(GlideActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Toast.makeText(GlideActivity.this, "加载成功", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .into(image17);

    }

    private void showIv06() {
        //Glide 实现图片下载
        downloadImage();
        //使用下载完的图片的方式
        loadImage();
    }

    public void loadImage() {
        String url = "http://cn.bing.com/az/hprichbg/rb/TOAD_ZH-CN7336795473_1920x1080.jpg";
        Glide.with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image16);
    }

    public void downloadImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://cn.bing.com/az/hprichbg/rb/TOAD_ZH-CN7336795473_1920x1080.jpg";
                    final Context context = getApplicationContext();
                    FutureTarget<File> target = Glide.with(context)
                            .load(url)
                            .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
                    final File imageFile = target.get();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, imageFile.getPath(), Toast.LENGTH_LONG).show();
                            LogUtil.e(TAG, "path=" + imageFile.getPath());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showIv05() {
        //a、预加载代码
        Glide.with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .preload();

        //preload() 有两种重载
        // 1、带有参数的重载,参数作用是设置预加载的图片大小；
        //2、不带参数的表示加载的图片为原始尺寸；

        //b、使用预加载的图片
        Glide.with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image15);

    }

    //1、通过自己构造 target 可以获取到图片实例
    SimpleTarget<Drawable> simpleTarget = new SimpleTarget<Drawable>() {
        @Override
        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//            image01.setImageDrawable(resource);
        }
    };

    //2、将图片实例记载到指定的imageview上，也可以做其他的事情
    public void loadImage1() {
        String url = "http://cn.bing.com/az/hprichbg/rb/TOAD_ZH-CN7336795473_1920x1080.jpg";
        Glide.with(this)
                .load(url)
                .into(simpleTarget);
    }

    private void showIv04() {

        /**
         * 占位图
         */
        Glide.with(this)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)//占位图
                .into(image05);
        Glide.with(this).load(url)
                .placeholder(R.color.font_black_6)
                .into(image06);
        Glide.with(this)
                .load(url4)
                .placeholder(R.color.font_black_6)
                .fallback(R.color.font_black_7)//设计 fallback Drawable 的主要目的是允许用户指示 null 是否为可接受的正常情况。
                .into(image14);

        /**
         * 加载失败 放置占位符
         */
        Glide.with(this)
                .load(url2)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.color.font_black_6)//加载失败 放置占位符
                .diskCacheStrategy(DiskCacheStrategy.NONE)//关闭Glide的硬盘缓存机制
                .into(image07);

        /**
         * 加载指定格式的图片--指定为静止图片
         */
        Glide.with(this)
                .asBitmap()//只加载静态图片，如果是git图片则只加载第一帧。
                .load(url_gif)
                .placeholder(R.color.font_black_6)
                .error(R.color.font_black_6)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(image08);

        /**
         * 加载动态图片
         */
        Glide.with(this)
                .asGif()//加载动态图片，若现有图片为非gif图片，则直接加载错误占位图。
                .load(url)
                .placeholder(R.color.font_black_6)
                .error(R.color.font_black_6)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(image09);

        /**
         * 加载指定大小的图片
         */
        Glide.with(this)
                .load(url3)
                .placeholder(R.color.font_black_6)
                .error(R.color.font_black_6)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .override(1000, 1920)//指定图片大小
                .fitCenter()
                .into(image10);

        /**
         * 关闭框架的内存缓存机制
         */
        Glide.with(this)
                .load(url)
                .skipMemoryCache(true)  //传入参数为false时，则关闭内存缓存。
                .into(image11);

        /**
         * 关闭硬盘的缓存
         * DiskCacheStrategy.NONE： 表示不缓存任何内容。
         * DiskCacheStrategy.SOURCE： 表示只缓存原始图片。
         * DiskCacheStrategy.RESULT： 表示只缓存转换过后的图片（默认选项）。
         * DiskCacheStrategy.ALL ： 表示既缓存原始图片，也缓存转换过后的图片。
         */
        Glide.with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)     //关闭硬盘缓存操作
                .into(image12);

        /**
         * 当引用的 url 存在 token 时解决方法-->重写 Glide 的 GlideUrl 方法
         */
        Glide.with(this)
                .load(new MyGlideUrl(url))
                .into(image13);
    }

    private void showIv03() {

        LogUtil.e(TAG, "getExternalCacheDir()=" + getExternalCacheDir().getAbsolutePath());//android/data/
        LogUtil.e(TAG, "Environment.getExternalStorageDirectory()=" + Environment.getExternalStorageDirectory());
        // 加载本地图片
        // Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)判断sdcard是否存在
        // 记得读sdcard权限要打开
        File file = new File(Environment.getExternalStorageDirectory() + "/DownloadFile/2019051014541548683.jpg");
        Glide.with(this).load(file).into(image03);

        // 加载应用资源
        int resource = R.drawable.f0;
        Glide.with(this).load(resource).into(image04);

        // 加载二进制流
//        byte[] image = getImageBytes();
//        Glide.with(this).load(image).into(imageView);

        // 加载Uri对象
//        Uri imageUri = getImageUri();
//        Glide.with(this).load(imageUri).into(imageView);

    }

    /**
     * gif 图
     */
    private void showIv02() {
        Glide.with(this)
                .load(url_gif)
                .into(image02);
    }

    /**
     * 常用
     */
    private void showIv01() {
        Glide.with(this)
                .load(url)
                .into(image01);
    }

}
