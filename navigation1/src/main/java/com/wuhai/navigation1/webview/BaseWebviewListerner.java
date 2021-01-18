package com.wuhai.navigation1.webview;

public interface BaseWebviewListerner {

    /**
     * webview 加载状态
     * @param status
     * @param url
     */
    void onStatusChanged(WebviewStatus status, String url);

    /**
     * url 变更
     * @param url
     * @return
     */
    boolean onUrlChanged(String url);

    /**
     * 标题变更
     * @param title
     */
    void onTitleChanged(String title);
}
