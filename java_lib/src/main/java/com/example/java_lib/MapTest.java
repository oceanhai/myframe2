package com.example.java_lib;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    public static void main(String[] args){
        Map<String,String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");

        for (Map.Entry<String,String> entry : map.entrySet()){
            System.out.println("key="+entry.getKey()+",value="+entry.getValue());
        }
        System.out.println("----------------------");
        map.put("1","4");
        for (Map.Entry<String,String> entry : map.entrySet()){
            System.out.println("key="+entry.getKey()+",value="+entry.getValue());
        }
        System.out.println("----------------------");
        Map<String,Long> map2 = new HashMap<>();
        map2.put("1",123L);
        map2.put("2",345L);
        System.out.println("get 1 :"+map2.get("1"));
        System.out.println("get 3 :"+map2.get("3"));
        Long pageStartTime = map2.get("3");
        System.out.println("pageStartTime = " + pageStartTime);

        map2.remove("1");
        System.out.println("remove 后再次get 1 :"+map2.get("1"));
    }
}
