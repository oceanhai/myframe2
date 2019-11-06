package com.wuhai.retrofit.net;


import com.wuhai.retrofit.bean.UpLoadFile;
import com.wuhai.retrofit.utils.NetworkUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Response;


/**网络工具
 * Created by wangC on 2017/1/12.
 */

public class HttpManager {



    //单例
    private static HttpManager mHttpManager;



    public static  void init(OkHttpClient okHttpClient){
        if (mHttpManager ==null) {
            synchronized (HttpManager.class) {
                if (mHttpManager == null) {
                    mHttpManager = new HttpManager(okHttpClient);
                }
            }
        }

    }

    public static HttpManager getInstance(){
        if(mHttpManager==null) {
            throw new RuntimeException(" 获取HttpManger 失败，请先调用 HttpManager 的init方法");
        }
        return mHttpManager;
    }

    private HttpManager(OkHttpClient   okHttpClient){
         OkHttpUtils.initClient(okHttpClient);
    }






   /* //返回 OkHttpClient 进行配置
    private  OkHttpClient getOkHttpClient() {
        if(okHttpClient!=null){
            return  okHttpClient;
        }
        return  null;
    }*/


    /**
     * doGetAsync 异步get请求方法
     * @param url  请求地址
     * @param parMap 请求参数集合
     * @param callBack 回调
     */
    public void doGetAsync(String url, Map<String, String> parMap  , LctCallBackInterFace callBack, Object tag){
                if(NetworkUtils.isNetworkAvailable()) {
                    SimpleGetOrGetCallBack simpleCallBack=new SimpleGetOrGetCallBack();
                    simpleCallBack.setRequestCallBack(callBack);
                    OkHttpUtils.get().url(url).params(parMap).tag(tag).build().execute(simpleCallBack);
                }
                else{
                    callBack.lct_onNoNet();
                }
            }


     /**
        * doGetAsync 同步get请求方法
         * @param url  请求地址
         * @param parMap 请求参数集合
         * @param callBack 回调
     */
     public void doGetExcute(String url, Map<String, String> parMap  , LctCallBackInterFace callBack, Object tag) {
            if(NetworkUtils.isNetworkAvailable()) {
                SimpleGetOrGetCallBack simpleCallBack=new SimpleGetOrGetCallBack();
                simpleCallBack.setRequestCallBack(callBack);
                try {
                   Response response=  OkHttpUtils.get().url(url).params(parMap).tag(tag).build().execute( );
                    if(response ==null){
                        callBack.lct_onFailed("",new RuntimeException("获取失败"));
                    }
                    else{
                        callBack.lct_onSuccess(response.body().string());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                callBack.lct_onNoNet();
            }
        }




    /**
     *Post 异步Post请求方法
     * @param url  请求地址
     * @param parMap 请求参数集合
     * @param callBack 回调
     */
    public void doPostAsync(String url, Map<String, String> parMap  , LctCallBackInterFace callBack, Object tag){
        if(NetworkUtils.isNetworkAvailable()) {
            SimpleGetOrGetCallBack simpleCallBack=new SimpleGetOrGetCallBack();
            simpleCallBack.setRequestCallBack(callBack);
            OkHttpUtils.post().url(url).params(parMap).tag(tag).build().execute(simpleCallBack);
        }
        else{
            callBack.lct_onNoNet();
        }
    }



     /**
     *Post 同步Post请求方法
     * @param url  请求地址
     * @param parMap 请求参数集合
     * @param callBack 回调
     */
    public void doPostExcute(String url, Map<String, String> parMap  , LctCallBackInterFace callBack, Object tag){
        if(NetworkUtils.isNetworkAvailable()) {
            SimpleGetOrGetCallBack simpleCallBack=new SimpleGetOrGetCallBack();
            simpleCallBack.setRequestCallBack(callBack);
            try {
                Response response=  OkHttpUtils.post().url(url).params(parMap).tag(tag).build().execute( );
                if(response ==null){
                    callBack.lct_onFailed("",new RuntimeException("获取失败"));
                }
                else{
                    callBack.lct_onSuccess(response.body().string());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            callBack.lct_onNoNet();
        }
    }


    /*
     *Post 同步Post请求方法
     * @param url  请求地址
     * @param parMap 请求参数集合
     * @param callBack 回调
     */
    public void doPostUpLoadFile(String url, String fileName, Map<String, String> parMap , Map<String, File> fileMap, LctCallBackInterFace callBack, Object tag){
        if(NetworkUtils.isNetworkAvailable()) {

         PostFormBuilder builder= OkHttpUtils.post() ;

         builder.params(parMap);//不用判断空，已经写了
         if(fileMap!=null && !fileMap.isEmpty()){
            Iterator it=fileMap.keySet().iterator();
             while (it.hasNext()){
                String key= (String) it.next();
                File f =fileMap.get(key);
                 builder.addFile(key,fileName,f );
             }
         }
         SimpleGetOrGetCallBack simpleCallBack=new SimpleGetOrGetCallBack();
         simpleCallBack.setRequestCallBack(callBack);
         builder  .url(url) .build() .execute(  simpleCallBack );
        }
        else{
            callBack.lct_onNoNet();
        }


    }




    /*
    *Post 同步Post请求方法
    * @param url  请求地址
    * @param parMap 请求参数集合
    * @param callBack 回调
    */
    public void doPostUpLoadFileMany(String url, Map<String, String> parMap , Map<String, UpLoadFile> fileMap, LctCallBackInterFace callBack, Object tag){
        if(NetworkUtils.isNetworkAvailable()) {

            PostFormBuilder builder= OkHttpUtils.post() ;

            builder.params(parMap);//不用判断空，已经写了
            if(fileMap!=null && !fileMap.isEmpty()){
                Iterator it=fileMap.keySet().iterator();
                while (it.hasNext()){
                    String key= (String) it.next();

                    UpLoadFile f =fileMap.get(key);
                    builder.addFile(key,f.getFileName(),f.getFile() );
                }
            }
            SimpleGetOrGetCallBack simpleCallBack=new SimpleGetOrGetCallBack();
            simpleCallBack.setRequestCallBack(callBack);
            builder  .url(url) .build() .execute(  simpleCallBack );
        }
        else{
            callBack.lct_onNoNet();
        }


    }






    /**
     * 取消一个请求
     * @param tag
     */
    public   void cancelTag(Object tag){
        OkHttpUtils.getInstance().cancelTag(tag);
    }




    /**
     *
     * @param path
     * @param fileName
     * @param url
     */
    public void doDownFile(String path, String fileName, String url, LctCallBackInterFace requestCallBack){

        SimpleDownLoadCallBack simpleDownLoadCallBack =new SimpleDownLoadCallBack(path , fileName);
        simpleDownLoadCallBack.setRequestCallBack(requestCallBack);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute( simpleDownLoadCallBack);



    }




}
