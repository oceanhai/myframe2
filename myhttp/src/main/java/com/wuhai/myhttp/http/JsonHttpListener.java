package com.wuhai.myhttp.http;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonHttpListener<T> implements IHttpListener{

    //1.用户用什么样的javaBean来接收数据
    private Class<T> response;

    //2.需要把最后的结果以对象的方式交给用户
    private IDataListener iDataListener;


    public JsonHttpListener(Class<T> response, IDataListener iDataListener) {
        this.response = response;
        this.iDataListener = iDataListener;
    }

    //线程切换
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onSuccess(InputStream inputStream) {
        //inputStream 上面已经有网络回来的数据了
        String content = getContent(inputStream);
        final T responseObject = JSON.parseObject(content, response);
        //线程切换
        handler.post(new Runnable() {
            @Override
            public void run() {
                iDataListener.onSuccess(responseObject);
            }
        });

    }

    /**
     * InputStream 转换成 string 类型
     * @param inputStream
     * @return
     */
    private String getContent(InputStream inputStream) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    @Override
    public void onFailure() {

    }
}
