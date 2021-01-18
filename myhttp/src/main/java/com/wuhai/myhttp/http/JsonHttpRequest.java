package com.wuhai.myhttp.http;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonHttpRequest implements IHttpRequest{

    private String url;
    private byte[] params;
    private IHttpListener iHttpListener;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setParams(byte[] params) {
        this.params = params;
    }

    @Override
    public void setListener(IHttpListener iHttpListener) {
        this.iHttpListener = iHttpListener;
    }

    private HttpURLConnection connection;

    /**
     * 真实的网络 操作就在这里执行
     * urlconnection socket okhttp ....
     * 只要能支持到inputStream的方式都能用
     *
     * public static void HttpURLConnection.setFollowRedirects(boolean followRedirects)
     * public void HttpURLConnection.setInstanceFollowRedirects(boolean followRedirects)
     * 前者设置所有的http连接是否自动处理重定向；
     * 后者设置本次连接是否自动处理重定向。
     * 设置成true，系统自动处理重定向；设置成false，则需要自己从http reply中分析新的url 自己重新连接。
     *
     */
    @Override
    public void execute() {
        try {
            URL url = new URL(this.url);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(6000);//链接超时时间
            connection.setUseCaches(false);//不使用缓存
            connection.setInstanceFollowRedirects(true);//设置本次连接是否自动处理重定向
            connection.setReadTimeout(3000);//响应超时的时间
            connection.setDoInput(true);//设置这个链接是否可以写入数据
            connection.setDoOutput(true);//设置这个链接是否可以输出数据
            connection.setRequestMethod("POST");
            connection.connect();
            String body = "userName=zhangsan&password=123456";
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            writer.write(body);
            writer.close();
            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream = connection.getInputStream();
//                String result = is2String(inputStream);//将流转换为字符串。
//                Log.d("kwwl","result============="+result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
