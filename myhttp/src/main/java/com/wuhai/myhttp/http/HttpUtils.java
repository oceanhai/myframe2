package com.wuhai.myhttp.http;

public class HttpUtils {

    /**
     *
     * T 输入的bean 请求参数
     * M 输出的bean 用户
     *
     * @param url
     * @param requestParams
     * @param response
     * @param iDataListener
     */
    public static<T,M> void sendJsonRequest(String url, T requestParams,
                                       Class<M> response, IDataListener iDataListener){
        IHttpRequest iHttpRequest = new JsonHttpRequest();
        IHttpListener iHttpListener = new JsonHttpListener<>(response, iDataListener);
        HttpTask httpTask = new HttpTask(url,requestParams,iHttpRequest,iHttpListener);
        ThreedManager.getInstance().addTask(httpTask);
    }

    //可扩展
//    public static void sendXMLRequest();
//    public static void sendImageRequest();

}
