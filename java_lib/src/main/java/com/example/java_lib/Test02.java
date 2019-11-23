package com.example.java_lib;

import java.util.HashMap;
import java.util.Map;

public class Test02 {
    final static void func(){
        System.out.println("g");
    }

    public static void main(String[] args){
//        for(int j=0; j<1000; j++)
//            func();

        method01();
    }

    private static void method01() {
        Map<String,String> map = new HashMap<>();
        map.put("a","a");
        map.put(null,"b");
        map.put("c",null);

        map.put("a","d");
        map.put(null,"e");
        map.put("f",null);

        for (Map.Entry<String,String> entry : map.entrySet()){
            System.out.println(entry.getKey()+","+entry.getValue());
        }
    }
}
