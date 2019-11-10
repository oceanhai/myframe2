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

    }
}
