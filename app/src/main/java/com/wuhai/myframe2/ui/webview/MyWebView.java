package com.wuhai.myframe2.ui.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * 作者 wh
 *
 * 创建日期 2020/9/2 11:07
 *
 * 描述：
 * https://blog.csdn.net/Jaden_hool/article/details/45481707
 */
public class MyWebView extends WebView {

    private OnTouchScreenListener onTouchScreenListener;

    public MyWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public MyWebView(Context context) {
        super(context);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (onTouchScreenListener != null)
                onTouchScreenListener.onTouchScreen();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (onTouchScreenListener != null)
                onTouchScreenListener.onReleaseScreen();
        }

        return super.onTouchEvent(event);
    }

    public interface OnTouchScreenListener {
        void onTouchScreen();

        void onReleaseScreen();
    }

    public void setOnTouchScreenListener(OnTouchScreenListener onTouchScreenListener) {
        this.onTouchScreenListener = onTouchScreenListener;
    }
}