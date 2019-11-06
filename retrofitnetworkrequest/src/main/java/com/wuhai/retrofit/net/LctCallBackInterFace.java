package com.wuhai.retrofit.net;

import android.app.Dialog;

import java.io.File;

/**
 * 所有请求的回调
 * Created by wangC on 2017/1/17.
 */

public interface LctCallBackInterFace {




    void lct_onSuccess(String json);

    void  lct_onFailed(String msg, Exception e);

    void  lct_onNoNet();

    Dialog lct_getLoading();

    void lct_inProccess(float num, long total);

    void lct_after(String msg);

    void lct_before(String msg);

    void lct_downFileResponse(File file, String json);
}
