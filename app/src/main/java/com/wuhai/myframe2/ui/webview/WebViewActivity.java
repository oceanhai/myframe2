package com.wuhai.myframe2.ui.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;

import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcWebviewBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：空ac
 */
public class WebViewActivity extends BaseActivity {

    private AcWebviewBinding binding;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, WebViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);

//        setContentView(R.layout.ac_null);
        parseIntent();
        init();
        setListener();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if(intent != null){

        }
    }

    @Override
    public void setStatistical() {

    }

    @SuppressLint("JavascriptInterface")
    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.ac_webview);

        WebSettings webSettings = binding.myWebView.getSettings();

        String cachePath = getContext().getFilesDir().getAbsolutePath()+"cache/";
        webSettings.setAppCachePath(cachePath);  // 1. 设置缓存路径
        webSettings.setAppCacheMaxSize(20*1024*1024);  // 2. 设置缓存大小
        webSettings.setAppCacheEnabled(true); // 3. 开启Application Cache存储机制

        webSettings.setJavaScriptEnabled(true);//打开js功能,使浏览器能使用脚本功能
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);
        webSettings.setDomStorageEnabled(true);// 开启DOM storage
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        //sd卡上的html
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);//允许混合内容，5.0之后的api
        }

        /**设置网页字体不跟随系统字体发生改变*/
        binding.myWebView.getSettings().setTextZoom(100);

        binding.myWebView.setHorizontalScrollBarEnabled(false);
        binding.myWebView.setHorizontalScrollbarOverlay(false);
        binding.myWebView.setVerticalScrollBarEnabled(false);
        binding.myWebView.setVerticalScrollbarOverlay(false);
//        binding.myWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE); //WebSettings.LOAD_DEFAULT
        binding.myWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        //根据官方说明，Web SQL Database存储机制不再推荐使用（不再维护）   取而代之的是 IndexedDB缓存机制
        String cacheDirPath = getContext().getFilesDir().getAbsolutePath()+"cache/";
        webSettings.setDatabasePath(cacheDirPath);// 设置缓存路径
        webSettings.setDatabaseEnabled(true); // 开启 数据库存储机制

        binding.myWebView.setOnTouchScreenListener(new MyWebView.OnTouchScreenListener() {
            @Override
            public void onTouchScreen() {
                Log.e(TAG, "onTouchScreen");
            }

            @Override
            public void onReleaseScreen() {
                Log.e(TAG, "onReleaseScreen");
            }
        });
        //TODO 这里其实压根监听不到点击事件
//        binding.myWebView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e(TAG, "webView onClick");
//            }
//        });

        binding.myWebView.addJavascriptInterface(this, "myframe2");
//        binding.myWebView.addJavascriptInterface(this, "qjj");

//        binding.myWebView.loadUrl("https://www.baidu.com/");
        binding.myWebView.loadUrl("file:///android_asset/perm");
//        binding.myWebView.loadUrl("file:///android_asset/ceshi.html");
    }

    private void setListener() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        binding.myWebView.clearCache(true);
        binding.myWebView.clearHistory();
    }

    @JavascriptInterface
    public void changeWindowFlag(boolean change) {
        Log.e(TAG, "changeWindowFlag change="+change);
        if(change){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
    }

    /**
     * 登录
     */
    @JavascriptInterface
    public void login() {
        Log.e(TAG, "login()");
    }


    /**
     * 获取token
     * @return
     */
    @JavascriptInterface
    public String getToken() {
        Log.e(TAG, "getToken = ");
        return "123";
    }
}
