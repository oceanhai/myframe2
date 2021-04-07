package com.wuhai.demo.lotteryticketkotlin.utils

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import java.util.*

/**
 * Created by wuhai on 2017/2/18 0018 14:35.
 * 描述：Gson 工具类
 */
class GsonUtils private constructor() {

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    fun toJson(`object`: Any?): String? {
        var gsonString: String? = null
        if (gson != null) {
            gsonString = gson.toJson(`object`)
        }
        return gsonString
    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    fun <T> fromJson(gsonString: String?, cls: Class<T>?): T? {
        var t: T? = null
        if (gson != null) {
            t = gson.fromJson(gsonString, cls)
        }
        return t
    }

    /**
     * 转成list
     * 泛型在编译期类型被擦除导致报错
     * @param gsonString
     * @param cls
     * @return
     */
    fun <T> parseToList1(gsonString: String?, cls: Class<T>?): List<T>? {
        var list: List<T>? = null
        if (gson != null) {
            list = gson.fromJson(gsonString, object : TypeToken<List<T>?>() {}.type)
        }
        return list
    }

    /**
     * 转成list
     * 解决泛型问题
     * @param json
     * @param cls
     * @param <T>
     * @return
    </T> */
    fun <T> parseToList2(json: String?, cls: Class<T>?): List<T> {
        val gson = Gson()
        val list: MutableList<T> = ArrayList()
        val array = JsonParser().parse(json).asJsonArray
        for (elem in array) {
            list.add(gson.fromJson(elem, cls))
        }
        return list
    }

    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    fun <T> parseToListMaps(gsonString: String?): List<Map<String, T>>? {
        var list: List<Map<String, T>>? = null
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    object : TypeToken<List<Map<String?, T>?>?>() {}.type)
        }
        return list
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    fun <T> parseToMap(gsonString: String?): Map<String, T>? {
        var map: Map<String, T>? = null
        if (gson != null) {
            map = gson.fromJson(gsonString, object : TypeToken<Map<String?, T>?>() {}.type)
        }
        return map
    }

    /**
     * 将json 字符串转换为 String List
     *
     * @param listJsonStr
     * @return String list
     */
    fun parsJson2StringList(listJsonStr: String?): List<String>? {
        val type = object : TypeToken<ArrayList<String?>?>() {}.type
        return gson!!.fromJson<List<String>>(listJsonStr, type)
    }

    /**
     * 将Map转化为Json
     *
     * @param map
     * @return String
     */
    fun <T> mapToJson(map: Map<String?, T>?): String {
        return gson!!.toJson(map)
    }
    //    /**
    //     * JsonElement
    //     * @param list
    //     * @return
    //     */
    //    public JsonElement getElement(List<AppInfo> list){
    //        return gson.toJsonTree(list);
    //    }
    /**
     * JsonArray
     * @param list
     * @return
     */
    fun getJsonarray(list: String?): JsonArray {
        val parser = JsonParser()
        return parser.parse(list).asJsonArray
    }

    companion object {
        val instance = GsonUtils()
        private val gson: Gson? = Gson()
    }
}