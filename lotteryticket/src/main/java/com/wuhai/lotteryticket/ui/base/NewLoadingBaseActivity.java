package com.wuhai.lotteryticket.ui.base;

import android.os.Bundle;

import com.wuhai.lotteryticket.widget.dialog.LoadingDialog;


/**
 * new loading BaseActivity
 */
public abstract class NewLoadingBaseActivity extends BaseActionBarActivity {

    public LoadingDialog mLoading;//new loading dialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoading = new LoadingDialog(this);
    }

    /**
     * showNewLoading
     * ※跟 基类里的showLoading() 区别，用基类showLoading()，成功后必须调showSuccessLayout()
     */
    public void showNewLoading(){
        if (mLoading != null && !mLoading.isShowing()) {
            try {
                mLoading.show();
            } catch (Exception e) {
            }

        }
    }

    /**
     * dimssNewLoading
     */
    public void dimssNewLoading() {
        if (mLoading != null && mLoading.isShowing()) {
            try {
                mLoading.dismiss();
            } catch (Exception e) {

            }

        }
    }

    @Override
    public void onDestroy() {
        dimssNewLoading();
        mLoading = null;
        super.onDestroy();
    }

    /**
     *
     * @param msg
     */
//    public void showErrorMsg(String msg) {
//        DSDialog dialog = new DSDialog.Builder(this).setType(DSDialog.TYPE_BOTTOM_ERROR).setTitle("错误提示")
//                .setMessage(msg).setPositiveButtonText("知道了").setButtonClickListener(new DSDialog.ActionClickListener() {
//                    @Override
//                    public void onClick(DSDialog dialog, int which, Object data) {
//                        dialog.dismiss();
//                    }
//                }).setIsBackCancelable(false).create();
//
//        if (!this.isFinishing()) {
//            dialog.show();
//        }
//    }
}
