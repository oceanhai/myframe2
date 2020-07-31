package com.example.java_lib.pactera;

import com.google.gson.Gson;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GsonTest {

    /**
     * TreeMap 有序  生成str不会变化
     */
    @Test
    public void method01(){
        Map<String, Object> params = new HashMap<>();
        params.put("appId", "app68ac701c43f14eca92e364fb2929dc2a");
        params.put("currVersion", "1.2.0");
        params.put("devType", 1);
        String json1 = new Gson().toJson(params);
        System.out.println("json1 = "+json1);

        Map<String, Object> params2 = new TreeMap<>();
        params2.put("appId", "app68ac701c43f14eca92e364fb2929dc2a");
        params2.put("devType", 1);
        params2.put("currVersion", "1.2.0");
        String json2 = new Gson().toJson(params2);
        System.out.println("json2 = "+json2);

        Map<String, Object> params3 = new HashMap<>();
        params3.put("devType", 1);
        params3.put("appId", "app68ac701c43f14eca92e364fb2929dc2a");
        params3.put("currVersion", "1.2.0");
        String json3 = new Gson().toJson(params3);
        System.out.println("json3 = "+json3);
    }


    @Test
    public void method02(){
        ExtParm extParm1 = new ExtParm();
        extParm1.setExtKey("version");
        extParm1.setExtOperator(0);
        extParm1.setExtValue("2");

        String str1 = GsonUtils.getInstance().toJson(null);
        System.out.println(str1);

        String str2 = GsonUtils.getInstance().toJson(new ArrayList<ExtParm>());
        System.out.println(str2);

        List<ExtParm> list = new ArrayList<>();
        list.add(extParm1);
        String str3 = GsonUtils.getInstance().toJson(list);
        System.out.println(str3);
    }
}
