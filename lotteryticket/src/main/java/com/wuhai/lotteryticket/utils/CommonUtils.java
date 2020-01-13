package com.wuhai.lotteryticket.utils;


import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by wuhai on 2015/11/17.
 * 常见工具类
 */
public final class CommonUtils {

    private CommonUtils() {
    }

    /**
     * Assets 资源文件转换成 string
     * Assets里可放josn数据
     *
     * @param fileName
     * @param context
     * @return
     */
    public static String getFromAssets(String fileName, Context context) {
        StringBuffer json = new StringBuffer();
        //.getClass().getClassLoader().getResourceAsStream("assets/" + "query.json");
        InputStream is = null;
        try
        {
            is = context.getAssets().open(fileName);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        if (is == null)
        {
            Log.e("fileName err", "fileName 路径有问题");
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try
        {
            while ((line = reader.readLine()) != null)
            {
                // sb.append(line + "/n");
                sb.append(line);
            }
        } catch (IOException e)
        {

            e.printStackTrace();
        } finally
        {
            try
            {
                is.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
