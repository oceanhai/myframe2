package com.wuhai.share;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;

/**
 * Created by Zhenggy on 2017/3/13.
 */

public class QddShareListener implements PlatformActionListener, ShareContentCustomizeCallback {

    private QddShareCallback mCallback;

    public QddShareListener(QddShareCallback mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        mCallback.onComplete(platform.getName());
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        mCallback.onError(platform.getName());
    }

    @Override
    public void onCancel(Platform platform, int i) {
        mCallback.onCancel(platform.getName());
    }

    public QddShareCallback getmCallback() {
        return mCallback;
    }

    public void setmCallback(QddShareCallback mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    public void onShare(Platform platform, ShareParams paramsToShare) {
        mCallback.onShare(platform, paramsToShare);
    }
}
