package com.wuhai.demo.lotteryticketkotlin.utils

import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Created by wuhai on 2015/11/17.
 * 常见工具类
 */
object CommonUtils {
    /**
     * Assets 资源文件转换成 string
     * Assets里可放josn数据
     *
     * @param fileName
     * @param context
     * @return
     */
    fun getFromAssets(fileName: String?, context: Context): String? {
        val json = StringBuffer()
        //.getClass().getClassLoader().getResourceAsStream("assets/" + "query.json");
        var `is`: InputStream? = null
        try {
            `is` = context.assets.open(fileName!!)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        if (`is` == null) {
            Log.e("fileName err", "fileName 路径有问题")
            return null
        }
        val reader = BufferedReader(InputStreamReader(`is`))
        val sb = StringBuilder()
        var line: String? = null
        try {
            while (reader.readLine().also { line = it } != null) {
                // sb.append(line + "/n");
                sb.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                `is`.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return sb.toString()
    }
}