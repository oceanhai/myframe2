package com.wuhai.share;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;

/**
 * Created by Zhenggy on 2017/3/13.
 */

public interface QddShareCallback {

    void onComplete(String platformName);

    void onError(String platformName);

    void onCancel(String platformName);

    void onShare(Platform platform, ShareParams paramsToShare);
}
