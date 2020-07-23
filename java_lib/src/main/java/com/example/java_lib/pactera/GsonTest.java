package com.example.java_lib.pactera;

import com.google.gson.Gson;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class GsonTest {

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
}
