package com.wuhai.demo.lotteryticketkotlin.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;

import com.wuhai.demo.lotteryticketkotlin.R;


public class LoadingDialog extends Dialog {

    private static final String TAG = "LoadingDialog";

    private ImageView imageView;
    private AnimationDrawable animationDrawable;

    public LoadingDialog(Context context) {
        super(context, R.style.CommonDialogLoading);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        getWindow().getAttributes().gravity = Gravity.CENTER;

        imageView = (ImageView) findViewById(R.id.loading_dialog_progress);
        animationDrawable = (AnimationDrawable) imageView.getBackground();

        setCancelable(true);
        Log.e(TAG, "onCreate");
    }

    @Override
    public void show() {
        Log.e(TAG, "show");
        super.show();
        if(animationDrawable != null){
            animationDrawable.start();
        }
    }

    @Override
    public void dismiss() {
        Log.e(TAG, "dismiss");
        super.dismiss();
        if(animationDrawable != null){
            animationDrawable.stop();
        }
    }
}
