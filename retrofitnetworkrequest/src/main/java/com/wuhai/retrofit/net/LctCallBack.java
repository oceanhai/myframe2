package com.wuhai.retrofit.net;

import android.app.Dialog;

import java.io.File;

/**
 * Created by wangC on 2017/1/19.
 */

public abstract class LctCallBack implements LctCallBackInterFace {

    //等待的窗口
    private Dialog mLoadingDialog = null;
    /**
     * 关闭等待的dialog
     *
     *
     */
    private void disMissLoading() {
        try {
            if ((this.mLoadingDialog != null) && this.mLoadingDialog.isShowing()) {
                this.mLoadingDialog.dismiss();
            }
        } catch (final IllegalArgumentException e) {
        } catch (final Exception e) {
        } finally {
        }
    }

    /**
     * 显示等待的dialog
     *
     */
    public void showLoadingDialog() {
        if (mLoadingDialog == null  ) {
            mLoadingDialog = lct_getLoading();
            if (mLoadingDialog != null && !mLoadingDialog.isShowing()) {
                mLoadingDialog.show();
            }
        }
    }




    /**
     * 网络请求之前
     */
    public void lct_before(String msg){
        showLoadingDialog();
    }

    /**
     * 网络请求之后
     */
    public void lct_after(String msg){
        disMissLoading();
    }


    @Override
    public void lct_inProccess(float num, long total) {

    }

    @Override
    public void lct_downFileResponse(File file, String json) {

    }
}
