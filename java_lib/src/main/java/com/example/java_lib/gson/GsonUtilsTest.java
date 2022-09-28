package com.example.java_lib.gson;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class GsonUtilsTest {
    public static void main(String[] args){
        method01();
    }

    private static void method01() {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("certNo", "12");
        params.put("name", "wuhai");
        params.put("phone", "110");
        String str = GsonUtils.getInstance().mapToJson(params);
        System.out.println("str="+str);

//        HashMap<String, String> params1 = GsonUtils.getInstance().parseToMap(str);

        //TODO
        Gson gson = new Gson();
        HashMap<String, String> resultMap = new HashMap<>();
        HashMap<String,String> hashMap = gson.fromJson(str, resultMap.getClass());

        System.out.println("hashMap:"+hashMap.toString());
//        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(Map.Entry<String, String> entry : hashMap.entrySet()){
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }

    }
}
