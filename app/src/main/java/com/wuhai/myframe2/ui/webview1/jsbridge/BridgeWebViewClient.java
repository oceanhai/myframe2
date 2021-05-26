package com.wuhai.myframe2.ui.webview1.jsbridge;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebView;
import com.wuhai.myframe2.ui.webview1.X5LogUtils;
import com.wuhai.myframe2.ui.webview1.X5WebUtils;
import com.wuhai.myframe2.ui.webview1.X5WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class BridgeWebViewClient extends X5WebViewClient {

    /**
     * 构造方法
     *
     * @param webView 需要传进来webview
     * @param context 上下文
     *
     */
    private Context context;
    private BridgeWebView webView;
    public BridgeWebViewClient(BridgeWebView webView, Context context) {
        super(webView, context);
        this.context = context;
        this.webView = webView;
    }

    /**
     * 这个方法中可以做拦截
     * 主要的作用是处理各种通知和请求事件
     * 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
     *
     * @param view view
     * @param url  链接
     * @return 是否自己处理，true表示自己处理
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        X5LogUtils.i("-------shouldOverrideUrlLoading----1---" + url);
        //页面关闭后，直接返回，不要执行网络请求和js方法
        boolean activityAlive = X5WebUtils.isActivityAlive(context);
        if (!activityAlive) {
            return false;
        }
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        try {
            url = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 如果是返回数据
        if (url.startsWith(BridgeUtil.YY_RETURN_DATA)) {
            webView.handlerReturnData(url);
            return true;
        } else if (url.startsWith(BridgeUtil.YY_OVERRIDE_SCHEMA)) {
            webView.flushMessageQueue();
            return true;
        } else {
            if (this.onCustomShouldOverrideUrlLoading(url)) {
                return true;
            } else {
                return super.shouldOverrideUrlLoading(view, url);
            }
        }
    }

    protected boolean onCustomShouldOverrideUrlLoading(String url) {
        return false;
    }


    /**
     * 增加shouldOverrideUrlLoading在api>=24时
     * 主要的作用是处理各种通知和请求事件
     * 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
     *
     * @param view    view
     * @param request request
     * @return
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        X5LogUtils.i("-------shouldOverrideUrlLoading----2---" + request.getUrl().toString());
        //页面关闭后，直接返回，不要执行网络请求和js方法
        boolean activityAlive = X5WebUtils.isActivityAlive(context);
        if (!activityAlive) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String url = request.getUrl().toString();
            if (TextUtils.isEmpty(url)) {
                return false;
            }
            try {
                url = URLDecoder.decode(url, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            //如果是返回数据
            if (url.startsWith(BridgeUtil.YY_RETURN_DATA)) {
                webView.handlerReturnData(url);
                return true;
            } else if (url.startsWith(BridgeUtil.YY_OVERRIDE_SCHEMA)) {
                webView.flushMessageQueue();
                return true;
            } else {
                if (this.onCustomShouldOverrideUrlLoading(url)) {
                    return true;
                } else {
                    return super.shouldOverrideUrlLoading(view, request);
                }
            }
        } else {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }


    /**
     * 当页面加载完成会调用该方法
     *
     * @param view view
     * @param url  url链接
     */
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        //这个时候添加js注入方法
        //WebViewJavascriptBridge.js
        BridgeUtil.webViewLoadLocalJs(view, BridgeWebView.TO_LOAD_JS);
        if (webView.getStartupMessage() != null) {
            for (Message m : webView.getStartupMessage()) {
                //分发message 必须在主线程才分发成功
                webView.dispatchMessage(m);
            }
            webView.setStartupMessage(null);
        }

        //在html标签加载完成之后在加载图片内容
        view.getSettings().setBlockNetworkImage(false);
    }
}
