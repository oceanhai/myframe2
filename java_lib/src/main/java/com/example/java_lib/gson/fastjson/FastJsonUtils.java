package com.example.java_lib.gson.fastjson;



import static com.alibaba.fastjson.JSON.parseObject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FastJsonUtils {
    /**
     * 对象转化为json fastjson 使用方式
     *
     * @return
     */
    public static String objectToJson(Object object) {
        if (object == null) {
            return "";
        }
        try {
            return JSON.toJSONString(object);
        } catch (JSONException e) {
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * json转化为对象  fastjson 使用方式
     *
     * @return
     */
    public static <T> T jsonToObject(String jsonData, Class<T> clazz) {
        T t = null;
        if (jsonData.isEmpty()) {
            return null;
        }
        try {
            t = parseObject(jsonData, clazz);
        } catch (Exception e) {
        }
        return t;
    }

    public static JSONObject jsonToObject(String jsonData) {
        JSONObject t = null;
        if (jsonData.isEmpty()) {
            return null;
        }
        try {
            t = parseObject(jsonData);
        } catch (Exception e) {
        }
        return t;
    }

    /**
     * json转化为List<Person>或List<String>数据  fastjson 使用方式
     *
     * @param jsonData
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToArray(String jsonData, Class<T> clazz) {
        List<T> list = null;
        try {
            list = JSON.parseArray(jsonData, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * json转化为List  fastjson 使用方式
     */
    public static List jsonToList(String jsonData) {
        if (jsonData.isEmpty()) {
            return null;
        }
        List arrayList = null;
        try {
            arrayList = parseObject(jsonData, new TypeReference<ArrayList>() {
            });
        } catch (Exception e) {
        }
        return arrayList;
    }


    /**
     * json转化为Map  fastjson 使用方式
     */
    public static Map jsonToMap(String jsonData) {
        if (jsonData.isEmpty()) {
            return null;
        }
        Map map = null;
        try {
            map = parseObject(jsonData, new TypeReference<Map>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
