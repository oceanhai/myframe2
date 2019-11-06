package com.wuhai.retrofit.net;

import android.app.Dialog;

/**
 * Created by wangC on 2017/1/20.
 */

public abstract class LctFileCallBack implements LctCallBackInterFace {

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

}
