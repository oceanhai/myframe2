package com.wuhai.share;

import android.content.Context;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Zhenggy on 2017/2/21.
 *
 * 第三方分享
 */

public class QddShareHelper {

    /**
     * 初始化方法，分享前调用，推荐在Activity的onCreate()方法中调用
     *
     * @param context 当前上下文对象
     */
    public static void initShare(Context context) {
        ShareSDK.initSDK(context);
    }

    /**
     * 分享时调用，调用此方法前应先调用初始化方法。
     *
     * @param context 当前上下文对象
     * @param model 分享内容实体类
     */
    public static void showShare(Context context, QddShareModel model, QddShareCallback callback) {
        OnekeyShare oks = new OnekeyShare();
        share(context, oks, model, callback);

    }

    public static void showShare(Context context, String platform, QddShareModel model,
            QddShareCallback callback) {
        OnekeyShare oks = new OnekeyShare();
        oks.setPlatform(platform);
        share(context, oks, model, callback);
    }

    private static void share(Context context, OnekeyShare oks, QddShareModel model,
                              QddShareCallback callback) {

        QddShareListener mShareListener = new QddShareListener(callback);
        oks.disableSSOWhenAuthorize();
        oks.setTitle(model.getTitle());
        oks.setTitleUrl(model.getUrl());
        oks.setText(model.getContentText());
        oks.setImagePath(model.getImageUrl());
        oks.setImageUrl(model.getImageUrl());
        oks.setUrl(model.getUrl());
        oks.setSite(model.getUrl());
        oks.setShareContentCustomizeCallback(mShareListener);
        oks.setSiteUrl(model.getUrl());
        oks.setCallback(mShareListener);
        oks.show(context);
    }
}
