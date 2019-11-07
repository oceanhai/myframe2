package com.wuhai.myframe2.network;

import android.text.TextUtils;

import com.wuhai.retrofit.retrofit.RequestHandler;
import com.wuhai.retrofit.utils.LogUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * 可以在这里拼接公用参数  做统一处理
 * Created by fanchang on 2017/1/7.
 */
public class MyRequestHandler implements RequestHandler {

    /**
     * post通用参数列表
     */
    public static Map<String, String> postParamsMap = new HashMap<>();
    /**
     * get通用参数列表
     */
    public static Map<String, String> getParamsMap = new HashMap<>();

    @Override
    public Request onBeforeRequest(Request request, Interceptor.Chain chain) {
        Request oldRequest = request;
        Request.Builder requestBuilder = oldRequest.newBuilder();
        //参与签名的map
        TreeMap<String, String> singMap = new TreeMap<>();
        //默认url上附加的get参数map
        Map<String, String> getMap = new HashMap<>();
        //默认附加的post参数map
        Map<String, String> postMap = new HashMap<>();
        if (oldRequest.url().querySize() > 0) {
            //附加的get参数
            getMap.putAll(strToMap(oldRequest.url().query()));
            singMap.putAll(getMap);
        }
        if (canInjectIntoBody(oldRequest)) {
            //附加的post参数
            postMap.putAll(strToMap(bodyToString(oldRequest.body())));
            singMap.putAll(postMap);
        }
        //所有参数参与签名
        singMap.putAll(getParamsMap);
        singMap.putAll(postParamsMap);
        //最新的token accessToken
//        singMap.put(Common.Constant.TOKEN, AccountManager.getInstance().getToken());
//        singMap.put(Common.Constant.ACCESS_TOKEN, SettingManager.getInstance().getAccessToken());
//        double[] locationLatLon = SettingManager.getInstance().getLocationLatLon();
//        singMap.put(Common.Constant.LAT, locationLatLon[0] + "");
//        singMap.put(Common.Constant.LNG, locationLatLon[1] + "");
//        singMap.put(Common.Constant.ADDRESS, SettingManager.getInstance().getLocationAddress());
//        String sing = getSingString(singMap);
//        for (Map.Entry<String, String> entry : getMap.entrySet()) {
//            singMap.remove(entry.getKey());
//        }
//        for (Map.Entry<String, String> entry : postMap.entrySet()) {
//            singMap.remove(entry.getKey());
//        }
        //add get params
        HttpUrl.Builder builder = oldRequest.url().newBuilder();
        if (singMap.size() > 0) {
            Iterator iterator = singMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                builder.addQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
//            builder.addQueryParameter(Common.Constant.SIGN, sing);
            HttpUrl url = builder.build();
            requestBuilder = requestBuilder.url(url);
            oldRequest = requestBuilder.build();
        }
        LogUtil.e("okhttp get方式 url:" + oldRequest.url());
        return oldRequest;
    }

    @Override
    public Response onAfterRequest(Response response, Interceptor.Chain chain) {
//        try {
//            ResponseBody body = response.body();
//            BufferedSource source = body.source();
//            source.request(Long.MAX_VALUE); // Buffer the entire body.
//            Buffer buffer = source.buffer();
//            Charset charset = Charset.defaultCharset();
//            MediaType contentType = body.contentType();
//            if (contentType != null) {
//                charset = contentType.charset(charset);
//            }
//
//            String bodyString = buffer.clone().readString(charset);
//            LogUtil.i("");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return response;
    }

    private boolean canInjectIntoBody(Request request) {
        if (request == null) {
            return false;
        }
        if (!TextUtils.equals(request.method(), "POST")) {
            return false;
        }
        RequestBody body = request.body();
        if (body == null) {
            return false;
        }
        MediaType mediaType = body.contentType();
        if (mediaType == null) {
            return false;
        }
        return TextUtils.equals(mediaType.subtype(), "x-www-form-urlencoded");
    }

    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获得签名
     *
     * @param newMap
     * @return
     */
//    public static String getSingString(TreeMap<String, String> newMap) {
//        //剔除sign
//        newMap.remove(Common.Constant.SIGN);
//        String sing = "";
//        Set<String> set = newMap.keySet();
//        Iterator it = set.iterator();
//        while (it.hasNext()) {
//            String key = (String) it.next();
//            String value = newMap.get(key);
//            sing += key + value;
//        }
//        sing += "c02e7a17b7f00c29cpl3gey1yfaep93z";
//        LogUtil.e("最终的签名串：" + sing);
//        return MD5(sing).toUpperCase();
//    }

    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    /**
     * url解析成map
     *
     * @param str
     * @return
     */
    public static Map<String, String> strToMap(String str) {
        Map<String, String> oldMap = new HashMap<>();
        if (!TextUtils.isEmpty(str)) {
            String[] mapStr = str.split("&");
            if (mapStr.length > 0) {
                for (int i = 0; i < mapStr.length; i++) {
                    String[] value = mapStr[i].split("=");
                    if (value.length == 2) {
                        String valueStr = "";
                        try {
                            valueStr = URLDecoder.decode(value[1], "utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        oldMap.put(value[0], valueStr);
                    } else if (value.length == 1) {
                        if (!TextUtils.isEmpty(value[0])) {
                            oldMap.put(value[0], "");
                        }
                    }
                }
            }
        }
        return oldMap;
    }

    public static class Builder {

        private MyRequestHandler myRequestHandler;

        public Builder() {
            myRequestHandler = new MyRequestHandler();
        }

        public Builder addPostParam(String key, String value) {
            postParamsMap.put(key, value);
            return this;
        }

        public Builder addPostParamsMap(Map<String, String> paramsMap) {
            postParamsMap.putAll(paramsMap);
            return this;
        }

        public Builder addGetParam(String key, String value) {
            getParamsMap.put(key, value);
            return this;
        }

        public Builder addGetParamsMap(Map<String, String> queryParamsMap) {
            getParamsMap.putAll(queryParamsMap);
            return this;
        }

        public MyRequestHandler build() {
            return myRequestHandler;
        }
    }
}
