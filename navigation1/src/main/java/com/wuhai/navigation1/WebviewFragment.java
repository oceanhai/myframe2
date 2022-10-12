package com.wuhai.navigation1;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wuhai.navigation1.utils.BankLog;
import com.wuhai.navigation1.webview.BaseWebview;
import com.wuhai.navigation1.webview.BaseWebviewListerner;
import com.wuhai.navigation1.webview.WebviewStatus;

/**
 * 作者 wuhai
 *
 * 创建日期 2018/7/31 17:40
 *
 * 描述： webview fr
 *      贷款超市页
 */

public class WebviewFragment extends MainBaseFragment implements BaseWebviewListerner {

    public static final String EXTRA_URL = "url";
    public static final String EXTRA_COLOR = "color";

    private String mURL;
    private String mColor;

    private BaseWebview mBaseWebview;

    private FrameLayout fr_bg;
    /**
     *
     * @param url
     * @return
     */
    public static WebviewFragment newInstance(String url, String color){
        WebviewFragment fragment = new WebviewFragment();
        Bundle bubdle = new Bundle();
        bubdle.putString(EXTRA_URL, url);
        bubdle.putString(EXTRA_COLOR, color);
        fragment.setArguments(bubdle);
        return fragment;
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fr_web, container, false);
        getBundle();
        initView(inflater, view);
        return view;
    }

    private void getBundle() {
        Bundle bundle = getArguments();
        if(bundle != null){
            mURL = bundle.getString(EXTRA_URL);
            mColor = bundle.getString(EXTRA_COLOR);
        }

    }

    private void initView(LayoutInflater mInflater, View view) {
//        mBaseWebview = view.findViewById(R.id.basewebview);
//        mBaseWebview.setListerner(this);//url监听、状态监听
//        mBaseWebview.setJSInterface(this,"QianDaodaoClient");//js交互
//
//        if(!TextUtils.isEmpty(mURL)){
//            mBaseWebview.loadUrl(mURL);
//        }

        fr_bg = view.findViewById(R.id.fr_bg);
        fr_bg.setBackgroundColor(Color.parseColor(mColor));

        TextView textView = view.findViewById(R.id.tv01);
        textView.setText(mURL);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void reloadData() {
        super.reloadData();
        if(mBaseWebview != null){
            mBaseWebview.reload();
        }
    }

    @Override
    public void onStatusChanged(WebviewStatus status, String url) {
        if(status == WebviewStatus.WebviewDownloading){//loading
//            showLoading();
        }else if(status == WebviewStatus.WebviewLoadFailed){//展示失败布局
            hideLoading(false);
        }else if(status == WebviewStatus.WebviewLoadSucessed){//success
            hideLoading(true);
        }
    }

    @Override
    public boolean onUrlChanged(String url) {
        BankLog.e("onUrlChanged url="+url);
        return false;
    }

    @Override
    public void onTitleChanged(String title) {
//        if(!TextUtils.isEmpty(title)){
//            setTitle(title);
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mBaseWebview != null){
            mBaseWebview.clear();
        }
    }

    /**
     *
     * @param key   友盟事件id
     * @param url   下级页面url
     */
    @JavascriptInterface
    public void jump(String key, String url) {

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("wh", "onResume() " + mURL);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("wh", "onPause() " + mURL);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("wh", "onHiddenChanged hidden="+hidden+", " + mURL);
    }
}
