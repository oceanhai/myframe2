package com.wuhai.myframe2.contract.base;

import android.graphics.drawable.Drawable;

/**
 * view 基类
 */
public interface IBaseViewInterFace extends INormalViewInterFace {

////    Dialog getLoadingDialog();

    /**
     * showSuccessLayout
     */
    void showSuccessLayout();

    /**
     * failed msg
     * @param message
     */
    void showFailedMessage(String message);

    void showEmptyMessage(String message);

    void showEmptyMessage(Drawable emptyImage, String message);

    void showEmptyMessage(Drawable emptyImage, int textColor, float textSize, String message);
//
//    void hideAssistedMessage();
//
//    void showErrorMsg(String msg);
//
    void showToast(String msg);

//    void showToast(int resId);
//
//    void showErrorMsgAndFinish(String errorMsg);
}
